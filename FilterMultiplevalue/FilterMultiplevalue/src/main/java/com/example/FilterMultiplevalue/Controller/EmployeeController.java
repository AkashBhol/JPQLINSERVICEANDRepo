package com.example.FilterMultiplevalue.Controller;

import com.example.FilterMultiplevalue.DTO.EmployeeDTO;
import com.example.FilterMultiplevalue.Model.Employee;
import com.example.FilterMultiplevalue.Service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v4")
@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService  iEmployeeService;

    @PostMapping("/post/employee")
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = iEmployeeService.createEmployee(employeeDTO);
        return employee;
    }

    @GetMapping("/get")
    public List<Employee> getFilterEmployee(@RequestBody EmployeeDTO employeeDTO){
        List<Employee> employees = iEmployeeService.filterEmployee(employeeDTO);
        return employees;
    }

    @GetMapping("/get/name/departament")
    public List<Employee> getEmployeeByNameAndDepartmane(
            @RequestParam("name") String name,
            @RequestParam("department") String department){
        List<Employee> employees = iEmployeeService.filterByNameAndDepartment(name, department);
        return employees;
    }

    @GetMapping("/get/name/department/role")
    public List<Employee> getEmployeeByNameAndDepartamentOrRole(
            @RequestParam("name") String name,
            @RequestParam("department") String department,
            @RequestParam("role") String role
    )
    {
        List<Employee> byNameAndDepartmentORRole = iEmployeeService.findByNameAndDepartmentORRole(name, department, role);
        return  byNameAndDepartmentORRole;
    }

    @GetMapping("/get/name/department/minage/role")
    public List<Employee> getNameDepartmentMinageRole(
            @RequestParam("name") String name,
            @RequestParam("department") String department,
            @RequestParam("minage") String minage,
            @RequestParam("role") String role
    )
    {
        List<Employee> byNameAndDepartmentAndMinageAndRole = iEmployeeService.findByNameAndDepartmentAndMinageAndRole1(name, department, minage, role);
        return byNameAndDepartmentAndMinageAndRole;
    }

}
