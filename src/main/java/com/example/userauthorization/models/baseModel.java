package com.example.userauthorization.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.util.Date;
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class baseModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdTime;
    @LastModifiedDate
    private Date updatedTime;
}
