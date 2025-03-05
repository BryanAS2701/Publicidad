package com.prototipo.publicidad.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prototipo.publicidad.model.Publicidad;
import com.prototipo.publicidad.model.dtoo.PublicidadDTO;

public interface PublicidadMapper {
    PublicidadMapper INSTANCE = Mappers.getMapper(PublicidadMapper.class);

    @Mapping(target = "imageUrl", expression = "java(mapImageUrlsToMap(publicidad))")
    PublicidadDTO publicidadToPublicidadDTO(Publicidad publicidad);

    default Map<String, Map<String, String>> mapImageUrlsToMap(Publicidad publicidad) {
        Map<String, Map<String, String>> imageUrls = new HashMap<>();

        Map<String, String> horizontal = new HashMap<>();
        horizontal.put("MEDIUM", publicidad.getImage_Horizontal_small());
        horizontal.put("LARGE", publicidad.getImage_Horizontal_large());

        Map<String, String> vertical = new HashMap<>();
        vertical.put("MEDIUM", publicidad.getImage_Vertical_small());
        vertical.put("LARGE", publicidad.getImage_Vertical_large());

        imageUrls.put("HORIZONTAL", horizontal);
        imageUrls.put("VERTICAL", vertical);

        return imageUrls;
    }
}
