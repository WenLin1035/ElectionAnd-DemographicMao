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
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 

public interface PrecinctRepo extends JpaRepository<Precincts, Integer> {
    List<Precincts> findAll();
    List<Precincts> findByStatefp(String id);
    Precincts findByName(String name);
    List<Precincts> findByNameStartingWithIgnoreCaseAndStatefp(String partOfName, String statefp);
    List<Precincts> findByStatefpAndErrorIsNotNull(String statefp);
}