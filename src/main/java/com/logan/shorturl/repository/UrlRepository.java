package com.logan.shorturl.repository;

import com.logan.shorturl.model.entity.UrlEntity;
import com.logan.shorturl.projection.UrlDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    @Query("{'hash': ?0, 'domain': {$in: [null, '']}}")
    Optional<UrlDto> findByHashAndEmptyDomain(String hash);
    boolean existsByDomain(String domain);

    Optional<UrlDto> findFirstByHashOrDomain(String hash, String domain);
}
