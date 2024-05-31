package com.imndb.demo.kafka;

import com.imndb.demo.entity.Url;
import com.imndb.demo.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
class KafkaUrlEventHandler {

    private static final Logger log = LoggerFactory.getLogger(
            KafkaUrlEventHandler.class
    );

    private final UrlRepository urlRepository;

    KafkaUrlEventHandler(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @KafkaListener(topics = "urls-topic", groupId = "demo")
    public void handle(UrlAddedEvent event) {
        log.info(
                "Received a Url with adress:{}: ",
                event.address()
        );
        Url newUrl = new Url();
        newUrl.setAddress(event.address());
        urlRepository.save(newUrl);
    }
}