package com.signdocs.user;

import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);


  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
    UserEntity savedUser = userRepository.saveAndFlush(user);
    return ResponseEntity.ok(savedUser);
  }

  @Operation(summary = "Get user Details", description = "Fetches details for the specified user by userId")
  @GetMapping("/{userId}")
  public ResponseEntity<?> getUser(@PathVariable Long userId) {
    log.debug("document get api was called with id  : {}", userId);
    return ResponseEntity.ok(userRepository.findById(userId));
  }

}
