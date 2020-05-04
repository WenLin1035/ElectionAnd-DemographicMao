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
 

public interface PrecinctRepo extends JpaRepository<Precinct, Integer> {
    List<Precinct> findAll();
    List<Precinct> findByStatefp(String id);
    Precinct findByName(String name);
    Precinct findByOgrFID(Integer id);
    List<Precinct> findByNameStartingWithIgnoreCaseAndStatefp(String partOfName, String statefp);
    List<Precinct> findByStatefpAndErrorIsNotNull(String statefp);
}