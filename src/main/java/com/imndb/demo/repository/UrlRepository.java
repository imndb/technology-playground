package com.imndb.demo.repository;

import com.imndb.demo.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findByAddressContains(String urlContains);

    Url findByAddress(String address);

}
