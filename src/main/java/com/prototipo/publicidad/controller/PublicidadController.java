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

    @GetMapping("/consultar/{id}")
    public ResponseEntity<PublicidadDTO> getPublicidad(@PathVariable Long id) throws OptionalNotFoundException {
        PublicidadDTO publicidadDTO = publicidadService.consultar(id);
        return new ResponseEntity<>(publicidadDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PublicidadDTO>> getAll() throws OptionalNotFoundException{
        List<PublicidadDTO> totalDTO = publicidadService.getAll();
        return new ResponseEntity<>(totalDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws OptionalNotFoundException {
        PublicidadDTO deletedPublicidad = publicidadService.delete(id);
        return new ResponseEntity<>(deletedPublicidad, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PublicidadDTO publiDTO) throws InvalidInputException {
        PublicidadDTO createdPublicidad = publicidadService.create(publiDTO);
        return new ResponseEntity<>(createdPublicidad, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/image/{imageId}")
    public ResponseEntity<PublicidadDTO> update(@PathVariable Long id, @PathVariable Long imageId, @RequestBody PublicidadDTO nuevoDTO) {
        try {
            PublicidadDTO updatedPublicidad = publicidadService.update(id, imageId, nuevoDTO);
            return ResponseEntity.ok(updatedPublicidad);
        } catch (OptionalNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
