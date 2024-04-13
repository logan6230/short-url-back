package com.logan.shorturl.service.impl;

import com.logan.shorturl.DTO.UrlResponsesDTO;
import com.logan.shorturl.model.entity.UrlEntity;
import com.logan.shorturl.projection.UrlDto;
import com.logan.shorturl.repository.UrlRepository;
import com.logan.shorturl.service.facade.UrlService;
import com.logan.shorturl.util.CreateHashShort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UrlImpl implements UrlService {
    @Value("${localhost}")
    private String urlDominio;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UrlResponsesDTO> getAll() {
        List<UrlEntity> urlEntities = urlRepository.findAll();

        return urlEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UrlResponsesDTO convertToDto(UrlEntity urlEntity) {
        UrlResponsesDTO urlResponsesDTO = modelMapper.map(urlEntity, UrlResponsesDTO.class);
        urlResponsesDTO.setUrlOrigin(urlEntity.getUrl());
        urlResponsesDTO.setId(urlEntity.getId());
        if (!urlEntity.getDomain().isEmpty()) {
            urlResponsesDTO.setUrlShorted(urlDominio + urlEntity.getDomain());
        } else {
            urlResponsesDTO.setUrlShorted(urlDominio + urlEntity.getHash());
        }
        return urlResponsesDTO;
    }

    @Override
    public String create(UrlDto urlDto) {

        if (existsByDomain(urlDto.getDomain()) && !urlDto.getDomain().isEmpty()) {
            return "El dominio ya existe";
        }
        String hash = CreateHashShort.crearHash(urlDto.getUrl());
        Optional<UrlDto> urlResponse = existHash(hash);
        if (urlResponse.isPresent() && urlDto.getDomain().isEmpty()) {
            return createUrlResponse(urlResponse.get());
        } else {
            urlDto.setHash(hash);

            urlRepository.save(modelMapper.map(urlDto, UrlEntity.class));
        }
        return createUrlResponse(urlDto);
    }

    @Override
    public String urlRedirect(String urlRequest) {
        Optional<UrlDto> url = urlRepository.findFirstByHashOrDomain(urlRequest, urlRequest);
        if (url.isPresent()) {
            return url.get().getUrl();
        } else {
            return "https://www.youtube.co/";
        }
    }

    private Optional<UrlDto> existHash(String hash) {
        return urlRepository.findByHashAndEmptyDomain(hash);
    }

    private boolean existsByDomain(String domain) {
        return urlRepository.existsByDomain(domain);
    }

    private String createUrlResponse(UrlDto urlDto) {

        StringBuilder urlResponseBuilder = new StringBuilder(urlDominio);
        String domainOrHash = urlDto.getDomain().isEmpty() ? urlDto.getHash() : urlDto.getDomain();
        urlResponseBuilder.append(domainOrHash);
        return urlResponseBuilder.toString();
    }

}
