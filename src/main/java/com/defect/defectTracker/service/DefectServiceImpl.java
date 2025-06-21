package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // Lombok generates constructor
@Service
public  class DefectServiceImpl implements DefectService {
@Autowired
    private DefectRepo defectRepo;


    Logger logger = LoggerFactory.getLogger(DefectServiceImpl.class);

    @Override
    public List<Defect> getDefectsByAssignee(Long userId) {
        return defectRepo.findByAssignedById(userId);
    }


    @Override
    public Defect getDefectByDefectId(String id) {
        DefectDto defectDto = new DefectDto();
        Defect defect = defectRepo.findByDefectId(id);
        logger.info("Fetching defect with ID: {}", id);
        if (defect != null ){
            //logger.info("Defect found: {}", defect.get().getDefectId());
            //BeanUtils.copyProperties(defectDto, defect.get());
            return defect;
        } else {
            return null;
        }
    }
}