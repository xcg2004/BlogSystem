package com.xcg.blogsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO implements Serializable {
    private String title;
    private String content;
    private List<String> tag;
    private String categoryId;
    private String summary;

}
