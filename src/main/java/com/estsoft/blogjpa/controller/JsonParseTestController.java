package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.external.ExternalApiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JsonParseTestController {
    private final ExternalApiParser parser;

    public JsonParseTestController(ExternalApiParser parser) {
        this.parser = parser;
    }

    @GetMapping("/json")
    public void test(){
        parser.parser();
    }
}
