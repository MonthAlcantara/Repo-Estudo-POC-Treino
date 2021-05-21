package io.github.monthalcantara.aws_project01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity testeController(@PathVariable String name){
        return ResponseEntity.ok(name);
    }

    @GetMapping("/dog/color")
    public ResponseEntity testeController(){
        return ResponseEntity.ok("Endpoint de teste");
    }

}
