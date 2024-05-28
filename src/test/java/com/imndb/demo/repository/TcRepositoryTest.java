package com.imndb.demo.repository;

import com.imndb.demo.DemoApplication;
import com.imndb.demo.entity.Url;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles("tc")
public class TcRepositoryTest {

    private static final String POSTGRES_IMAGE = "postgres:13.1-alpine";

    @Autowired
    private UrlRepository urlRepository;

    @ClassRule
    public static PostgreSQLContainer postgresql = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_IMAGE))
            .withDatabaseName("testdb")
            .withUsername("pass-postgres")
            .withPassword("pass-postgres")
            .withInitScript("schema.sql");


    static {
        postgresql.start();
    }
    @Test
    @Transactional
    public void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationNative_ThenModifyMatchingUsers() {
        String address = "SAMPLE3";
        Url entity = new Url();
        entity.setAddress(address);
        Url updatedUrl = urlRepository.save(entity);
        assertThat(updatedUrl.getAddress()).isEqualTo(address);
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        postgresql.start(); // Ensure container is started
        postgresql.waitingFor(new LogMessageWaitStrategy().withRegEx(".*server started.*\\s"));

        registry.add("spring.datasource.url", postgresql::getJdbcUrl);
        registry.add("spring.datasource.username", postgresql::getUsername);
        registry.add("spring.datasource.password", postgresql::getPassword);
    }

}
