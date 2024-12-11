package com.signdocs.signature;


import com.signdocs.document.DocumentRepository;
import com.signdocs.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signatures")
public class SignatureController {


  private static final Logger log = LoggerFactory.getLogger(SignatureController.class);


  @Autowired
  private SignatureRepository signatureRepository;

  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<String> saveSignature(@RequestBody SignatureEntity signature) {
    signatureRepository.save(signature);
    return ResponseEntity.ok("Signature saved successfully!");
  }

  @Operation(summary = "Get signature Details", description = "Fetches details for the specified signature by signatureId")
  @GetMapping("/{signatureId}")
  public ResponseEntity<?> getSignature(@PathVariable Long signatureId) {
    log.debug("signature get api was called with id  : {}", signatureId);
    return ResponseEntity.ok(signatureRepository.findById(signatureId));
  }


}

