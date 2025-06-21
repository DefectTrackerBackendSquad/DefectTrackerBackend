package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


@Service
public  class DefectServiceImpl implements DefectService {
@Autowired
    private DefectRepo defectRepo;

    @Override
    public List<Defect> getDefectsByAssignee(Long userId) {
        return defectRepo.findByAssignedById(userId);
    }
}