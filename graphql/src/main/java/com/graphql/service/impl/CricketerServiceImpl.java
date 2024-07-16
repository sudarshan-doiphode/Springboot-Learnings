package com.graphql.service.impl;

import com.graphql.entity.Cricketer;
import com.graphql.repo.CricketerRepo;
import com.graphql.service.CricketerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CricketerServiceImpl implements CricketerService {

    private final CricketerRepo cricketerRepo;

    @Override
    public String deleteCricketer(int id) {
        cricketerRepo.deleteById(id);
        return "Record Deleted !!!";
    }

    @Override
    public Cricketer getCricketer(int id) {
        return cricketerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No Records found !!!"));
    }

    @Override
    public Cricketer createCricketer(String name, String runs, String wickets) {
        Cricketer cricketer = new Cricketer();
        cricketer.setName(name);
        cricketer.setRuns(runs);
        cricketer.setWickets(wickets);
        return cricketerRepo.save(cricketer);
    }


    @Override
    public List<Cricketer> getAllCricketers() {
        return cricketerRepo.findAll();
    }

    @Override
    public Cricketer updateCricketer(int id, String name, String runs, String wickets) {
        Optional<Cricketer> optionalCricketer = cricketerRepo.findById(id);
        if (optionalCricketer.isPresent()) {
            Cricketer cricketer = optionalCricketer.get();
            if (name != null) {
                cricketer.setName(name);
            }
            if (runs != null) {
                cricketer.setRuns(runs);
            }
            if (wickets != null) {
                cricketer.setWickets(wickets);
            }
            return cricketerRepo.save(cricketer);
        } else {
            throw new RuntimeException("Cricketer with id " + id + " not found");
        }
    }

}
