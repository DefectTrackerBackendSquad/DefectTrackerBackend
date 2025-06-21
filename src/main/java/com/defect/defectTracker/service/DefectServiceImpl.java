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
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok generates constructor
public class DefectServiceImpl implements DefectService {
    Logger logger = LoggerFactory.getLogger(DefectServiceImpl.class);

    @Autowired
    private DefectRepo defectRepo;

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