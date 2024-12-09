package com.bootstrapwith;

import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.support.DefaultTestContextBootstrapper;

public class CustomTestContextBootstrapper extends DefaultTestContextBootstrapper {

    @Override
    protected Class<? extends ContextLoader> getDefaultContextLoaderClass(Class<?> testClass) {
        System.out.println("CustomTestContextBootstrapper is being used!");
        return super.getDefaultContextLoaderClass(testClass);
    }
}
