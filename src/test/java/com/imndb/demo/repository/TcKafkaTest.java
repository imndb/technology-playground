package com.imndb.demo.repository;

import com.imndb.demo.entity.Url;
import com.imndb.demo.kafka.UrlAddedEvent;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


@SpringBootTest
public class TcKafkaTest {

    @Autowired
    private UrlRepository urlRepository;

    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.6.1")).withKraft();

    static {
        kafka.start();

    }

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void shouldHandleUrlAddEvent() {

        UrlAddedEvent urlAddedEvent = new UrlAddedEvent("http://testadress");

        kafkaTemplate.send("urls-topic", urlAddedEvent.address(), urlAddedEvent);

        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> {
                    Optional<Url> optionalUrl = Optional.ofNullable(urlRepository.findByAddress(urlAddedEvent.address()));
                            assertThat(optionalUrl.get().getAddress()).isEqualTo(urlAddedEvent.address());
                        }


                );


    }
}
