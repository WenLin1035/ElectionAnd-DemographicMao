/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author webst
 */

@Entity
@Table(name="PRECINCT")
public class Precinct {
    
    private Error error;
    private Demographic demographic;
    private Election election;
    private Integer id;
    private String shape_geojson;
    private String name;
    private String statefp;
 
    public Precinct(){
        super();
    }
    
    public Precinct(String statefp,String shape_geojson, String name) {
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
    public Error getError(){
        return error;
    }
    
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public Demographic getDemographic(){
        return demographic;
    }
    
    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="precinct")
    @JsonManagedReference 
    public Election getElection(){
        return election;
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

    public void setStatefp(String statefp){
        this.statefp=statefp;
    }
    
    public void setError(Error error){
        this.error=error;
    }
    
    public void setDemographic(Demographic demographic){
        this.demographic=demographic;
    }
    
    public void setElection(Election election){
        this.election=election;
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
