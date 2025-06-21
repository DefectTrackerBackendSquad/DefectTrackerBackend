package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDTO;
import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DefectStatusServiceImpl implements DefectStatusService {

    @Autowired
    private DefectStatusRepo defectStatusRepository;

    @Override
    public DefectStatusDTO createDefectStatus(DefectStatusDTO dto) {
        DefectStatusDTO response = new DefectStatusDTO();

        String defectStatus = dto.getDefectStatus();

        if (defectStatus == null || defectStatus.trim().isEmpty()) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage(defectStatus == null ?
                    "Defect Status cannot be null." : "Missing required fields.");
            return response;
        }

        String trimmedStatus = defectStatus.trim();
        if (Pattern.matches(".*\\d.*", trimmedStatus) ||
                !Pattern.matches("^[a-zA-Z\\s]+$", trimmedStatus) ||
                trimmedStatus.replaceAll("\\s+", "").length() < 2) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Invalid status.");
            return response;
        }

        if (defectStatusRepository
                .findByDefectStatusNameIgnoreCase(trimmedStatus) != null) {
            response.setStatus("error");
            response.setStatusCode("4000");
            response.setMessage("Status Already Exist.");
            return response;
        }

        try {
            DefectStatus newStatus = new DefectStatus();
            newStatus.setDefectStatusName(trimmedStatus);
            defectStatusRepository.save(newStatus);

            response.setStatus("success");
            response.setStatusCode("2001");
            response.setMessage("Saved successfully.");
            response.setDefectStatus(trimmedStatus); // optional, if needed
        } catch (Exception e) {
            response.setStatus("error");
            response.setStatusCode("500");
            response.setMessage("Internal server error occurred.");
        }

        return response;
    }
}
