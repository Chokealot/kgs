package com.chokealot.konyiguitar.comments;

import lombok.Data;

@Data
public class Comment {

    private Long id;
    private Long commentId;
    private String title;
    private String body;

}