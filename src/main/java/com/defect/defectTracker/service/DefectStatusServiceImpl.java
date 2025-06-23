package com.defect.defectTracker.service;

<<<<<<< Updated upstream
import com.defect.defectTracker.dto.DefectStatusDTO;
import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

=======
import com.defect.defectTracker.dto.DefectStatusDto;
import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

>>>>>>> Stashed changes
@Service
public class DefectStatusServiceImpl implements DefectStatusService {

    @Autowired
<<<<<<< Updated upstream
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
=======
    private DefectStatusRepo defectStatusRepo;

    @Override
    public DefectStatusDto createDefectStatus(DefectStatusDto defectStatusDto) {
        // Check for duplicate (case-insensitive)
        if (defectStatusRepo.existsByDefectStatusNameIgnoreCase(defectStatusDto.getDefectStatusName())) {
            throw new DataIntegrityViolationException("Defect status already exists");
        }

        DefectStatus defectStatus = new DefectStatus();
        defectStatus.setDefectStatusName(defectStatusDto.getDefectStatusName());

        DefectStatus savedDefectStatus = defectStatusRepo.save(defectStatus);

        DefectStatusDto responseDto = new DefectStatusDto();
        responseDto.setDefectStatusName(savedDefectStatus.getDefectStatusName());

        return responseDto;
    }
}
>>>>>>> Stashed changes
