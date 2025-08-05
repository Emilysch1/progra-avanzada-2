package com.programacion.repository;

import com.programacion.db.Singer;

import java.util.List;

public interface SingerRepository {
    Singer findById(Integer id);
    List<Singer> findAll();
    void save(Singer company);
    void remove(Singer company);
    void remove(Integer companyId);
    void update(Singer company);
}
