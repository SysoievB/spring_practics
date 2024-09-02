package com.object_provider;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class EmployeeController {
    private final ObjectProvider<EmployeeService> employeeServices;

    @GetMapping
    public String getEmployee(@RequestParam WorkType workType) {
        EmployeeService service = employeeServices.orderedStream()
                .filter(s -> s.getWorkType().equals(workType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No service found for the given work type"));

        Employee employee = service.getFirstEmployeeByWorkType();
        return service.getEmployeeName(employee);
    }

    @GetMapping("/available")
    public String getIfAvailable() {
        return employeeServices.getIfAvailable(RemoteEmployeeService::new).getClass().getSimpleName();
    }//NoUniqueBeanDefinitionException

    @GetMapping("/unique")
    public String getIfUnique() {
        return employeeServices.getIfUnique(OnsiteEmployeeService::new).getClass().getSimpleName();
    }
}
