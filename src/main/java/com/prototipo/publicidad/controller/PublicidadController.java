package com.prototipo.publicidad.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prototipo.publicidad.model.Publicidad;
import com.prototipo.publicidad.model.dtoo.PublicidadDTO;
import com.prototipo.publicidad.service.PublicidadService;
import com.prototipo.publicidad.utils.InvalidInputException;
import com.prototipo.publicidad.utils.OptionalNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/advertisements")
public class PublicidadController {
    @Autowired
    private PublicidadService publicidadService;

    @GetMapping("/consultar/{id}") 
    public ResponseEntity<?> consulta(@Valid @PathVariable Long id) throws OptionalNotFoundException{
        PublicidadDTO publiDTO = publicidadService.consultar(id); 
        return new ResponseEntity<>(publiDTO, HttpStatus.OK); 
    }

    @GetMapping 
    public ResponseEntity<?> getAll() {
        List<PublicidadDTO> totalDTO = publicidadService.getAll(); 
        return new ResponseEntity<>(totalDTO, HttpStatus.OK); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Long id) throws OptionalNotFoundException{
        publicidadService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Publicidad publi) throws InvalidInputException{
        publicidadService.create(publi);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/image/{imageId}")
    public ResponseEntity<Publicidad> update(@PathVariable Long id, @PathVariable Long imageId, @RequestBody Publicidad nuevo) {
        try {
            Publicidad updatedPubli = publicidadService.update(id, imageId, nuevo);
            
            return ResponseEntity.ok(updatedPubli);
        } catch (OptionalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
