package com.prototipo.publicidad.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.prototipo.publicidad.model.Publicidad;
import com.prototipo.publicidad.model.dtoo.PublicidadDTO;
@Mapper(componentModel= "spring")
public interface PublicidadMapper {
    @Mapping(target = "imageUrl", expression = "java(mapImageUrlsToMap(publicidad))")
    PublicidadDTO publicidadToPublicidadDTO(Publicidad publicidad);

    // Mapeamos de PublicidadDTO a Publicidad
    @Mapping(target = "imageHorizontalSmall", source = "imageUrl.HORIZONTAL.SMALL")
    @Mapping(target = "imageHorizontalLarge", source = "imageUrl.HORIZONTAL.LARGE")
    @Mapping(target = "imageVerticalSmall", source = "imageUrl.VERTICAL.SMALL")
    @Mapping(target = "imageVerticalLarge", source = "imageUrl.VERTICAL.LARGE")
    @Mapping(target = "active", source = "active")
    Publicidad publicidadDTOToPublicidad(PublicidadDTO publicidadDTO);

    // Método para mapear las URLs a un mapa con la estructura deseada
    default Map<String, Map<String, String>> mapImageUrlsToMap(Publicidad publicidad) {
        Map<String, Map<String, String>> imageUrls = new HashMap<>();

        // Mapeamos las imágenes horizontales
        Map<String, String> horizontal = new HashMap<>();
        horizontal.put("SMALL", publicidad.getImageHorizontalSmall());
        horizontal.put("LARGE", publicidad.getImageHorizontalLarge());

        // Mapeamos las imágenes verticales
        Map<String, String> vertical = new HashMap<>();
        vertical.put("SMALL", publicidad.getImageVerticalSmall());
        vertical.put("LARGE", publicidad.getImageVerticalLarge());

        // Agregamos ambos mapas al mapa principal
        imageUrls.put("HORIZONTAL", horizontal);
        imageUrls.put("VERTICAL", vertical);

        return imageUrls;
    }
}
