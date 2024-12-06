package com.spring_transactions;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionIsolationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void testReadUncommittedIsolation() throws InterruptedException, ExecutionException {
        try (val executorService = Executors.newFixedThreadPool(2)) {

            // Thread 1: Inserts a user but doesn't commit immediately
            Callable<Void> writerTask = () -> {
                userService.createUserWithoutCommit("UncommittedUser");
                return null;
            };

            // Thread 2: Reads data using READ_UNCOMMITTED isolation level
            Callable<List<User>> readerTask = () -> userService.readUncommittedUsers();

            // Submit writer task
            Future<Void> writerFuture = executorService.submit(writerTask);
            Thread.sleep(1000); // Ensure the writer task starts first

            // Submit reader task
            Future<List<User>> readerFuture = executorService.submit(readerTask);

            // Wait for tasks to complete
            writerFuture.get(); // This will block until Thread 1 finishes
            List<User> users = readerFuture.get(); // This will get the results from Thread 2

            // Assertions
            assertThat(users).anyMatch(user -> user.getName().equals("UncommittedUser"));
        }
    }

    @Test
    void testReadCommittedIsolation() throws InterruptedException, ExecutionException {
        try (val executorService = Executors.newFixedThreadPool(2)) {

            // Thread 1: Writes a user but does not commit immediately
            Callable<Void> writerTask = () -> {
                userService.createUserWithoutCommit("UncommittedUser2");
                return null;
            };

            // Thread 2: Attempts to read users
            Callable<List<User>> readerTaskBeforeCommit = () -> userService.readCommittedUsers();

            // Submit writer task
            Future<Void> writerFuture = executorService.submit(writerTask);
            Thread.sleep(1000); // Ensure writer starts first

            // Submit reader task (attempt to read uncommitted data)
            Future<List<User>> readerFutureBeforeCommit = executorService.submit(readerTaskBeforeCommit);

            // Wait for writer to finish
            writerFuture.get();

            // Submit another reader task after the transaction commits
            Callable<Void> writerTaskCommit = () -> {
                userService.createUserWithCommit("CommittedUser");
                return null;
            };
            Future<Void> writerFutureCommit = executorService.submit(writerTaskCommit);
            writerFutureCommit.get();

            Future<List<User>> readerFutureAfterCommit = executorService.submit(readerTaskBeforeCommit);

            // Assertions
            List<User> usersBeforeCommit = readerFutureBeforeCommit.get();
            List<User> usersAfterCommit = readerFutureAfterCommit.get();

            // Validate no uncommitted data was read
            assertThat(usersBeforeCommit)
                    .noneMatch(user -> user.getName().equals("UncommittedUser2"));

            // Validate committed data is visible
            assertThat(usersAfterCommit)
                    .anyMatch(user -> user.getName().equals("CommittedUser"));
        }
    }

    @Test
    void testSerializableIsolation() throws InterruptedException, ExecutionException {
        try (val executorService = Executors.newFixedThreadPool(2)) {

            // Thread 1: Attempts to read users
            Callable<List<User>> readerTask = () -> userService.readUsers();

            // Thread 2: Writes a user
            Callable<Void> writerTask = () -> {
                userService.serializableExample();
                return null;
            };

            // Submit reader task (attempt to read data with SERIALIZABLE isolation)
            Future<List<User>> readerFuture = executorService.submit(readerTask);
            Thread.sleep(2000); // Ensure the reading transaction starts first

            // Submit writer task while the first transaction is still ongoing
            Future<Void> writerFuture = executorService.submit(writerTask);

            // Capture exceptions or failures in writer thread due to SERIALIZABLE constraints
            try {
                writerFuture.get(); // Attempt to commit the writer transaction
            } catch (ExecutionException ex) {
                System.err.println("Writer transaction failed: " + ex.getCause().getMessage());
            }

            // Wait for the reader transaction to finish
            List<User> usersRead = readerFuture.get();

            // Assertions
            assertThat(usersRead).doesNotContain(new User("Serialized User", "serialized@example.com"));

            // After reader finishes, re-execute reader
            List<User> usersAfterReader = userService.readUsers();
            assertThat(usersAfterReader).anyMatch(user -> user.getName().equals("Serialized User"));
        }
    }

    @Test
    public void testRepeatableReadIsolation() throws InterruptedException, ExecutionException {
        // Prepopulate database with a user
        User initialUser = new User("Initial User", "initial@example.com");
        userRepository.save(initialUser);

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            // Reader Task: Reads users twice within the same transaction
            Callable<List<User>> readerTask = () -> {
                List<User> initialRead = userService.readUserWithinTransaction2();

                // Simulate a delay before the second read
                Thread.sleep(5000);

                // Read the data again
                List<User> secondRead = userService.readUserWithinTransaction2();

                // Ensure the data is the same in both reads
                assertThat(initialRead).isEqualTo(secondRead);

                return secondRead;
            };

            // Writer Task: Updates or deletes a user
            Callable<Void> writerTask = () -> {
                Thread.sleep(2000); // Ensure the reader starts reading first
                userService.createUserWithinTransaction();
                return null;
            };

            // Submit reader and writer tasks
            Future<List<User>> readerFuture = executorService.submit(readerTask);
            Future<Void> writerFuture = executorService.submit(writerTask);

            // Wait for both tasks to complete
            writerFuture.get(); // Ensures the writer commits after reader's initial read
            List<User> usersAfterRead = readerFuture.get();

            // Validate the new user exists in the database after transactions
            assertThat(usersAfterRead)
                    .noneMatch(user -> user.getName().equals("COMPLETELY NEW USER"));

            // Confirm new user is added after the reader transaction completes
            List<User> usersAfterTransaction = userRepository.findAll();
            assertThat(usersAfterTransaction)
                    .anyMatch(user -> user.getName().equals("COMPLETELY NEW USER"));
        }
    }
}

