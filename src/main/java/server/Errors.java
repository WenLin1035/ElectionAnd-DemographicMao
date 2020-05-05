/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author webst
 */
@Entity
@Table(name="errors")
public class Errors {
    
    private Integer id;
    private String errorType;
    private String comment;
    private Timestamp commentTime;
    private Precincts precinct;
    
    public Errors(){
        super();
    }
    
    public Errors(Timestamp commentTime, String errorType, String comment){
        this.errorType=errorType;
        this.comment=comment;
        this.commentTime=commentTime;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }
    
    @OneToOne()
    @MapsId
    @JoinColumn(name="id")
    @JsonBackReference
    public Precincts getPrecinct(){
        return precinct;
    }
    
    @Column(name="comment_time")
    public Timestamp getCommentTime(){
        return commentTime;
    }

    @Column(name="error_type")
    public String getErrorType() {
        return errorType;
    }

    @Column(name="comment")
    public String getComment() {
        return comment;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrecinct(Precincts precinct){
        this.precinct=precinct;
    }
    
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public void setCommentTime(Timestamp commentTime){
        this.commentTime=commentTime;
    }
    
}
