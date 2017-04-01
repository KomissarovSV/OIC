package oic.controller;

import oic.entity.СотрудникиEntity;
import oic.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("employee/all")
    public List<СотрудникиEntity> getAll(){
        return employeeService.findAll();
    }
}
