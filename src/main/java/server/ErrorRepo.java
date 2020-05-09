/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author webst
 */
public interface ErrorRepo extends JpaRepository<Errors, Integer> {
    //A Native Query means it is a SQL statement, normally by default it is JPQL Queries which can only perform CRUD queries.
    //The : binds the variable stateFP to the param stateFP to dynamically load errors from specific states

}
