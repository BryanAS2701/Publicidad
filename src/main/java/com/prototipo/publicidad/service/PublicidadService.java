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
        if (publiDTO.getImageUrl() == null || publiDTO.getImageUrl().isEmpty()) {
            throw new InvalidInputException("provide at least one image address.");
        } else if (
            (publiDTO.getImageUrl().get("HORIZONTAL") == null || publiDTO.getImageUrl().get("HORIZONTAL").isEmpty()) &&
            (publiDTO.getImageUrl().get("VERTICAL") == null || publiDTO.getImageUrl().get("VERTICAL").isEmpty())
        ) {
            throw new InvalidInputException("provide at least one image address.");
        } else if (
            (publiDTO.getImageUrl().get("HORIZONTAL").get("SMALL") == null || publiDTO.getImageUrl().get("HORIZONTAL").get("SMALL").isEmpty()) &&
            (publiDTO.getImageUrl().get("HORIZONTAL").get("LARGE") == null || publiDTO.getImageUrl().get("HORIZONTAL").get("LARGE").isEmpty()) &&
            (publiDTO.getImageUrl().get("VERTICAL").get("SMALL") == null || publiDTO.getImageUrl().get("VERTICAL").get("SMALL").isEmpty()) &&
            (publiDTO.getImageUrl().get("VERTICAL").get("LARGE") == null || publiDTO.getImageUrl().get("VERTICAL").get("LARGE").isEmpty())
        ) {
            throw new InvalidInputException("provide at least one image address.");
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
                    publi.setImageHorizontalLarge(horizontalSizes.get("LARGE"));
                }
                if (horizontalSizes.containsKey("SMALL")) {
                    publi.setImageHorizontalSmall(horizontalSizes.get("SMALL"));
                }
            }

            if (imageUrl.containsKey("VERTICAL")) {
                Map<String, String> verticalSizes = imageUrl.get("VERTICAL");
                if (verticalSizes.containsKey("LARGE")) {
                    publi.setImageVerticalLarge(verticalSizes.get("LARGE"));
                }
                if (verticalSizes.containsKey("SMALL")) {
                    publi.setImageVerticalSmall(verticalSizes.get("SMALL"));
                }
            }
        }
    
        if (nuevoDTO.getTitle() != null) {
            publi.setTitle(nuevoDTO.getTitle());
        }
    
        if (nuevoDTO.isActive() != publi.isActive()) {
            publi.setActive(nuevoDTO.isActive());
        }
    
        Publicidad updatedPublicidad = adrepo.save(publi);
        return publicidadMapper.publicidadToPublicidadDTO(updatedPublicidad);
    }
    
}
