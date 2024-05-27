package com.imndb.demo;

import com.imndb.demo.entity.Url;
import com.imndb.demo.repository.UrlRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DemoController {

    private final UrlRepository urlRepository;

    public DemoController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping("/urls")
    public List<Url> getUrl() {
        return urlRepository.findAll();
    }

    @GetMapping("/time")
    public String time() {
        return "current time:" + LocalDateTime.now();
    }
}
