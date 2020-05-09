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
    private PrecinctRepo precinctService;
    @Autowired
    private NeighborsRepo neighborService;
    @Autowired
    private DistrictsRepo districtService;
    @Autowired
    private FixedErrorsRepo fixedErrorsService;
    @Autowired
    private ErrorRepo unfixedErrorsService;
    
    // RESTful API methods for Retrieval operations
    @GetMapping("/precincts")
    public List<Precincts> findAllPrecincts() {
        List<Precincts> allPrecincts=precinctService.findAll();
        for(Precincts p: allPrecincts){
            p.setDemographic(null);
            p.setError(null);
            p.setElections(null);
            p.setNeighbors(null);
        }
        return allPrecincts;
    }
    
    @GetMapping("/precincts/{id}")
    public Precincts findPrecinct(@PathVariable String id) {
        Precincts precinct=precinctService.findById(Integer.parseInt(id)).get();
        return precinct;
    }
    
    @GetMapping("/cong/{statefp}")
    public List<Districts> getAllCongsForState(@PathVariable String statefp){
        return districtService.findByStatefp(statefp);
    }
    
    @GetMapping("/precincts/search/{partOfName}/{statefp}")
    public ResponseEntity<List<Precincts>> findAllNamesForSearch(@PathVariable String partOfName,@PathVariable String statefp){
        List<Precincts> precincts=precinctService.findByNameStartingWithIgnoreCaseAndStatefp(partOfName,statefp);
        List<Precincts> smallPrecincts=new ArrayList<Precincts>();
        int maxCount=0;
        for(Precincts p: precincts){
            if(maxCount<5){
                p.setShape_geojson(null);
                p.setDemographic(null);
                p.setElections(null);
                p.setNeighbors(null);
                p.setError(null);
                smallPrecincts.add(p);
                maxCount++;
            }
        }
        if(smallPrecincts.size()==0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(smallPrecincts,HttpStatus.FOUND);
    }
    
    @GetMapping("/precincts/error/{statefp}")
    public List<Precincts> findErrorsForPrecincts(@PathVariable String statefp){
        List<Precincts> precincts=precinctService.findByStatefpAndErrorIsNotNull(statefp);
        for(Precincts p:precincts){
            p.setShape_geojson(null);
            p.setDemographic(null);
            p.setElections(null);
            p.setNeighbors(null);
        }
        return precincts;
    }
    
    @PostMapping("/fixederrors")
    public void setFixedError(@RequestBody FixedErrors error){
        error.setCommentTime(new Timestamp(System.currentTimeMillis()));
        fixedErrorsService.save(error);
    }
    
    @GetMapping("/precincts/neighbors/{id}")
    public List<Neighbors> findNeighborsForPrecincts(@PathVariable String id){
        Precincts precinct=precinctService.findById(Integer.parseInt(id)).get();
        List<Neighbors> neighbors=precinct.getNeighbors();
        //check if the precinct has no neighbor
        if(neighbors.size()<1){
            //if there is no neighbors find all precincts that said that our precinct is a neighbor
            List<Neighbors> findNeighbors=neighborService.findBySecondPrecinct(precinct);
            //for all those neighbors switch the edge and set everything unimportant to null
            for(Neighbors findNeighbor:findNeighbors){
                Precincts firstPrecinct=findNeighbor.getFirstPrecinct();
                firstPrecinct.setDemographic(null);
                firstPrecinct.setError(null);
                firstPrecinct.setElections(null);
                firstPrecinct.setNeighbors(null);
                firstPrecinct.setShape_geojson("");
                findNeighbor.setSecondPrecinct(firstPrecinct);
            }
            return findNeighbors;
        }
        else{
            //if there are neighbors, for all neighbors set everything unimportant to null
            for(Neighbors neighbor:neighbors){
                Precincts neighborPrecinct=neighbor.getSecondPrecinct();              
                neighborPrecinct.setDemographic(null);
                neighborPrecinct.setError(null);
                neighborPrecinct.setElections(null);
                neighborPrecinct.setNeighbors(null);
                neighborPrecinct.setShape_geojson("");
            }
            List<Neighbors> findNeighbors=neighborService.findBySecondPrecinct(precinct);
            //for all those neighbors switch the edge and set everything unimportant to null
            for(Neighbors findNeighbor:findNeighbors){
                Precincts firstPrecinct=findNeighbor.getFirstPrecinct();
                firstPrecinct.setDemographic(null);
                firstPrecinct.setError(null);
                firstPrecinct.setElections(null);
                firstPrecinct.setNeighbors(null);
                firstPrecinct.setShape_geojson("");
                findNeighbor.setSecondPrecinct(firstPrecinct);
                neighbors.add(findNeighbor);
            }
        }

        return neighbors;
    }
    
    @GetMapping("/precincts/{statefp}/{districtid}/{countyname}")
    public List<Precincts> findPrecinctsInCong(@PathVariable String statefp,@PathVariable String districtid,@PathVariable String countyname){
        List<Precincts> allPrecincts=precinctService.findByStatefpAndDistrictidAndCountyname(statefp,districtid,countyname);        
        for(Precincts p: allPrecincts){
            p.setError(null);
            p.setNeighbors(null);
        }
        return allPrecincts;
    }
    
    @GetMapping("/precincts/counties/{statefp}/{districtid}")
    public List<String> findCountiesInCong(@PathVariable String statefp,@PathVariable String districtid){
        return precinctService.getAllCounties(statefp,districtid);
    }
    
    // RESTful API method for Update operation
    @PutMapping("/precincts/{id}")
    public void update(@RequestBody Precincts precinct, @PathVariable String id) {
        try {
            Precincts existPrecinct = precinctService.findById(Integer.parseInt(id)).get();
            precinct.setId(Integer.parseInt(id));
            for(Elections e:precinct.getElections()){
                e.setPrecinct(precinct);
            }
            for(Neighbors n:precinct.getNeighbors()){
                if(n.getFirstPrecinct()==null){
                    n.setFirstPrecinct(precinct);
                }
                else{
                    n.setSecondPrecinct(precinct);
                }
            }
            precinctService.save(precinct);
            if(precinct.getError()!=null){
                if(precinct.getError().getErrorType().equals("RESOLVED")){
                    unfixedErrorsService.deleteById(precinct.getId());
                }
            }
        } catch (NoSuchElementException e) {
        }      
    }
    
    @PostMapping("/addNeighbors/{id1}/{id2}")
    public void addNeighbor(@PathVariable String id1, @PathVariable String id2){
        Precincts firstPrecinct=precinctService.findById(Integer.parseInt(id1)).get();
        Precincts secondPrecinct=precinctService.findById(Integer.parseInt(id2)).get();
        Neighbors newNeighbor=new Neighbors();
        newNeighbor.setFirstPrecinct(firstPrecinct);
        newNeighbor.setSecondPrecinct(secondPrecinct);
        neighborService.save(newNeighbor);
    }
    
    // RESTful API method for Delete operation
    @DeleteMapping("/deleteNeighbors/{id1}/{id2}")
    public void deleteNeighbor(@PathVariable String id1, @PathVariable String id2) {
        Precincts firstPrecinct=precinctService.findById(Integer.parseInt(id1)).get();
        Precincts secondPrecinct=precinctService.findById(Integer.parseInt(id2)).get();
        Neighbors toDelete=neighborService.findByFirstPrecinctAndSecondPrecinct(firstPrecinct, secondPrecinct);
        if(toDelete==null){
            toDelete=neighborService.findByFirstPrecinctAndSecondPrecinct(secondPrecinct, firstPrecinct);
            System.out.println(toDelete.getId());
            neighborService.deleteById(toDelete.getId());
        }
        else{
            neighborService.deleteById(toDelete.getId());
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
    public ResponseEntity<?> merge(@RequestBody Precincts newPrecinct, @PathVariable String enclosingPrecinctID, @PathVariable String enclosedPrecinctID){
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
        precinctService.deleteById(id);
    }
}