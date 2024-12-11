package com.signdocs.document;

import com.signdocs.user.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "documents")
public class DocumentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @ManyToMany
  @JoinTable(
      name = "document_shared_users",
      joinColumns = @JoinColumn(name = "document_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<UserEntity> sharedWith;

  @OneToOne
  @JoinColumn(name = "users", referencedColumnName = "id")
  private UserEntity ownedBy;

  private byte[] fileData;

  private long fileSize;

  public DocumentEntity() {
  }

  public DocumentEntity(String title, LocalDateTime createdAt, List<UserEntity> sharedWith, UserEntity ownedBy,
      byte[] fileData, long fileSize) {
    this.title = title;
    this.sharedWith = sharedWith;
    this.ownedBy = ownedBy;
    this.fileData = fileData;
    this.fileSize = fileSize;
    this.createdAt = createdAt;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<UserEntity> getSharedWith() {
    return sharedWith;
  }

  public void setSharedWith(List<UserEntity> sharedWith) {
    this.sharedWith = sharedWith;
  }

  public UserEntity getOwnedBy() {
    return ownedBy;
  }

  public void setOwnedBy(UserEntity ownedBy) {
    this.ownedBy = ownedBy;
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }
}
