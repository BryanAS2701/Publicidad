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

    @GetMapping("/{id}")
    public ResponseEntity<PublicidadDTO> getPublicidad(@PathVariable Long id) throws OptionalNotFoundException {
        PublicidadDTO publicidadDTO = publicidadService.consultar(id);
        return ResponseEntity.ok(publicidadDTO); 
    }

    @GetMapping
    public ResponseEntity<List<PublicidadDTO>> read() throws OptionalNotFoundException{
        List<PublicidadDTO> totalDTO = publicidadService.getAll();
        return ResponseEntity.ok(totalDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws OptionalNotFoundException {
        publicidadService.delete(id); 
        return ResponseEntity.ok().build(); 
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PublicidadDTO publiDTO) throws InvalidInputException {
        publicidadService.create(publiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PublicidadDTO> update(@PathVariable Long id, @RequestBody PublicidadDTO nuevoDTO) throws OptionalNotFoundException {
        publicidadService.update(id, nuevoDTO); 
        return ResponseEntity.ok().build(); 
    }
}
