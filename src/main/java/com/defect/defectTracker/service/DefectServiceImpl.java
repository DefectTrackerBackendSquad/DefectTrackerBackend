// DefectServiceImpl.java
package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefectServiceImpl implements DefectService {

    private final DefectRepo defectRepository;

    public DefectServiceImpl(DefectRepo defectRepository) {
        this.defectRepository = defectRepository;
    }
    @Override
    public Defect getDefectByDefectId(String defectId) {
        return defectRepository.findByDefectId(defectId);
    }
    @Override
    public Defect updateDefect(Defect defect) {
        return defectRepository.save(defect);
    }


}