package com.project.questapp.requests;

import lombok.Data;

//fazla yer kaplamaz,gereksiz bilgileri sunmaz!

@Data
public class PostCreateRequest {
    Long id;
    String title;
    String text;
    Long userId;
}
