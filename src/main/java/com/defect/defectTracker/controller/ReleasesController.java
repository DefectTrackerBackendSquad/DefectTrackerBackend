package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleasesDTO;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.service.ReleasesService;
import com.defect.defectTracker.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/releases")
public class ReleasesController {

    Logger logger = LoggerFactory.getLogger("ReleaseController.class");

    @Autowired
    private ReleasesService releasesService;

    @PostMapping
    public ResponseEntity<StandardResponse> createRelease(@RequestBody Map<String, Object> request) {
        try {
            logger.info("Creating new release with data: {}", request);

            Releases releases = new Releases();
            releases.setReleaseName((String) request.get("releaseName"));


            Object dateObj = request.get("releaseDate");
            if (dateObj != null) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date releaseDate = sdf.parse(dateObj.toString());
                    releases.setReleaseDate(releaseDate);
                } catch (ParseException e) {
                    return new ResponseEntity<>(
                            new StandardResponse("error", "Invalid date format. Use yyyy-MM-dd", null, 400),
                            HttpStatus.BAD_REQUEST
                    );
                }
            }

            releases.setReleaseType((String) request.get("releaseType"));
            String projectId = (String) request.get("projectId");

            Releases createdRelease = releasesService.createRelease(releases, projectId);


            return new ResponseEntity<>(
                    new StandardResponse("success", "Release created successfully",null, 201),
                    HttpStatus.CREATED
            );

        } catch (Exception e) {
            logger.info("Error creating release: {}", e.getMessage());
            return new ResponseEntity<>(
                    new StandardResponse("error", "Failed to import", null, 500),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @GetMapping("/search")
    public ResponseEntity<StandardResponse> searchReleases(
            @RequestParam(required = false) String releaseId,  // Added this parameter
            @RequestParam(required = false) String releaseName,
            @RequestParam(required = false) String releaseType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date releaseDate,
            @RequestParam(required = false) Long projectId
    ) {
        List<ReleasesDTO> results = releasesService.searchReleases(
                releaseId,
                releaseName,
                releaseType,
                releaseDate,
                projectId
        );

        return ResponseEntity.ok(
                new StandardResponse("success", "Releases fetched", results, 200)
        );
    }














}


