package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.manages.CheckManageDTO;
import com.example.sitpassbek.dto.manages.CreateManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDetailDTO;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.service.ManagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/allManagesForFacility/{id}")
    public ResponseEntity<List<ManagesDetailDTO>> getAllManagesForFacility(@PathVariable Long id) {
        return ResponseEntity.ok(managesService.getActiveManagersByFacility(id));

    }

    @PostMapping("/check")
    public boolean checkManage(@RequestBody CheckManageDTO dto) {
        return managesService.doesUserManageFacility(dto);
    }
}
