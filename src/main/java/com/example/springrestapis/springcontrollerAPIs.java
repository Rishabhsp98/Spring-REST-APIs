package com.example.springrestapis;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController   // to make visible to dispatcherServlet
public class springcontrollerAPIs {


    private HashMap<Integer,restApimodel> mp = new HashMap<>();

    @GetMapping("/employees")
    public List<restApimodel> getEmployees(){
//        System.out.println(this.mp.values());
        return this.mp.values().stream().collect(Collectors.toList());
//        return "Hello!";
    }

    @GetMapping("/employee/id/{id}")
    public restApimodel getEmployee(@PathVariable("id") int id){
        return this.mp.get(id);
    }

    @PostMapping("/employee")
    public void createEmployee(@RequestBody restApimodel restApimodel) throws Exception {
        if(restApimodel.getId() == null)
        {
            throw new Exception("Employee Id can not be null!");
        }
        this.mp.put(restApimodel.getId(),restApimodel); // storing map of request body to id
    }
}
