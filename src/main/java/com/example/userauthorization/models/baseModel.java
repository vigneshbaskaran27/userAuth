package com.example.userauthorization.models;

import lombok.Data;

import java.util.Date;

@Data
public class baseModel {
    private long id;
    private Date createdTime;
    private Date updatedTime;
}
