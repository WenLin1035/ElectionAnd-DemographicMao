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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 

public interface PrecinctRepo extends JpaRepository<Precinct, Integer> {
    List<Precinct> findAll();
    @Query(value="select e.precinct_id from error e join precinct p WHERE p.OGR_FID=e.precinct_id",nativeQuery=true)
    List<String> finder();
    List<Precinct> findByStatefp(String id);
    Precinct findByName(String name);
    Precinct findByOgrFID(Integer id);
}