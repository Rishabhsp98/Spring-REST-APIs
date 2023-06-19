package com.example.springrestapis;


import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class springControllerDBdemo {

    private  DBOperations dbOperations;

    // on the object creation of the controller ,
    // we call dboperations to connect to database , so that don;t need to call again
    public springControllerDBdemo(){
        this.dbOperations = new DBOperations();
    }

    @GetMapping("/employees")
    public List<restApimodel> getEmployee() throws SQLException {
        return this.dbOperations.getAllEmployee();
    }

    @GetMapping("/employee/id/{id}")
    public restApimodel getEmployee(@PathVariable("id") int id) throws SQLException {
            return this.dbOperations.getEmployee(id);
    }

    @PostMapping("/employee")
    public void createEmployee(@RequestBody restApimodel restApimodel) throws SQLException {
        this.dbOperations.createEmployee(restApimodel);
    }


    @PutMapping("/employee/id/{id}")
    public void updateEmployee(@RequestBody restApimodel restApimodel) throws Exception {

    }
    @PatchMapping("/employee/id/{id}")
    public restApimodel patchUpdateEmployee(@RequestBody restApimodel restApimodel)
    {
        return null;
    }

    @DeleteMapping("/employee/id/{id}")
    public void deleteEmployee(@PathVariable("id") int id){

    }


}
