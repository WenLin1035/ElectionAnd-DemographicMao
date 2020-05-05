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
import java.sql.Timestamp;
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
    public List<Precincts> findAllPrecincts() {
        List<Precincts> allPrecincts=service.findAll();
        for(Precincts p: allPrecincts){
            p.setDemographic(null);
            p.setError(null);
            p.setElections(null);
            p.setNeighbors(null);
        }
        return allPrecincts;
    }
    
    @GetMapping("/precincts/search/{partOfName}/{statefp}")
    public ResponseEntity<List<String>> findAllNamesForSearch(@PathVariable String partOfName,@PathVariable String statefp){
        List<Precincts> precincts=service.findByNameStartingWithIgnoreCaseAndStatefp(partOfName,statefp);
        List<String> precinctNames=new ArrayList<String>();
        int maxCount=0;
        for(Precincts p: precincts){
            if(maxCount<5){
                precinctNames.add(p.getName());
                maxCount++;
            }
        }
        if(precinctNames.size()==0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(precinctNames,HttpStatus.FOUND);
    }
    
    @GetMapping("/precincts/error/{statefp}")
    public List<Precincts> findErrorsForPrecincts(@PathVariable String statefp){
        List<Precincts> precincts=service.findByStatefpAndErrorIsNotNull(statefp);
        for(Precincts p:precincts){
            p.setShape_geojson(null);
            p.setDemographic(null);
            p.setElections(null);
        }
        return precincts;
    }
    
    @GetMapping("/precincts/demographic/{id}")
    public Demographics findDemographicForPrecincts(@PathVariable String id){
        Precincts precinct=service.findById(Integer.parseInt(id)).get();
        return precinct.getDemographic();
    }
    
    @GetMapping("/precincts/election/{id}")
    public List<Elections> findElectionForPrecincts(@PathVariable String id){
        Precincts precinct=service.findById(Integer.parseInt(id)).get();
        return precinct.getElections();
    }
    
    @GetMapping("/precincts/neighbors/{id}")
    public List<Neighbors> findNeighborsForPrecincts(@PathVariable String id){
        Precincts precinct=service.findById(Integer.parseInt(id)).get();
        List<Neighbors> neighbors=precinct.getNeighbors();
        for(Neighbors neighbor:neighbors){
            Precincts neighborPrecinct=neighbor.getSecondPrecinct();
            neighborPrecinct.setDemographic(null);
            neighborPrecinct.setError(null);
            neighborPrecinct.setElections(null);
            neighborPrecinct.setNeighbors(null);
            neighborPrecinct.setShape_geojson("");
        }
        return neighbors;
    }
    
    @GetMapping("/precincts/{id}")
    public List<Precincts> findPrecinctsInState(@PathVariable String id){
        return service.findByStatefp(id);
    }
    
    // RESTful API method for Update operation
    @PutMapping("/precincts/{id}")
    public void update(@RequestBody Precincts precinct, @PathVariable String id) {
        try {
            Precincts existPrecinct = service.findById(Integer.parseInt(id)).get();
            precinct.setId(Integer.parseInt(id));
            if(precinct.getDemographic()==null){
                precinct.setDemographic(existPrecinct.getDemographic());
            }
            if(precinct.getElections()==null){
                precinct.setElections(existPrecinct.getElections());
            }
            if(precinct.getError()==null){
                precinct.setError(existPrecinct.getError());
            }
            else{
                if(precinct.getError().getCommentTime()==null){
                    precinct.getError().setCommentTime(new Timestamp(System.currentTimeMillis()));
                }
            }
            service.save(precinct);
        } catch (NoSuchElementException e) {
        }      
    }
    
    // RESTful API method for Create operation
    @PostMapping("/precincts")
    public void add(@RequestBody Precincts precinct) {
        System.out.println("hi from java");
        System.out.println(precinct);
        //service.save(precinct);
    }
    
    @Transactional
    @PutMapping("/merge/{name1}/{name2}")
    public ResponseEntity<?> merge(@RequestBody Precincts newPrecinct,@PathVariable String enclosingPrecinctID, @PathVariable String enclosedPrecinctID){
        //TO-DO
        try {
            //delete precinct 1
            //delete precinct 2
            //add new precinct
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