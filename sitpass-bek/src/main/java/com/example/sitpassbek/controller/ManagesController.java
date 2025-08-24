package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.manages.CreateManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDTO;
import com.example.sitpassbek.service.ManagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manages")
public class ManagesController {

    private final ManagesService managesService;

    @Autowired
    public ManagesController(ManagesService managesService) {
        this.managesService = managesService;
    }

    @PostMapping("/assign")
    public ResponseEntity<ManagesDTO> assignManager(@RequestBody CreateManagesDTO dto) {
        ManagesDTO created = managesService.assignManager(dto);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteManager(@RequestBody CreateManagesDTO dto) {
        managesService.deleteManages(dto);
        return ResponseEntity.ok().build();
    }
}
