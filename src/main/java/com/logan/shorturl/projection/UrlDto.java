package com.logan.shorturl.projection;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Data
public class UrlDto {
    private String id;
    @NotBlank(message = "La URL no debe estar en blanco")
    private String url;
    private String hash;
    @Pattern(regexp = "^$|.{5,8}", message = "El dominio debe tener minimo 5 caracteres maximo 8")
    private String domain;
}