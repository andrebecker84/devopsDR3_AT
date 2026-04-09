package br.com.area51.ufoTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publico")
public class PublicController {
    @GetMapping
    public ResponseEntity<?> publico(){
        return ResponseEntity.ok().build();
    }
}
