package com.logan.shorturl.service.facade;

import com.logan.shorturl.DTO.UrlResponsesDTO;
import com.logan.shorturl.model.entity.UrlEntity;
import com.logan.shorturl.projection.UrlDto;

import java.util.List;


public interface UrlService {

    List<UrlResponsesDTO> getAll();
    String create (UrlDto urlEntity);

    String urlRedirect(String urlRequest);


}
