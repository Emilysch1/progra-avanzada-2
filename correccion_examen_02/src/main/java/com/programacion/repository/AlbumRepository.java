package com.programacion.repository;

import com.programacion.db.Album;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface AlbumRepository extends EntityRepository<Album, Integer> {

}
