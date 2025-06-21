package com.example.defectTracker.service;

import com.example.defectTracker.Dto.DefectStatusRequest;
import com.example.defectTracker.Dto.DefectStatusResponse;

public interface DefectStatusService {
    DefectStatusResponse createDefectStatus(DefectStatusRequest defectStatusRequest);
}