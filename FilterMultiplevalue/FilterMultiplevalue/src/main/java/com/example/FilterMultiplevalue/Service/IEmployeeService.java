package com.example.FilterMultiplevalue.Service;

import com.example.FilterMultiplevalue.DTO.EmployeeDTO;
import com.example.FilterMultiplevalue.Model.Employee;

import java.util.List;

public interface IEmployeeService {

    public Employee createEmployee(EmployeeDTO employeeDTO);

    public List<Employee> filterEmployee(EmployeeDTO employeeDTO);

    public List<Employee> filterByNameAndDepartment(String name,String department);

    public List<Employee> findByNameAndDepartmentORRole(String name,String department,String role);

    public List<Employee> findByNameAndDepartmentAndMinageAndRole1(String name,String department,String minage,String role);
}
