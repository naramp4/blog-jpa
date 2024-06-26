package com.estsoft.blogjpa.dto;

import com.estsoft.blogjpa.domain.Article;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {
    private String title;
    private String content;


    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getTitle();
    }
}
