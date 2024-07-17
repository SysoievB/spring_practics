package com.autowire_require_false;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentClass {
    @Autowired(required = false)
    private NotComponentClass notComponentClass;//only field injection not constructor injection

    void helloFromComponentClass() {
        System.out.println("--------hello from ComponentClass---------");
        if (notComponentClass != null) {
            notComponentClass.helloFromNotComponentClass();
        } else {
            System.out.println("NotComponentClass is not available");
        }
    }
}
