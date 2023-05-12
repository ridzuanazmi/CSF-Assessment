package ibf2022.batch2.csf.backend.repositories;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class ImageRepository {

	@Autowired
    private AmazonS3 s3Client;

    @Value("${DO_STORAGE_BUCKETNAME}")
    private String bucketName;

	//TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
    // Upload file into S3
    public String upload(MultipartFile zipFile) throws IOException {

        // Create a map to pass in the metadata in the object later
        Map<String, String> userData = new HashMap<>();
        userData.put("name", "Ridzy");
        userData.put("uploadDateTime", LocalDateTime.now().toString());
        userData.put("originalFileName", zipFile.getOriginalFilename());

        // Set metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(zipFile.getContentType());
        metadata.setContentLength(zipFile.getSize());
        metadata.setUserMetadata(userData);

        // Generate a UUID 
        String objectKey = UUID.randomUUID().toString().substring(0, 8);
        System.out.println(">>>>uploadS3(): " + zipFile.getOriginalFilename());

        // The file extension of the uploaded file is extracted using a StringTokenizer.
        // If the file extension is "blob", it is assumed to be a PNG image, and the
        // extension is changed to "blob.png"
        StringTokenizer tk = new StringTokenizer(zipFile.getOriginalFilename(), ".");
        int count = 0;
        String filenameExt = "";
        while (tk.hasMoreTokens()) {
            if (count == 1) {
                filenameExt = tk.nextToken();
                break;
            } else {
                filenameExt = tk.nextToken();
                count++;
            }
        }
        if (filenameExt.equals("blob"))
            filenameExt = filenameExt + ".png";

        PutObjectRequest putRequest = new PutObjectRequest(
            bucketName, "myobject%s.%s".formatted(objectKey, filenameExt), 
            zipFile.getInputStream(), metadata);
        
        putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
        // The S3 client is used to upload the file to the S3 bucket with the putObject method, using the putRequest object created earlier
        s3Client.putObject(putRequest); 
        // returns a String representing the unique object key of the uploaded file in the S3 bucket, 
        // with the format "myobject%s.%s", where %s placeholders are replaced 
        // by the generated unique key and the file extension.
        return "myobject%s.%s".formatted(objectKey, filenameExt);

    } // end of uploadS3()

}
