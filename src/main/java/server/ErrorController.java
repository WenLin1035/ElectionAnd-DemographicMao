/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.*;

/**
 *
 * @author webst
 */
@RestController
public class ErrorController {
    @Autowired
    private ErrorRepo service;
    
    @GetMapping("/errors/{id}")
    public List<Error> lister(@PathVariable String id){
        System.out.println(service);
        return service.findByStatefp(id);
    }
    
    
    @GetMapping("/errors")
    public List<Error> list() {
        return service.findAll();
    }
    
}
