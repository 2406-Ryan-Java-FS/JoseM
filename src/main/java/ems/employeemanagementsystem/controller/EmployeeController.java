package ems.employeemanagementsystem.controller;

import ems.employeemanagementsystem.entity.Employee;
import ems.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing employee related operations.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Endpoint to get all employees for an admin account.
     *
     * @param accountId The ID of the admin account requesting the employee list.
     * @return A ResponseEntity containing the list of all employees.
     */
    @GetMapping("all/{accountId}")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable Long accountId){

        return ResponseEntity.ok(employeeService.getAllEmployees(accountId));
    }

    /**
     * Endpoint to add a new employee to an account.
     *
     * @param employee The employee to be added.
     * @param AccountId The ID of the account to which the employee will be added.
     * @return A ResponseEntity containing the added Employee.
     */
    @PostMapping("/add/{AccountId}")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, @PathVariable Long AccountId){

        return ResponseEntity.ok(employeeService.addEmployee(employee, AccountId));
    }

    /**
     * Endpoint to get a specific employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return A ResponseEntity containing the Employee.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){

        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    /**
     * Endpoint to delete a specific employee by ID.
     *
     * @param id The ID of the employee to delete.
     * @return A ResponseEntity containing the deleted Employee.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){

        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

    /**
     * Endpoint to update an existing employee's information.
     *
     * @param employee The employee with updated information.
     * @return A ResponseEntity containing the updated Employee.
     */
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){

        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

}
