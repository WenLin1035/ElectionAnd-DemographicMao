/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author webst
 */
@RestController
public class ErrorController {
    @Autowired
    private ErrorRepo service;
    
    @GetMapping("/errors/{id}")
    public List<Error> findErrorById(@PathVariable String id){
        return service.findByStatefp(id);
    }
    
    @GetMapping("/errors")
    public List<Error> findAllErrors() {
        return service.findAll();
    }
    
    @PutMapping("/errors/{id}")
    public void update(@RequestBody Error error, @PathVariable String id){
        try {
            Error existError = service.findById(Integer.parseInt(id)).get();
            error.setId(existError.getId());
            error.setCommentTime(new Timestamp(System.currentTimeMillis()));
            service.save(error);
        } catch (NoSuchElementException e) {
        } 
    }
}
