/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author webst
 */

@Entity
@Table(name="precincts")
public class Precincts {
    
    private Errors error;
    private Demographics demographic;
    private List<Elections> elections;
    private Integer id;
    private String shape_geojson;
    private String name;
    private String statefp;
    private List<Neighbors> neighbors;
    private Neighbors neighbor;
 
    public Precincts(){
        super();
    }
    
    public Precincts(String statefp,String shape_geojson, String name) {
        this.statefp=statefp;
        this.shape_geojson=shape_geojson;
        this.name=name;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId(){
        return id;
    }
    
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public Errors getError(){
        return error;
    }
    
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public Demographics getDemographic(){
        return demographic;
    }
    
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public List<Elections> getElections(){
        return elections;
    }
    
    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="firstPrecinct")
    @JsonManagedReference 
    public List<Neighbors> getNeighbors(){
        return neighbors;
    }
    
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="secondPrecinct")
    @JsonBackReference 
    public Neighbors getNeighbor(){
        return neighbor;
    }
    
    @Column(name="statefp")
    public String getStatefp(){
        return statefp;
    }

    @Column(name="shape_geojson")
    public String getShape_geojson() {
        return shape_geojson;
    }
    
    @Column(name="name")
    public String getName() {
        return name;
    }
    
    public void setNeighbors(List<Neighbors> neighbors){
        this.neighbors=neighbors;
    }
    
    public void setNeighbor(Neighbors neighbor){
        this.neighbor=neighbor;
    }

    public void setStatefp(String statefp){
        this.statefp=statefp;
    }
    
    public void setError(Errors error){
        this.error=error;
    }
    
    public void setDemographic(Demographics demographic){
        this.demographic=demographic;
    }
    
    public void setElections(List<Elections> elections){
        this.elections=elections;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShape_geojson(String shape_geojson) {
        this.shape_geojson = shape_geojson;
    }
    
    public void setId(Integer id){
        this.id=id;
    }
}
