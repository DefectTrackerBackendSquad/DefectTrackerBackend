package com.defect.defectTracker.controller;

import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.service.ReleasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/releases")
public class ReleasesController {

    @Autowired
    private ReleasesService releasesService;

    // 🔍 Search endpoint
    @GetMapping("/search")
    public List<Releases> searchReleases(
            @RequestParam(required = false) String releaseName,
            @RequestParam(required = false) String releaseType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date releaseDate,
            @RequestParam(required = false) Long projectId
    ) {
//        System.out.println("Searching with name: " + releaseName);
//        System.out.println("Type: " + releaseType);
//        System.out.println("Date: " + releaseDate);
//        System.out.println("Project ID: " + projectId);

        return releasesService.searchReleases(releaseName, releaseType, releaseDate, projectId);
    }
}