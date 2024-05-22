package com.Mangos.MangosBlog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotBlank(message = "username must not be blank")
    private String commentUsername = "anonymous";

    @NotBlank(message = "content must not be blank")
    @Size(min = 2, max = 300, message = "content must contain between 3 to 300 characters")
    private String commentContent;
    private String commentTile;


    @Column(name = "blogId", nullable = false)
    private Long blogId;

    private LocalDateTime createdAt;

    public BlogComments(String commentUsername, String commentContent, Long blogId, String commentTile){
        this.createdAt = LocalDateTime.now();
        this.commentContent = commentContent;
        this.blogId = blogId;
        this.commentUsername = commentUsername;
        this.commentTile = blogId.toString();
    }

    //getter and setter

    public Long getId(){
        return commentId;
    }

    public void setId(Long commentId){
        this.commentId = commentId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setCommentUsername(String username){
        this.commentUsername = username;
    }


    public void setCommentUserName(String commentUserName){
        if(commentUserName == null ||commentUserName.isBlank()){
            this.commentUsername = "anonymous";
        } else {
            this.commentUsername = commentUserName;
        }
    }
    public String getCommentUsername(){
        return this.commentUsername;
    }

    public String getContent(){
        return this.commentContent;
    }
    public void setContent(String commentContent){
        this.commentContent = commentContent;
    }

    public Long getCommentId(){
        return this.commentId;
    }
    public void setCommentId(Long commentId){
        this.commentId = commentId;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreateDateTime(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

}
