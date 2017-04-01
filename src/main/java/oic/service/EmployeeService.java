package oic.service;

import oic.entity.СотрудникиEntity;
import oic.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<СотрудникиEntity> findAll(){
        return employeeRepository.findAll();
    }
}
