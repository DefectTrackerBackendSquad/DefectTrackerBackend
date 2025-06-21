package com.defect.defectTracker.service;

import org.springframework.stereotype.Service;
import com.defect.defectTracker.dto.DefectDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface DefectService {
    DefectDto createDefect(DefectDto defectDto, MultipartFile attachment);
}
