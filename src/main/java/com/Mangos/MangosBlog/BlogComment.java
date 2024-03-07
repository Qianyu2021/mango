package com.Mangos.MangosBlog;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; // // Optional: An anonymous user might choose to leave a name
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedAt = new Date();

    public BlogComment(){

    }

    //constructor with parameters
    public BlogComment(String username, String conent){
        this.username = username;
        this.content = conent;
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getContent(){
        return content;
    }

    public java.util.Date getPostedAt() {
        return postedAt;
    }
    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setPostedAt(Date postedAt){
        this.postedAt = postedAt;
    }

}
