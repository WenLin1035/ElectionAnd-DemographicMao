/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author webst
 */


@Entity
@Table(name="neighbors")
public class Neighbors {
    private Integer id;
    private Precincts firstPrecinct;
    private Precincts secondPrecinct;
    
    public Neighbors(){
        super();
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    @ManyToOne()
    @JoinColumn(name="first_precinct_id")
    @JsonBackReference
    public Precincts getFirstPrecinct(){
        return firstPrecinct;
    }
    
    @OneToOne()
    @JoinColumn(name="second_precinct_id")
    @JsonManagedReference
    public Precincts getSecondPrecinct(){
        return secondPrecinct;
    }
    
    public void setFirstPrecinct(Precincts firstPrecinct){
        this.firstPrecinct=firstPrecinct;
    }
    
    public void setSecondPrecinct(Precincts secondPrecinct){
        this.secondPrecinct=secondPrecinct;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
}
