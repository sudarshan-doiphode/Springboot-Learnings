package com.graphql.service;

import com.graphql.entity.Cricketer;

import java.util.List;

public interface CricketerService {

    String deleteCricketer(int id);

    Cricketer getCricketer(int id);

    Cricketer createCricketer(String name, String runs, String wickets);

    List<Cricketer> getAllCricketers();

    Cricketer updateCricketer(int id, String name, String runs, String wickets);
}
