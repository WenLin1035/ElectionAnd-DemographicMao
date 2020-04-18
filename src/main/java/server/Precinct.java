/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author webst
 */

@Entity
public class Precinct {

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamelegal() {
        return namelegal;
    }

    public void setNamelegal(String nameLegal) {
        this.namelegal = nameLegal;
    }

    public String getNamenick() {
        return namenick;
    }

    public void setNamenick(String nameNick) {
        this.namenick = nameNick;
    }
    private Integer id;
    private String namelegal;
    private String namenick;
    private Integer g18ussdwhi;
 
    public Precinct() {
    }
 
    public Precinct(Integer id,String nameLegal, String nameNick, Integer g18ussdwhi) {
        this.id=id;
        this.namelegal = nameLegal;
        this.namenick = nameNick;
        this.g18ussdwhi = g18ussdwhi;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    public String toString(){
        return "Precinct: {id: "+id+", namelegal: "+namelegal+", namenick: "+namenick+", G18USSDWHI: "+getG18ussdwhi()+"}";
    }

    public Integer getG18ussdwhi() {
        return g18ussdwhi;
    }

    public void setG18ussdwhi(Integer g18ussdwhi) {
        this.g18ussdwhi = g18ussdwhi;
    }
   
}
