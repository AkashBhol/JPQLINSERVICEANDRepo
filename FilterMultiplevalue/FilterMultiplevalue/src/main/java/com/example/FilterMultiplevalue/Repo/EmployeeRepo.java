package com.example.FilterMultiplevalue.Repo;

import com.example.FilterMultiplevalue.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

    //if name and department are not null or empty
//    @Query("SELECT e FROM Employee e where e.name = :name and e.department = :department")
//    List<Employee> findByNameAndDepartment(String name, String department);

    //if name and department are null
    @Query("SELECT e FROM Employee e WHERE" +
            "(e.name = :name OR name IS NULL) AND" +
            "(e.department = :department OR department IS NULL)")
     public  List<Employee> findByNameAndDepartment(String name,String department);

    //finding all value if it is null
    @Query("SELECT e FROM Employee e WHERE " +
            "(:name IS NULL  OR e.name = :name) AND "+
            "(:department IS NULL OR e.department = :department) AND " +
            "(:minage IS NULL OR e.minage = :minage) AND " +
            "(:role IS NULL OR e.role = :role)")
    public List<Employee> findByNameAndDepartmentAndMinageAndRole(
            @Param("name") String name,
            @Param("department") String department,
            @Param("minage") String minage,
            @Param("role") String role
    );

//    @Query("SELECT e FROM Employee e WHERE " +
//            "(:name IS NULL OR e.name = :name) AND " +
//            "(:department IS NULL OR e.department = :department) AND " +
//            "(:minage IS NULL OR e.minage = :minage) AND " +
//            "(:role IS NULL OR e.role = :role)")
//    public List<Employee> findByNameAndDepartmentAndMinageAndRole(
//            @Param("name") String name,
//            @Param("department") String department,
//            @Param("minage") String minage,
//            @Param("role") String role
//    );

}
