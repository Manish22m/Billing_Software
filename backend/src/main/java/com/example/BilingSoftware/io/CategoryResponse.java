package com.example.BilingSoftware.io;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CategoryResponse {

    private String categoryID;
    private String name;
    private String description;
    private String bgcolor;
    private String imgUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
