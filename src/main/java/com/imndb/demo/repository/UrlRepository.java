package com.imndb.demo.repository;

import com.imndb.demo.entity.Url;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
}
