package io.github.monthalcantara.aws_project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dogs")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public ResponseEntity testeController(@RequestParam String teste){
        return ResponseEntity.ok(teste);
    }

}
