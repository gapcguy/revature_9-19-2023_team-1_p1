package com.revature.p1.banking.Controller;

import com.revature.p1.banking.DAO.EmployeeDAO;
import com.revature.p1.banking.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                 // Subset of the @Controller Stereotype annotation. Makes a class a bean, plus "MVC Stuff"
@RequestMapping("/employees")    // This is one that will take over the role of the endpoint handlers. Every request to 5000/p1/employee will go to this class.
@CrossOrigin                    // Permits the ability to take in HTTP requests from different origins.
public class EmployeeController {

    private EmployeeDAO eDAO;

    // We need to autowire the DAO, to inject it as a dependency of the controller (since we need to use its methods).
    @Autowired // Remember, spring boot will automagically inject an eDAO thanks to this annotation.
    public EmployeeController(EmployeeDAO eDAO) {
        this.eDAO = eDAO;
    }

    // HTTP Requests -----
    // This will handle the "getting" of all employees. It will be reached by a get request to /employee.
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {

        // Call on DAO to get the employees

        // Return a ResponseEntity. Set the status code to 200. Set the response body data
        // There will be no error handling here.
        return ResponseEntity.status(200).body(eDAO.findAll());
    }

    @PostMapping    // Method accepts HTTP POST requests ending in /employee
    // Thanks to @Request Body in the parameter, the body of the request will be automatically converted
    // into an Employee object called "e"
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e) {

        //Save the incoming employee to the database, and save the return in a variable for error handling.
        Employee newEmp = eDAO.save(e);

        if(newEmp == null) { return ResponseEntity.badRequest().build(); }

        // Returns an HTTP 202 as well as a new user in the response body.
        //Accepted is a friendlier version of .status(202).
        return ResponseEntity.accepted().body(newEmp);
    }

    // Takes in an ID in the request params, and returns the Employee with a given ID.
    @GetMapping("/{id}") // Get requests to /employee/{employeeId} will be here.
    //@PathVariable allows us to get the user-inputted path variable from the request.
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {

        // Now we can try a little error handling
        // If the user sends an invalid id, send a bad request status code and no response body.
        if(id <= 0) { return ResponseEntity.badRequest().build(); }

        // get an Employee from the Dao
        Employee e = eDAO.getReferenceById(id);

        // If no user is associated with the inputted ID, send a 204 error, and no response body.
        if(e == null) { return ResponseEntity.noContent().build(); }

        // if none of the checks get activated, send the user.
        return ResponseEntity.ok().body(e);

    }

    @PutMapping
    public ResponseEntity<Employee> updateEntireEmployee(@RequestBody Employee e) {

        Employee updatedEmployee = eDAO.saveAndFlush(e);

        //return the updated employee
        return ResponseEntity.accepted().body(updatedEmployee);
    }
}
