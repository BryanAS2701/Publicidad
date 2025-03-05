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
    
    // Definimos el mapeo de PublicidadDTO a Publicidad 
    @Mapping(target = "image_Horizontal_small", source = "imageUrl.HORIZONTAL.MEDIUM")
    @Mapping(target = "image_Horizontal_large", source = "imageUrl.HORIZONTAL.LARGE")
    @Mapping(target = "image_Vertical_small", source = "imageUrl.VERTICAL.MEDIUM")
    @Mapping(target = "image_Vertical_large", source = "imageUrl.VERTICAL.LARGE")
    Publicidad publicidadDTOToPublicidad(PublicidadDTO publicidadDTO);

    // Método para mapear las URLs a un mapa con la estructura deseada
    default Map<String, Map<String, String>> mapImageUrlsToMap(Publicidad publicidad) {
        Map<String, Map<String, String>> imageUrls = new HashMap<>();

        // Mapeamos las imágenes horizontales
        Map<String, String> horizontal = new HashMap<>();
        horizontal.put("SMALL", publicidad.getImage_Horizontal_small());
        horizontal.put("LARGE", publicidad.getImage_Horizontal_large());

        // Mapeamos las imágenes verticales
        Map<String, String> vertical = new HashMap<>();
        vertical.put("SMALL", publicidad.getImage_Vertical_small());
        vertical.put("LARGE", publicidad.getImage_Vertical_large());

        // Agregamos ambos mapas al mapa principal
        imageUrls.put("HORIZONTAL", horizontal);
        imageUrls.put("VERTICAL", vertical);

        return imageUrls;
    }
}
