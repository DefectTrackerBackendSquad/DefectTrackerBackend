package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleaseDTO;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.service.ReleasesService;
import com.defect.defectTracker.util.StandardResponse;
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

    Logger logger = LoggerFactory.getLogger(ReleasesController.class);

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
                    new StandardResponse("success", "Release created successfully", null, 200),
                    HttpStatus.CREATED
            );

        } catch (Exception e) {
            logger.error("Error creating release", e);
            return new ResponseEntity<>(
                    new StandardResponse("error", "Failed to import", null, 400),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }



    @GetMapping("/search")
    public ResponseEntity<StandardResponse> searchReleases(
            @RequestParam(required = false) String releaseId,
            @RequestParam(required = false) String releaseName,
            @RequestParam(required = false) String releaseType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date releaseDate,
            @RequestParam(required = false) Long projectId
    ) {
        try {
            // Check if no search parameters provided at all
            if ((releaseId == null || releaseId.isBlank()) &&
                    (releaseName == null || releaseName.isBlank()) &&
                    (releaseType == null || releaseType.isBlank()) &&
                    releaseDate == null &&
                    projectId == null) {

                return ResponseEntity.badRequest().body(
                        new StandardResponse("failure", "At least one search parameter is required", null, 400)
                );
            }

            List<ReleaseDTO> results = releasesService.searchReleases(
                    releaseId,
                    releaseName,
                    releaseType,
                    releaseDate,
                    projectId
            );

            if (results.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new StandardResponse("failure", "No releases found matching criteria", null, 400)
                );
            }

            return ResponseEntity.ok(
                    new StandardResponse("success", "Releases fetched", results, 200)
            );

        } catch (Exception e) {
            logger.error("Error searching releases", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new StandardResponse("error", "Failed to fetch releases: " + e.getMessage(), null, 400)
            );
        }
    }

    @GetMapping("/releaseId/{releaseId}")
    public ResponseEntity<ReleaseDTO.ApiResponse> getReleaseByReleaseId(@PathVariable String releaseId) {
        try {
            ReleaseDTO.ReleaseResponse response = releasesService.getReleaseByReleaseId(releaseId);

            ReleaseDTO.ApiResponse successResponse = new ReleaseDTO.ApiResponse(
                    "success",
                    "2000",
                    "Retrieved successfully",
                    response
            );

            return ResponseEntity.ok(successResponse);

        } catch (ResourceNotFoundException e) {
            ReleaseDTO.ApiResponse failResponse = new ReleaseDTO.ApiResponse(
                    "failure",
                    "4000",
                    "Id not found: " + e.getMessage(),
                    null
            );

            return ResponseEntity.ok(failResponse);
        } catch (Exception e) {
            ReleaseDTO.ApiResponse errorResponse = new ReleaseDTO.ApiResponse(
                    "failure",
                    "4000",
                    "Something went wrong: " + e.getMessage(),
                    null
            );

            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @GetMapping("/projectId/{projectId}")
    public ResponseEntity<StandardResponse> getReleasesByProjectId(@PathVariable String projectId) {
        try {
            List<ReleaseDTO> releases = releasesService.getReleasesByProjectId(projectId);

            return ResponseEntity.ok(
                    new StandardResponse("success", "Releases for project fetched", releases, 200)
            );
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new StandardResponse("error", "Project not found: " + e.getMessage(), null, 400)
            );
        } catch (Exception e) {
            logger.error("Error fetching releases by projectId", e);
            return new ResponseEntity<>(
                    new StandardResponse("error", "Something went wrong", null, 400),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}