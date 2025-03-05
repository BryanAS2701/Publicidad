package com.prototipo.publicidad.model.dtoo;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicidadDTO {
    private Long id;
    private String title;
    private Map<String, Map<String, String>> imageUrl;
    private String redirectUrl;
    private boolean active;
    private LocalDateTime createdAd;
    private LocalDateTime updatedAd;
}
