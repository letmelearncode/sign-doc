package com.signdocs.signature;

import com.signdocs.document.DocumentEntity;
import com.signdocs.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "signatures")
public record SignatureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    DocumentEntity document,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity owner,

    @Column(columnDefinition = "TEXT")
    String imageData
) {}
