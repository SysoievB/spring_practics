package com.test_execution_listener;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class CustomTestExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("CustomTestExecutionListener: Before test class - "
                + testContext.getTestClass().getName());
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        System.out.println("CustomTestExecutionListener: Before test instance - "
                + testContext.getTestInstance().getClass().getName());
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        System.out.println("CustomTestExecutionListener: Before test method - "
                + testContext.getTestMethod().getName());
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        System.out.println("CustomTestExecutionListener: After test method - "
                + testContext.getTestMethod().getName());
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        System.out.println("CustomTestExecutionListener: After test class - "
                + testContext.getTestClass().getName());
    }
}
