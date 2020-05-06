/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author webst
 */
public interface DistrictsRepo extends JpaRepository<Districts, Integer> {
    List<Districts> findByStatefp(String statefp);
}
