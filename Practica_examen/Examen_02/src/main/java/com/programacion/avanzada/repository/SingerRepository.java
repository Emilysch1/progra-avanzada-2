package com.programacion.avanzada.repository;

import com.programacion.avanzada.db.Singer;

import java.util.List;

public interface SingerRepository  {

    Singer findById(Integer id) ;
     List<Singer> findAll();

     void save(Singer company);
     void remove(Singer company);
     void remove(Integer companyid);
     void update(Singer company);

}
