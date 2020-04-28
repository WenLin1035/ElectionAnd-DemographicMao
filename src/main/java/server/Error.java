/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.persistence.*;

/**
 *
 * @author webst
 */
@Entity
@Table(name="error")
public class Error {
    private Integer errorID;
    private Integer precinctID;
    private String errorType;
    private String comment;
    
    public Error(){
        super();
    }
    
    public Error(Integer errorID, Integer precinctID, String errorType, String comment){
        this.errorID=errorID;
        this.precinctID=precinctID;
        this.errorType=errorType;
        this.comment=comment;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="error_id")
    public Integer getErrorID() {
        return errorID;
    }

    @Column(name="precinct_id")
    public Integer getPrecinctID() {
        return precinctID;
    }

    @Column(name="error_type")
    public String getErrorType() {
        return errorType;
    }

    @Column(name="comment")
    public String getComment() {
        return comment;
    }

    public void setErrorID(Integer errorID) {
        this.errorID = errorID;
    }

    public void setPrecinctID(Integer precinctID) {
        this.precinctID = precinctID;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
