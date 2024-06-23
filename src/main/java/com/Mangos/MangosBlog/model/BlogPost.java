package com.Mangos.MangosBlog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "blogPost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    LocalDate createdDate = LocalDate.now();
    LocalDate updatedDate = LocalDate.now();


    @NotBlank
    private String blogTitle;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private String externalLink;
    private String topImageUrl;
    private String topImageUUID;

    @OneToMany(mappedBy = "blogId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogComments> comments = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "blog_post_images", joinColumns = @JoinColumn(name = "blog_post_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    public BlogPost(String blogTitle, String description, String content) {
        this.blogTitle = blogTitle;
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
        this.description = description;
        this.content = content;
    }
    public Long getBlogId() {
        return blogId;
    }
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public String getBlogTitle() {

        return blogTitle;
    }
    public void setBlogTitle(String blogTitle) {

        this.blogTitle = blogTitle;
    }

    /*
    public String getExternalLink() {

        return externalLink;
    }
    public void setExternalLink(String externalLink) {

        this.externalLink = externalLink;
    }
*/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void addImageUrl(String imageUrl) {
        this.imageUrls.add(imageUrl);
    }
    public void removeImageUrl(String imageUrl) {
        this.imageUrls.remove(imageUrl);
    }

}
