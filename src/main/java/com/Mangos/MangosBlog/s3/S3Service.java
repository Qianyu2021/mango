package com.Mangos.MangosBlog.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.net.URL;

@Service
public class S3Service {

    private final S3Client s3;

    public S3Service(S3Client s3) {
        this.s3 = s3;
    }

    public void uploadFile(String bucketName, String key, byte[] file) {
        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .acl("public-read") //make it public readable
                    .build();

            s3.putObject(objectRequest, RequestBody.fromBytes(file));
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    public URL getimageUrl(String bucketName, String keyName) {
        try {
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(keyName)
                    .build();

            return s3.utilities().getUrl(request);
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return null;
        }
    }

    public void putObject(String blogPost, String image, byte[] bytes) {
    }
}





//
//
//    public void putObject(String bucketName, String key, byte[] file) {
//        PutObjectRequest objectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//        s3.putObject(objectRequest, RequestBody.fromBytes(file));
//    }
//
//    //this is to download the file
//    public byte[] getObject(String bucketName, String key) {
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        ResponseInputStream<GetObjectResponse> response= s3.getObject(getObjectRequest);
////getting the object
//        try {
//            return response.readAllBytes();
//        } catch (IOException e){
//            throw new RuntimeException(e);
//        }
//    }

//}
