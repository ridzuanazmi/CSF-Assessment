package ibf2022.batch2.csf.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class AppConfig {
    
    @Value("${MONGO_URL}")
    private String mongoUrl;

    @Value("${DO_STORAGE_KEY}")
    private String accessKey;

    @Value("${DO_STORAGE_SECRETKEY}")
    private String secretKey;

    @Value("${DO_STORAGE_ENDPOINT}")
    private String endPoint;

    @Value("${DO_STORAGE_ENDPOINT_REGION}")
    private String endPointRegion;

    @Bean
    public AmazonS3 createS3Client() {
        
        BasicAWSCredentials cred =
                new BasicAWSCredentials(accessKey, secretKey);
        EndpointConfiguration ep = 
                new EndpointConfiguration(endPoint, endPointRegion);

        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(ep)
            .withCredentials(new AWSStaticCredentialsProvider(cred))
            .build();
    }

    @Bean
    public MongoTemplate createMongoTemplate() {
        // Create a MongoClient
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, "csfass"); // REMEMBER TO CHANGE THE DATABASE NAME
    }
}
