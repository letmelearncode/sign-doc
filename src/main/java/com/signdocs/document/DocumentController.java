package com.signdocs.document;


import com.signdocs.common.DocumentUploadRequest;
import com.signdocs.user.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

  private static final Logger log = LoggerFactory.getLogger(DocumentController.class);


  @Autowired
  private DocumentRepository documentRepository;


  @Operation(summary = "Get document Details", description = "Fetches details for the specified signature by signatureId")
  @GetMapping("/{documentId}")
  public ResponseEntity<?> getDocument(@PathVariable Long documentId) {
    log.debug("document get api was called with id  : {}", documentId);
    return ResponseEntity.ok(documentRepository.findById(documentId));
  }


  @PostMapping("/upload")
  public ResponseEntity<String> uploadDocument(@RequestParam("file") MultipartFile file,
      @RequestParam("ownerId") Long ownerId, @RequestParam("sharedWith") List<Long> sharedWith) {
    if (file.isEmpty()) {
      return new ResponseEntity<>("Please upload a valid document.", HttpStatus.BAD_REQUEST);
    }

    try {
      DocumentEntity document = new DocumentEntity(
          file.getName(), LocalDateTime.now(), sharedWith.stream().map(a -> new UserEntity(a, null, null)).toList()
          , new UserEntity(ownerId, null, null),
          file.getBytes(), file.getSize());

      // Store the file's binary content

      documentRepository.save(document);  // Save the file to the database

      return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
    } catch (IOException e) {
      return new ResponseEntity<>("File upload failed: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
