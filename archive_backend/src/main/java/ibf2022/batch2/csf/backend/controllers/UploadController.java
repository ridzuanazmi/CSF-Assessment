package ibf2022.batch2.csf.backend.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.Archive;
import ibf2022.batch2.csf.backend.services.UploadService;

@RestController
public class UploadController {

	@Autowired
	private UploadService uploadSrvc;

	// TODO: Task 2, Task 3, Task 4
	// POST /upload
	// Content-Type: multipart/form-data
	// Accepts: application/json
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> handleFileUpload(
			@RequestParam("name") String name,
			@RequestParam("title") String title,
			@RequestParam("comments") String comments,
			@RequestPart("archive") MultipartFile archive) throws IOException {

		Map<String, String> responseMessage = new HashMap<>();
		try {
			// Upload the archive and get the resulting bundle
			Archive bundle = this.uploadSrvc.upload(archive, title, name, comments);

			// Save the data in MongoDB
			this.uploadSrvc.recordBundle(bundle);

			// Add the bundleId to the response
			responseMessage.put("bundleId", bundle.getBundleId());

			return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
		} catch (Exception e) {
			// Log the exception here with a logging framework like SLF4J, Log4j, etc.
			// log.error("Failed to upload file", e);

			// If an error occurs, return a 500 response with the error message
			responseMessage.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	// TODO: Task 5
	// GET /bundle/<bundleId>
	@GetMapping(path = "/bundle/{bundleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBundle(@PathVariable String bundleId) {

		Optional<Archive> optArchive = uploadSrvc.getBundleByBundleId(bundleId);

        if (optArchive.isPresent()) {
            return ResponseEntity.ok(optArchive.get());
        } else {
            Map<String, String> responseMessage = new HashMap<>();
            responseMessage.put("error", "Bundle not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
	}

	// TODO: Task 6

}
