package com.logan.shorturl.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document("url")
public class UrlEntity {
    @Id
    private String id;
    private String url;
    private String hash;
    private String domain;

}
