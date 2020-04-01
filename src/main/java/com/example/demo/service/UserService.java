package com.example.demo.service;


import org.springframework.stereotype.Service;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

@Service
public class UserService {




    @Transactional
    public List<String> compare (String pic1, String pic2,String pic3){
        String photo1 = pic1;
        String photo2 = pic2;
        String photo3 = pic3;
        String bucket = "hoangle-facecompare";

        List<String> out = new ArrayList<>();
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        CompareFacesRequest compareFacesRequest = new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo1).withBucket(bucket))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo2).withBucket(bucket))).withSimilarityThreshold(10F);

        try {

            CompareFacesResult result= rekognitionClient.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists= result.getFaceMatches();

            out.add("Compare " + photo1+ " and "+photo2);
            if(!lists.isEmpty()){
                for (CompareFacesMatch label: lists) {
                    out.add("Similarity is " + label.getSimilarity().toString());

                }
            }else{
                out.add("Faces Does not match");
            }
        } catch(AmazonRekognitionException e) {
            e.printStackTrace();
        }



        compareFacesRequest= new CompareFacesRequest().withSourceImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo1).withBucket(bucket))).withTargetImage(new Image()
                .withS3Object(new S3Object()
                        .withName(photo3).withBucket(bucket))).withSimilarityThreshold(0F);

        try {

            CompareFacesResult result= rekognitionClient.compareFaces(compareFacesRequest);
            List<CompareFacesMatch> lists= result.getFaceMatches();

            out.add("Compare " + photo1+ " and "+photo3);
            if(!lists.isEmpty()){
                for (CompareFacesMatch label: lists) {
                    out.add("Similarity is " + label.getSimilarity().toString());

                }
            }else{
                out.add("Faces Does not match");
            }
        } catch(AmazonRekognitionException e) {
            e.printStackTrace();
        }




        return out;
    }
}
