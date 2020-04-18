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
import java.util.*;
 
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
 
import org.springframework.web.bind.annotation.*;
 
@RestController
public class PrecinctController {
 
    @Autowired
    private PrecinctRepo service;
    
    
    // RESTful API methods for Retrieval operations
    @GetMapping("/precincts")
    public List<Precinct> list() {
        System.out.println(service.findAll());
        return service.findAll();
    }
    
    // RESTful API method for Create operation
    @PostMapping("/precincts")
    public void add(@RequestBody Precinct precinct) {
        System.out.println("hi from java");
        System.out.println(precinct);
        service.save(precinct);
    }
    
    // RESTful API method for Update operation
    @PutMapping("/precincts/{id}")
    public ResponseEntity<?> update(@RequestBody Precinct precinct, @PathVariable Integer id) {
        try {
            Precinct existPrecinct = service.findById(id).get();
            precinct.setId(id);
            service.save(precinct);
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