package com.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

    void getNotificationFromPrototype() {
        PrototypeBean prototypeBean = getPrototypeBean();
        System.out.println(prototypeBean.getNotification());
    }

    @Lookup("prototypeBean")
    public PrototypeBean getPrototypeBean() {
        return null;//new PrototypeBean();
    }
}
