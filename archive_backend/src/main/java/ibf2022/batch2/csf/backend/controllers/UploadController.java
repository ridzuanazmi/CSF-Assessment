package ibf2022.batch2.csf.backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadController {

	// TODO: Task 2, Task 3, Task 4
	// POST /upload
	// Content-Type: multipart/form-data
	// Accepts: application/json
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> handleFileUpload(
			@RequestParam("name") String name,
			@RequestParam("title") String title,
			@RequestParam("comments") String comments,
			@RequestPart("archive") MultipartFile archive) {

		Map<String, String> responseMessage = new HashMap<>();
		responseMessage.put("name", name);
		responseMessage.put("title", title);
		responseMessage.put("comments", comments);
		responseMessage.put("archive", archive.getOriginalFilename());

		return ResponseEntity.ok().body(responseMessage);
	}

	// TODO: Task 5

	// TODO: Task 6

}
