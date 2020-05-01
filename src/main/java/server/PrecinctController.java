/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author webst
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
 
import org.springframework.web.bind.annotation.*;
 
//This annotation marks the class as a request handler and maps the request sent from the client to a method in the server
@RestController
public class PrecinctController {
    //The Autowired annotation injects the constructor of PrecinctRepo into this class and that object has the methods from PrecinctRepo and JPA Repo
    @Autowired
    private PrecinctRepo service;
    
    // RESTful API methods for Retrieval operations
    @GetMapping("/precincts")
    public List<Precinct> findAllPrecincts() {
        return service.findAll();
    }
    
    @GetMapping("/precincts/{id}")
    public List<Precinct> findPrecinctsInState(@PathVariable String id){
        return service.findByStatefp(id);
    }
    
    // RESTful API method for Update operation
    @PutMapping("/precincts/{id}")
    public void update(@RequestBody Precinct precinct, @PathVariable String id) {
        try {
            Precinct existPrecinct = service.findByOgrFID(Integer.parseInt(id));
            precinct.setOgrFID(Integer.parseInt(id));
            service.save(precinct);
        } catch (NoSuchElementException e) {
        }      
    }
    
    // RESTful API method for Create operation
    @PostMapping("/precincts")
    public void add(@RequestBody Precinct precinct) {
        System.out.println("hi from java");
        System.out.println(precinct);
        //service.save(precinct);
    }
    
    @Transactional
    @PutMapping("/merge/{name1}/{name2}")
    public ResponseEntity<?> merge(@RequestBody List<Precinct> precinctList, @PathVariable String name1, @PathVariable String name2){
        //TO-DO
        try {
            Precinct precinct1 = service.findByName(name1);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }
    
    // RESTful API method for Delete operation
    @DeleteMapping("/precincts/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}