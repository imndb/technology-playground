package com.imndb.demo.repository;

import com.imndb.demo.entity.Url;
import com.imndb.demo.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})

public class RepositoryTest {


    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void testAddUrl() {
        Url url = new Url();
        String address = "address";
        url.setAddress(address);
        Url savedUrl = urlRepository.save(url);
        assertNotNull(savedUrl);
        assertEquals(address, savedUrl.getAddress());

    }
}
