package com.signdocs.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse <T>{
  private String status;
  private String errorCode;
  private String errorDescription;
  private T data;

}
