package com.signdocs.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DocumentUploadRequest {

  private Long ownerId;
  private List<Long> sharedWith;

  public DocumentUploadRequest() {
  }

  public DocumentUploadRequest(Long ownerId, List<Long> sharedWith) {
    this.ownerId = ownerId;
    this.sharedWith = sharedWith;
  }

  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  public List<Long> getSharedWith() {
    return sharedWith;
  }

  public void setSharedWith(List<Long> sharedWith) {
    this.sharedWith = sharedWith;
  }
}
