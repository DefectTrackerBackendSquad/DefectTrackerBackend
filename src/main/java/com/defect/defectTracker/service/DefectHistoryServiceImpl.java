package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectHistoryDto;
import com.defect.defectTracker.entity.DefectHistory;
import com.defect.defectTracker.repository.DefectHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefectHistoryServiceImpl implements DefectHistoryService {

    @Autowired
    private DefectHistoryRepo defectHistoryRepository;

    @Override
    public List<DefectHistoryDto> getDefectHistoriesByDefectId(Long defectId) {
        List<DefectHistory> historyList = defectHistoryRepository.findByDefectId(defectId);
        return historyList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private DefectHistoryDto convertToDto(DefectHistory history) {
        DefectHistoryDto dto = new DefectHistoryDto();
        dto.setId(history.getId());
        dto.setDefectDate(history.getDefectDate());
        dto.setDefectTime(history.getDefectTime());
        dto.setPreviousStatus(history.getPreviousStatus());
        dto.setAssignedTo(history.getAssignedTo());
        dto.setAssignedBy(history.getAssignedBy());
        dto.setReleaseId(history.getReleaseId());
        dto.setDefectStatus(history.getDefectStatus());
        if (history.getDefect() != null) {
            dto.setDefectId(history.getDefect().getId());
        }
        return dto;
    }
}
