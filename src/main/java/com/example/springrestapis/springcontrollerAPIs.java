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
    public restApimodel getEmployee(@PathVariable("id") int id) throws Exception {
        if(!mp.containsKey(id))
        {
            throw new Exception("User Does not Exist");
        }
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

    @PatchMapping("/employee/id/{id}")
    public restApimodel patchUpdateEmployee(@RequestBody restApimodel restApimodel){

        restApimodel employeeFromMap = this.mp.get(restApimodel.getId());

        if(restApimodel.getAge() != null){
            employeeFromMap.setAge(restApimodel.getAge());
        }

        if(restApimodel.getName() != null){
            employeeFromMap.setName(restApimodel.getName());
        }

        return this.mp.put(restApimodel.getId(), employeeFromMap);
    }


    @PutMapping("/employee/id/{id}")
    public void updateEmployee(@RequestBody restApimodel restApimodel) throws Exception {
        if(restApimodel.getId() == null || !this.mp.containsKey(restApimodel.getId()))
        {
            throw new Exception("Not valid request!!");
        }
        this.mp.put(restApimodel.getId(),restApimodel); // storing map of request body to id
    }

    // put api , patch api and delete api



    @DeleteMapping("/employee/id/{id}")
    public void deleteEmployee(@PathVariable int id) throws Exception {
        if(!mp.containsKey(id))
        {
            throw new Exception("User Does not Exist");
        }
        this.mp.remove(id);
    }
}
