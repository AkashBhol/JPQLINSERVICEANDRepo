package com.example.FilterMultiplevalue.ServiceImpl;

import com.example.FilterMultiplevalue.DTO.EmployeeDTO;
import com.example.FilterMultiplevalue.Model.Employee;
import com.example.FilterMultiplevalue.Repo.EmployeeRepo;
import com.example.FilterMultiplevalue.Service.IEmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EntityManager entityManager;

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setMinage(employeeDTO.getMinage());
        employee.setMaxAge(employeeDTO.getMaxage());
        employee.setRole(employeeDTO.getRole());
        Employee save = employeeRepo.save(employee);
        return save;
    }


    @Override
    public List<Employee> filterEmployee(EmployeeDTO employeeDTO) {
        StringBuilder queryString = new StringBuilder("SELECT e FROM Employee e WHERE 1=1");
        StringJoiner conditions = new StringJoiner(" AND ");


        if (!(employeeDTO.getName().isEmpty() && employeeDTO.getName() != null)) {
            conditions.add("e.name = :name");
        }
        if (!(employeeDTO.getDepartment().isEmpty() && employeeDTO.getDepartment() != null)) {
            conditions.add("e.department = :department");
        }
        if (!(employeeDTO.getMinage().isEmpty() && employeeDTO.getMinage() != null)) {
            conditions.add("e.minage = :minage");
        }
        if (!(employeeDTO.getMaxage().isEmpty() && employeeDTO.getMaxage() != null)) {
            conditions.add("e.maxAge = :maxAge");
        }
        if (!(employeeDTO.getRole().isEmpty() && employeeDTO.getRole() != null)) {
            conditions.add("e.role = :role");
        }

        queryString.append(" AND ").append(conditions.toString());
        TypedQuery<Employee> query = entityManager.createQuery(queryString.toString(), Employee.class);
        if (employeeDTO.getName() != null) {
            query.setParameter("name", employeeDTO.getName());
        }
        if (employeeDTO.getDepartment() != null) {
            query.setParameter("department", employeeDTO.getDepartment());
        }
        if (employeeDTO.getMinage() != null) {
            query.setParameter("minage", employeeDTO.getMinage());
        }
        if (employeeDTO.getMaxage() != null) {
            query.setParameter("maxAge", employeeDTO.getMaxage());
        }
        if (employeeDTO.getRole() != null) {
            query.setParameter("role", employeeDTO.getRole());
        }

        return query.getResultList();
    }

    @Override
    public List<Employee> filterByNameAndDepartment(String name, String department) {

        //1st approach can debug
        StringBuilder queryString = new StringBuilder("SELECT e FROM Employee e where 1=1");
        StringJoiner conditions = new StringJoiner(" AND ");
        if(!(name.isEmpty() && name!=null)){
            conditions.add("e.name = :name");
        }
        if(!(department.isEmpty() && department!=null)){
            conditions.add("e.department = :department");
        }
        queryString.append(" AND ").append(conditions);
        TypedQuery<Employee> query = entityManager.createQuery(queryString.toString(), Employee.class);
        if(name!=null){
            query.setParameter("name",name);
        }
        if(department!=null){
            query.setParameter("department",department);
        }
        return query.getResultList();


        //2nd approach in repository you can not debug
//        List<Employee> byNameAndDepartament = employeeRepo.findByNameAndDepartment(name, department);
//        return byNameAndDepartament;
    }

    @Override
    public List<Employee> findByNameAndDepartmentORRole(String name, String department, String role) {
        StringBuilder queryString=new StringBuilder("SELECT e FROM Employee e WHERE ");
        StringJoiner conditionsAnd=new StringJoiner(" AND ");
        StringJoiner conditionOr=new StringJoiner(" OR ");

        if(!(name.isEmpty() && name!=null)){
            conditionsAnd.add("e.name = :name");
        }
        if(!(department.isEmpty() && department!=null)){
            conditionsAnd.add("e.department = :department");
        }
        if(!(role.isEmpty() && role!=null)){
            conditionOr.add("e.role = :role");
        }
        if (conditionsAnd.length() > 0) {
            queryString.append("(").append(conditionsAnd.toString()).append(")");
        }
        if (conditionOr.length() > 0) {
            if (conditionsAnd.length() > 0) {
                queryString.append(" OR ");
            }
            queryString.append("(").append(conditionOr.toString()).append(")");
        }
        TypedQuery<Employee> query = entityManager.createQuery(queryString.toString(), Employee.class);
        if(name!=null){
            query.setParameter("name",name);
        }
        if(department!=null){
            query.setParameter("department",department);
        }
        if(role!=null){
            query.setParameter("role",role);
        }
        return query.getResultList();
    }

    @Override
    public List<Employee> findByNameAndDepartmentAndMinageAndRole1(String name, String department, String minage, String role) {
        List<Employee> byNameAndDepartmentAndMinageAndRole = employeeRepo.findByNameAndDepartmentAndMinageAndRole(name, department, minage, role);
        return byNameAndDepartmentAndMinageAndRole;
    }

}
