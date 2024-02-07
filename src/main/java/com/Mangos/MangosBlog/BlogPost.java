package com.Mangos.MangosBlog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;
    private Date datePosted;

    // Constructors, getters and setters

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String newContent) {
        content = newContent;
    }
    
    public String getContent() {
        return content;
    }

    public void setImageUrl(String newImage) {
        imageUrl = newImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setPostDate(Date newPostDate) {
        datePosted = newPostDate;
    }

    public Date getPostDate() {
        return datePosted;
    }

    public long getId() {
        return id;
    }

}
