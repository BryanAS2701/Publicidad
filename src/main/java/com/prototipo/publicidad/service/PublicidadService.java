package com.prototipo.publicidad.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prototipo.publicidad.mapper.PublicidadMapper;
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

    public PublicidadDTO consultar(Long id) throws OptionalNotFoundException {
        Publicidad publiOriginal = adrepo.findById(id).orElseThrow(() -> new OptionalNotFoundException("Ad not available"));
        return publicidadMapper.publicidadToPublicidadDTO(publiOriginal);
    }

    public List<PublicidadDTO> getAll() throws OptionalNotFoundException{ 
        List<Publicidad> publicidadList = adrepo.findAll();
        return publicidadList.stream()
                .map(publicidadMapper::publicidadToPublicidadDTO)
                .collect(Collectors.toList());
    }
    

    public PublicidadDTO delete(Long id) throws OptionalNotFoundException {
        Publicidad publicidad = adrepo.findById(id).orElseThrow(() -> new OptionalNotFoundException("Ad not available"));

        adrepo.delete(publicidad);
        return publicidadMapper.publicidadToPublicidadDTO(publicidad);
    }

    public PublicidadDTO create(PublicidadDTO publiDTO) throws InvalidInputException {
        if (publiDTO.getTitle() == null || publiDTO.getTitle().isEmpty()) {
            throw new InvalidInputException("The title cannot be empty");
        }
        if (publiDTO.getRedirectUrl() == null || publiDTO.getRedirectUrl().isEmpty()) {
            throw new InvalidInputException("The redirectUrl cannot be empty");
        }
        Publicidad publi = publicidadMapper.publicidadDTOToPublicidad(publiDTO);
        Publicidad savedPublicidad = adrepo.save(publi);
        return publicidadMapper.publicidadToPublicidadDTO(savedPublicidad);
    }

    public PublicidadDTO update(Long id, PublicidadDTO nuevoDTO) throws OptionalNotFoundException { 
        Publicidad publi = adrepo.findById(id).orElseThrow(() -> new OptionalNotFoundException("Ad not available"));
    
        if (nuevoDTO.getImageUrl() != null) {
            Map<String, Map<String, String>> imageUrl = nuevoDTO.getImageUrl();
            if (imageUrl.containsKey("HORIZONTAL")) {
                Map<String, String> horizontalSizes = imageUrl.get("HORIZONTAL");
                if (horizontalSizes.containsKey("LARGE")) {
                    publi.setImage_Horizontal_large(horizontalSizes.get("LARGE"));
                }
                if (horizontalSizes.containsKey("SMALL")) {
                    publi.setImage_Horizontal_small(horizontalSizes.get("SMALL"));
                }
            }
    
            // Si se proporcionan las imágenes verticales
            if (imageUrl.containsKey("VERTICAL")) {
                Map<String, String> verticalSizes = imageUrl.get("VERTICAL");
                if (verticalSizes.containsKey("LARGE")) {
                    publi.setImage_Vertical_large(verticalSizes.get("LARGE"));
                }
                if (verticalSizes.containsKey("SMALL")) {
                    publi.setImage_Vertical_small(verticalSizes.get("SMALL"));
                }
            }
        }
    
        if (nuevoDTO.getTitle() != null) {
            publi.setTitle(nuevoDTO.getTitle());
        }
    
        if (nuevoDTO.isActive() != publi.isActive()) {
            publi.setActive(nuevoDTO.isActive());
        }
    
        if (nuevoDTO.getUpdatedAd() != null) {
            publi.setUpdatedAd(nuevoDTO.getUpdatedAd());
        }
    
        Publicidad updatedPublicidad = adrepo.save(publi);
        return publicidadMapper.publicidadToPublicidadDTO(updatedPublicidad);
    }
    
}
