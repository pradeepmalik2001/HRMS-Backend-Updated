package com.ahom.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahom.hrms.Repository.OpeningRepository;
import com.ahom.hrms.entities.Opening;

@Service
public class OpeningService {
    private final OpeningRepository openingRepository;

    @Autowired
    public OpeningService(OpeningRepository openingRepository) {
        this.openingRepository = openingRepository;
    }

    public List<Opening> getAllOpenings() {
        return openingRepository.findAll();
    }

    public Opening getOpeningById(int id) {
        return openingRepository.findById(id).orElse(null);
    }

    public Opening createOpening(Opening opening) {
        return openingRepository.save(opening);
    }

    public Opening updateOpening(Integer id, Opening updatedOpening) {
        if (!openingRepository.existsById(id)) {
            return null;
        }
        updatedOpening.setId(id);
        return openingRepository.save(updatedOpening);
    }

    public void deleteOpening(int id) {
        openingRepository.deleteById(id);
    }
}
