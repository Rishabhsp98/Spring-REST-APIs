package com.example.springrestapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {


    private static Logger logger = LoggerFactory.getLogger(DBOperations.class);

    private Connection connection;


    // in the constructor first we establish a connection and then we create a table in that db created
    public DBOperations(){
        try{
            createConnection();
            createEmployeeTable();
        }
        catch (Exception e){
            logger.error("Exception in Db Connection with the - {}",e);
        }
    }
    private void createConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employee_db";
        this.connection = DriverManager.    getConnection(url,"root","1998Ch@mp");
    }


    // using statement interface methods to create tables from java-spring
    public void createEmployeeTable() throws SQLException {
        String sqlCreateTablequery ="create table if not exists employee(id int primary key auto_increment, name varchar(30), age int)";

        Statement statement = this.connection.createStatement();
        boolean result = statement.execute(sqlCreateTablequery);

        logger.info("Create Table successfull, {}",result);
    }

    public void createEmployee(restApimodel restApimodel) throws SQLException {
        // write query to insert into table
        // insert into employee(id,name,age) VALUES(1,"ABC",24);

        String sqlinsertData ="insert into employee(name,age) VALUES('" + restApimodel.getName() + "'," + restApimodel.getAge() + ")";

        Statement statement = this.connection.createStatement();
        int result = statement.executeUpdate(sqlinsertData);

        logger.info("Number of employees created {}",result);
    }
    public restApimodel getEmployee(Integer empId) throws SQLException {
        String getemployeeQuery = "Select * from employee where id =" + empId;

        Statement statement = this.connection.createStatement(); // this gives us empty statement , to use after

        ResultSet rs = statement.executeQuery(getemployeeQuery);
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Integer age = rs.getInt("age");

            restApimodel employee = new restApimodel(id, name, age);
            return employee;
        }
        return null;
    }

    public List<restApimodel> getAllEmployee() throws SQLException {

            String getAllDataquery = "Select * from employee";

            Statement statement = this.connection.createStatement(); // this gives us empty statement , to use after

            List<restApimodel> employeeList = new ArrayList<>();
            ResultSet rs = statement.executeQuery(getAllDataquery);
            while(rs.next())
            {
                int empId = rs.getInt("id");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");

                restApimodel employee = new restApimodel(empId,name,age);
                employeeList.add(employee);
            }
            return employeeList;
    }
}
