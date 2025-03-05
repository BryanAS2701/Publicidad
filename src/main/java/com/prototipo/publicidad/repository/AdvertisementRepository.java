package com.prototipo.publicidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prototipo.publicidad.model.Publicidad;

public interface AdvertisementRepository extends JpaRepository<Publicidad, Long>{
    
} 