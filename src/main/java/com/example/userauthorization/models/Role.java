package com.example.userauthorization.models;

import jakarta.persistence.Entity;
import lombok.Data;
@Entity
@Data
public class Role extends baseModel {
    String role_type;
}
