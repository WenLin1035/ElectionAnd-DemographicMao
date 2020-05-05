/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author webst
 */
@Entity
@Table(name="error")
public class Error {
    
    private Integer id;
    private String errorType;
    private String comment;
    private Timestamp commentTime;
    
    public Error(){
        super();
    }
    
    public Error(Timestamp commentTime, String errorType, String comment){
        this.errorType=errorType;
        this.comment=comment;
        this.commentTime=commentTime;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
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
