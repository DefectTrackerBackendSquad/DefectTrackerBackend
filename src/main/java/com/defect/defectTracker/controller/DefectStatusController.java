package com.defect.defectTracker.controller;

<<<<<<< Updated upstream
import com.defect.defectTracker.dto.DefectStatusDTO;
import com.defect.defectTracker.service.DefectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defectStatus")
=======
import com.defect.defectTracker.dto.DefectStatusDto;
import com.defect.defectTracker.service.DefectStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/defectstatus")
>>>>>>> Stashed changes
public class DefectStatusController {

    @Autowired
    private DefectStatusService defectStatusService;

    @PostMapping
<<<<<<< Updated upstream
    public ResponseEntity<DefectStatusDTO> addDefectStatus(@RequestBody DefectStatusDTO dto) {
        DefectStatusDTO response = defectStatusService.createDefectStatus(dto);

        switch (response.getStatusCode()) {
            case "2001":
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            case "4000":
                return ResponseEntity.badRequest().body(response);
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
=======
    public ResponseEntity<?> createDefectStatus(@Valid @RequestBody DefectStatusDto defectStatusDto) {
        try {
            DefectStatusDto savedDefectStatus = defectStatusService.createDefectStatus(defectStatusDto);
            return new ResponseEntity<>(savedDefectStatus, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
>>>>>>> Stashed changes
