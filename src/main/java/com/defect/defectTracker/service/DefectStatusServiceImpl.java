package com.example.defectTracker.service;

import com.example.defectTracker.Dto.DefectStatusRequest;
import com.example.defectTracker.Dto.DefectStatusResponse;
import com.example.defectTracker.entity.DefectStatus;
import com.example.defectTracker.repository.DefectStatusRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DefectStatusServiceImpl implements DefectStatusService {

    @Autowired
    private DefectStatusRepository defectStatusRepository;

    @Override
    public DefectStatusResponse createDefectStatus(DefectStatusRequest defectStatusRequest) {
        DefectStatusResponse response = new DefectStatusResponse();

        // Validate defect status
        String defectStatus = defectStatusRequest.getDefectStatus();

        // Check if null or empty
        if (defectStatus == null) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Defect Status cannot be null.");
            return response;
        }

        // Trim and check if empty after trimming
        String trimmedStatus = defectStatus.trim();
        if (trimmedStatus.isEmpty()) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Missing required fields.");
            return response;
        }

        // Check if contains any numbers
        if (Pattern.matches(".*\\d.*", trimmedStatus)) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Invalid status.");
            return response;
        }

        // Check for invalid characters (only letters and spaces allowed)
        if (!Pattern.matches("^[a-zA-Z\\s]+$", trimmedStatus)) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Invalid status.");
            return response;
        }

        // Check if meaningful text (at least 2 non-space characters)
        if (trimmedStatus.replaceAll("\\s+", "").length() < 2) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Invalid status");
            return response;
        }

        // Check if already exists (case-insensitive check)
        DefectStatus existingStatus = defectStatusRepository.findByDefectStatusNameIgnoreCase(trimmedStatus);
        if (existingStatus != null) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Status Already Exit.");
            return response;
        }

        // Create new defect status using BeanUtils
        DefectStatus newDefectStatus = new DefectStatus();
        try {
            // Using BeanUtils to copy properties from request to entity
            BeanUtils.copyProperties(newDefectStatus, defectStatusRequest);

            // Explicitly set the status name as we want the trimmed version
            newDefectStatus.setDefectStatusName(trimmedStatus);

            defectStatusRepository.save(newDefectStatus);

            response.setStatus("success");
            response.setStatusCode("2001");
            response.setMessage("Saved successfully.");
        } catch (Exception e) {
            response.setStatus("error");
            response.setStatusCode("500");
            response.setMessage("Internal server error occurred.");
        }

        return response;
    }
}