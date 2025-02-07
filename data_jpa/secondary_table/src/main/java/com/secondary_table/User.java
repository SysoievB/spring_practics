package com.secondary_table;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * The secondary table must be linked to the primary table so that Hibernate knows how
 * to join the data when retrieving or persisting the entity.
 * The foreign key ensures referential integrity, meaning the secondary table's data
 * always corresponds to an entity in the primary table.
 * */

@Entity
@Table(name = "users")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@SecondaryTable(name = "user_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String email;

    // Stored in the user_details table
    @Column(table = "user_details")
    String address;

    @Column(name = "phone_number", table = "user_details")
    String phoneNumber;
}

