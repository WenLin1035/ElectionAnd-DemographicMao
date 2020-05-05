/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author webst
 */
@RestController
public class StateController {
    @Autowired
    private StateRepo service;
    
    @GetMapping("/states")
    public List<States> findAllStates() {
        return service.findAll();
    }
}
