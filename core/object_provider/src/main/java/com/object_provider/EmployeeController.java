package com.object_provider;

import com.object_provider.abstract_factory.EmployeeTrigger;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeTrigger employeeTrigger;

    @GetMapping
    public String getEmployee(@RequestParam WorkType workType) {
        return employeeTrigger.getFirstEmployeeByWorkType(workType).getName();
    }
}
