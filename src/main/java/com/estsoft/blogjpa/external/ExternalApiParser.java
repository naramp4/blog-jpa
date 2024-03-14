package com.estsoft.blogjpa.external;

import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Component
public class ExternalApiParser {

    private final BlogService service;

    public ExternalApiParser(BlogService service) {
        this.service = service;
    }

    public void parser(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", List.class);
        if(response.getStatusCode().is2xxSuccessful()){
            response.getBody();
        }
        log.info("test" + response.getBody());

        List<LinkedHashMap<String, Object>> list = response.getBody();
        List<AddArticleRequest> insertList = new ArrayList<>();
        for(LinkedHashMap<String, Object> map : list){
            String title = (String) map.get("title");
            String content = (String) map.get("body");

            service.save(new AddArticleRequest(title, content));
        }
    }



}
