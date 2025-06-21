// DefectServiceImpl.java
package com.example.defectTracker.service;

import com.example.defectTracker.entity.Defect;
import com.example.defectTracker.repository.DefectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefectServiceImpl implements DefectService {

    private final DefectRepository defectRepository;

    public DefectServiceImpl(DefectRepository defectRepository) {
        this.defectRepository = defectRepository;
    }

    @Override
    public Defect createDefect(Defect defect) {
        return defectRepository.save(defect);
    }

    @Override
    public Defect getDefectByDefectId(String defectId) {
        return defectRepository.findByDefectId(defectId);
    }

    @Override
    public List<Defect> getAllDefects() {
        return defectRepository.findAll();
    }

    @Override
    public Defect updateDefect(Defect defect) {
        return defectRepository.save(defect);
    }

    @Override
    public void deleteDefect(String defectId) {
        defectRepository.deleteByDefectId(defectId);
    }
}