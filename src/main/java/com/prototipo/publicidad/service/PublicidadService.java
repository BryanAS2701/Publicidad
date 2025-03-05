package com.prototipo.publicidad.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prototipo.publicidad.mapper.PublicidadMapper;
import com.prototipo.publicidad.model.Image;
import com.prototipo.publicidad.model.Publicidad;
import com.prototipo.publicidad.model.dtoo.PublicidadDTO;
import com.prototipo.publicidad.repository.AdvertisementRepository;
import com.prototipo.publicidad.utils.InvalidInputException;
import com.prototipo.publicidad.utils.OptionalNotFoundException;

@Service
public class PublicidadService {
    @Autowired
    AdvertisementRepository adrepo;
    
    @Autowired
    PublicidadMapper publicidadMapper;

    public List<PublicidadDTO> getAll(){ 
        List<Publicidad> publicidadList = adrepo.findAll();
        return publicidadList.stream()
                .map(publicidadMapper::publicidadToPublicidadDTO)
                .collect(Collectors.toList());
    }

    public Publicidad create(Publicidad publi) throws InvalidInputException{
        return adrepo.save(publi);
    }
    
    public PublicidadDTO consultar(Long id) throws OptionalNotFoundException{ 
        Publicidad publiOriginal = adrepo.findById(id).orElseThrow(()-> new OptionalNotFoundException("ad not available"));
        System.out.println("Imágenes asociadas: " + publiOriginal.getImages().size());
        return publicidadMapper.publicidadToPublicidadDTO(publiOriginal);
    }

    public Publicidad update(Long id, Long imageId, Publicidad nuevo) throws OptionalNotFoundException {
        // Buscar la publicidad por su ID
        Publicidad publi = adrepo.findById(id).orElseThrow(() -> new OptionalNotFoundException("ad not available"));
        Image imageToUpdate = publi.getImages().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new OptionalNotFoundException("Image not found"));

        if (nuevo.getImages() != null && !nuevo.getImages().isEmpty()) {
            imageToUpdate.setUrl(nuevo.getImages().get(0).getUrl());
            imageToUpdate.setSize(nuevo.getImages().get(0).getSize());
            imageToUpdate.setPosition(nuevo.getImages().get(0).getPosition());
        }

        if (nuevo.getTitle() != null) {
            publi.setTitle(nuevo.getTitle());
        }
    
        if (nuevo.isActive() != publi.isActive()) {
            publi.setActive(nuevo.isActive());
        }
    
        if (nuevo.getUpdatedAd() != null) {
            publi.setUpdatedAd(nuevo.getUpdatedAd());
        }
        return adrepo.save(publi);
    }

    public Publicidad delete(Long id) throws OptionalNotFoundException{
        Publicidad publicidad = adrepo.findById(id).orElseThrow(() -> new OptionalNotFoundException("ad not available"));
        adrepo.delete(publicidad);
        return publicidad;
    }
}
