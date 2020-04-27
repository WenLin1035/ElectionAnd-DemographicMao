/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
/**
 *
 * @author webst
 */

@Entity
@Table(name="PRECINCT")
public class Precinct {
    
    private Integer ogrFID;
    private String shape_geojson;
    private String vtdst;
    private String name;
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
    private Integer aminov18;
    private Integer asianov18;
    private Integer blackov18;
    private Integer hawov18;
    private Integer hisov18;
    private Integer whiteov18;
    private Integer otherov18;
    private Integer pop100;
    private String statefp;
 
    public Precinct(){
        super();
    }
    
    public Precinct(String statefp,String shape_geojson,String vtdst, String name, Integer demvotpres, Integer repvotpres, Integer othvotpres, Integer totvotpres, Integer demvot16, Integer repvot16, Integer totvot16, Integer othvot16, Integer demvot18, Integer repvot18, Integer othvot18, Integer totvot18, Integer aminov18, Integer asianov18, Integer blackov18, Integer hawov18, Integer hisov18, Integer whiteov18, Integer otherov18, Integer pop100) {
        this.statefp=statefp;
        this.shape_geojson=shape_geojson;
        this.vtdst=vtdst;
        this.name=name;
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
        this.aminov18=aminov18;
        this.asianov18=asianov18;
        this.blackov18=blackov18;
        this.hawov18=hawov18;
        this.hisov18=hisov18;
        this.whiteov18=whiteov18;
        this.otherov18=otherov18;
        this.pop100=pop100;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OGR_FID")
    public Integer getOgrFID(){
        return ogrFID;
    }
    
    @Column(name="statefp")
    public String getStatefp(){
        return statefp;
    }
    
    @Column(name="vtdst")
    public String getVtdst() {
        return vtdst;
    }

    @Column(name="shape_geojson")
    public String getShape_geojson() {
        return shape_geojson;
    }
    
    @Column(name="name")
    public String getName() {
        return name;
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

    @Column(name="aminov18")
    public Integer getAminov18() {
        return aminov18;
    }

    @Column(name="asianov18")
    public Integer getAsianov18() {
        return asianov18;
    }

    @Column(name="blackov18")
    public Integer getBlackov18() {
        return blackov18;
    }

    @Column(name="hawov18")
    public Integer getHawov18() {
        return hawov18;
    }

    @Column(name="hisov18")
    public Integer getHisov18() {
        return hisov18;
    }

    @Column(name="whiteov18")
    public Integer getWhiteov18() {
        return whiteov18;
    }

    @Column(name="otherov18")
    public Integer getOtherov18() {
        return otherov18;
    }

    @Column(name="pop100")
    public Integer getPop100() {
        return pop100;
    }

    public void setStatefp(String statefp){
        this.statefp=statefp;
    }
    
    public void setVtdst(String vtdst) {
        this.vtdst = vtdst;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setAminov18(Integer aminov18) {
        this.aminov18 = aminov18;
    }

    public void setAsianov18(Integer asianov18) {
        this.asianov18 = asianov18;
    }

    public void setBlackov18(Integer blackov18) {
        this.blackov18 = blackov18;
    }

    public void setHawov18(Integer hawov18) {
        this.hawov18 = hawov18;
    }

    public void setHisov18(Integer hisov18) {
        this.hisov18 = hisov18;
    }

    public void setWhiteov18(Integer whiteov18) {
        this.whiteov18 = whiteov18;
    }

    public void setOtherov18(Integer otherov18) {
        this.otherov18 = otherov18;
    }

    public void setPop100(Integer pop100) {
        this.pop100 = pop100;
    }

    public void setShape_geojson(String shape_geojson) {
        this.shape_geojson = shape_geojson;
    }
    
    public void setOgrFID(Integer ogrFID){
        this.ogrFID=ogrFID;
    }
}
