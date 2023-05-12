package ibf2022.batch2.csf.backend.repositories;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${DO_STORAGE_BUCKETNAME}")
	private String bucketName;

	// TODO: Task 3
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Upload file into S3
	public void upload(MultipartFile zipFile) throws IOException {

		Tika tika = new Tika();
		ZipInputStream zis = new ZipInputStream(zipFile.getInputStream());
		ZipEntry zipEntry = zis.getNextEntry();

		// Maintain a map to keep track of file names and their count
		Map<String, Integer> fileNames = new HashMap<>();

		while (zipEntry != null) {
			if (!zipEntry.isDirectory()) {
				// Read the file into a byte array
				byte[] bytes = IOUtils.toByteArray(zis);

				// Determine the content type
				String contentType = tika.detect(bytes, zipEntry.getName());

				// Create metadata with the size and content type
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(bytes.length);
				metadata.setContentType(contentType);

				// Generate a unique name for the file if it exists
				String fileName = zipEntry.getName();
				if (fileNames.containsKey(fileName)) {
					int count = fileNames.get(fileName);
					fileNames.put(fileName, count + 1);
					fileName = (count + 1) + "-" + fileName;
				} else {
					fileNames.put(fileName, 0);
				}

				// Create a put request and upload the file to S3
				PutObjectRequest request = new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(bytes),
						metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead);
				s3Client.putObject(request);
			}

			zipEntry = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();
	} // end of upload()

}
