package com.logan.shorturl.controller;

import com.logan.shorturl.DTO.UrlResponsesDTO;
import com.logan.shorturl.exceptions.ResourceNotFoundExceptions;
import com.logan.shorturl.model.entity.UrlEntity;
import com.logan.shorturl.projection.UrlDto;
import com.logan.shorturl.service.facade.UrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;


    @GetMapping("/{domain}")
    public RedirectView redirect(@PathVariable(name = "domain") String domain) {
        return new RedirectView(urlService.urlRedirect(domain));
    }

    @GetMapping("/all")
    public List<UrlResponsesDTO> getAll() {
        return urlService.getAll();
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody UrlDto urlDto) {
        System.out.println(urlDto);
        return urlService.create(urlDto);
    }
}
