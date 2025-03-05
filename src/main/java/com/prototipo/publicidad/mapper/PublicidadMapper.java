package com.prototipo.publicidad.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.prototipo.publicidad.model.Image;
import com.prototipo.publicidad.model.Publicidad;
import com.prototipo.publicidad.model.dtoo.PublicidadDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicidadMapper {
    @Mapping(target = "imageUrl", source = "images", qualifiedByName = "mapImagesToImageUrl")
    PublicidadDTO publicidadToPublicidadDTO(Publicidad publicidad);

    @Named("mapImagesToImageUrl")
    default Map<String, Map<String, String>> mapImagesToImageUrl(List<Image> images) {
        if (images == null) {
            return null;
        }
        Map<String, Map<String, String>> imageUrlAnidado = new HashMap<>();

        for (Image image : images) {
            String position = image.getPosition().toUpperCase(); 
            if (!imageUrlAnidado.containsKey(position)) {
                imageUrlAnidado.put(position, new HashMap<>());
            }
            imageUrlAnidado.get(position).put(image.getSize(), image.getUrl()); 
        }
        return imageUrlAnidado;
    }
} 