package com.ahom.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahom.hrms.entities.Opening;
import com.ahom.hrms.service.OpeningService;

@RestController
@RequestMapping("/openings")
public class OpeningController {
    private final OpeningService openingService;

    @Autowired
    public OpeningController(OpeningService openingService) {
        this.openingService = openingService;
    }

    @GetMapping
    public List<Opening> getAllOpenings() {
        return openingService.getAllOpenings();
    }

    @GetMapping("/{id}")
    public Opening getOpeningById(@PathVariable int id) {
        return openingService.getOpeningById(id);
    }

    @PostMapping
    public Opening createOpening(@RequestBody Opening opening) {
        return openingService.createOpening(opening);
    }

    @PutMapping("/{id}")
    public Opening updateOpening(@PathVariable int id, @RequestBody Opening updatedOpening) {
        return openingService.updateOpening(id, updatedOpening);
    }

    @DeleteMapping("/{id}")
    public void deleteOpening(@PathVariable int id) {
        openingService.deleteOpening(id);
    }
}
