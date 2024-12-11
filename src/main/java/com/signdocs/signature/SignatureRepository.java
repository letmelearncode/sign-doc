package com.signdocs.signature;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<SignatureEntity, Long> {

  List<SignatureEntity> findByDocumentId(Long documentId);

}


