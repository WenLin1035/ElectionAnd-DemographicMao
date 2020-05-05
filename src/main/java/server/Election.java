/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author webst
 */
@Entity
@Table(name="election")
public class Election {
    
    private Integer id;
    private Integer demvotpres;
    private Integer repvotpres;
    private Integer othvotpres;
    private Integer totvotpres;
    private Integer demvot16;
    private Integer repvot16;
    private Integer totvot16;
    private Integer othvot16;
    private Integer demvot18;
    private Integer repvot18;
    private Integer othvot18;
    private Integer totvot18;
    private Precinct precinct;
    
    public Election(){
        super();
    }
    
    private Election(Integer demvotpres, Integer repvotpres, Integer othvotpres, Integer totvotpres, Integer demvot16, Integer repvot16, Integer totvot16, Integer othvot16, Integer demvot18, Integer repvot18, Integer othvot18, Integer totvot18){
        this.demvotpres=demvotpres;
        this.repvotpres=repvotpres;
        this.othvotpres=othvotpres;
        this.totvotpres=totvotpres;
        this.demvot16=demvot16;
        this.repvot16=repvot16;
        this.totvot16=totvot16;
        this.othvot16=othvot16;
        this.demvot18=demvot18;
        this.repvot18=repvot18;
        this.othvot18=othvot18;
        this.totvot18=totvot18;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    @OneToOne(fetch=FetchType.LAZY)
    @MapsId
    @JoinColumn(name="id")
    @JsonBackReference
    public Precinct getPrecinct(){
        return precinct;
    }
    
    @Column(name="demvotpres")
    public Integer getDemvotpres() {
        return demvotpres;
    }

    @Column(name="repvotpres")
    public Integer getRepvotpres() {
        return repvotpres;
    }

    @Column(name="othvotpres")
    public Integer getOthvotpres() {
        return othvotpres;
    }

    @Column(name="totvotpres")
    public Integer getTotvotpres() {
        return totvotpres;
    }

    @Column(name="demvot16")
    public Integer getDemvot16() {
        return demvot16;
    }

    @Column(name="repvot16")
    public Integer getRepvot16() {
        return repvot16;
    }

    @Column(name="totvot16")
    public Integer getTotvot16() {
        return totvot16;
    }

    @Column(name="othvot16")
    public Integer getOthvot16() {
        return othvot16;
    }

    @Column(name="demvot18")
    public Integer getDemvot18() {
        return demvot18;
    }

    @Column(name="repvot18")
    public Integer getRepvot18() {
        return repvot18;
    }

    @Column(name="othvot18")
    public Integer getOthvot18() {
        return othvot18;
    }

    @Column(name="totvot18")
    public Integer getTotvot18() {
        return totvot18;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPrecinct(Precinct precinct){
        this.precinct=precinct;
    }
    
    public void setDemvotpres(Integer demvotpres) {
        this.demvotpres = demvotpres;
    }

    public void setRepvotpres(Integer repvotpres) {
        this.repvotpres = repvotpres;
    }

    public void setOthvotpres(Integer othvotpres) {
        this.othvotpres = othvotpres;
    }

    public void setTotvotpres(Integer totvotpres) {
        this.totvotpres = totvotpres;
    }

    public void setDemvot16(Integer demvot16) {
        this.demvot16 = demvot16;
    }

    public void setRepvot16(Integer repvot16) {
        this.repvot16 = repvot16;
    }

    public void setTotvot16(Integer totvot16) {
        this.totvot16 = totvot16;
    }

    public void setOthvot16(Integer othvot16) {
        this.othvot16 = othvot16;
    }

    public void setDemvot18(Integer demvot18) {
        this.demvot18 = demvot18;
    }

    public void setRepvot18(Integer repvot18) {
        this.repvot18 = repvot18;
    }

    public void setOthvot18(Integer othvot18) {
        this.othvot18 = othvot18;
    }

    public void setTotvot18(Integer totvot18) {
        this.totvot18 = totvot18;
    }
}
