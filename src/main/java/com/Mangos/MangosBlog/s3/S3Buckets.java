package com.Mangos.MangosBlog.s3;


import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "aws.s3.buckets")
public class S3Buckets {

    private String bucketName="blogpicture";
    public String getBlog() {
        return bucketName;
    }

    public void setBlogPost(String bucketName) {
        this.bucketName = bucketName;
    }

}
