package com.boot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private final MyService myService;
    private final MyServiceBean myServiceBean;

    public MyController(MyService myService, @Qualifier("windowsService") MyServiceBean myServiceBean) {
        this.myService = myService;
        this.myServiceBean = myServiceBean;
    }

    @GetMapping("/on-missing-service")
    public String getServiceMissing() {
        return "Active Service: " + myService.getDescription();
    }

    @GetMapping("/service")
    public String getService() {
        return "Active Service: " + myServiceBean.getName();
    }
}
