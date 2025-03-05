package com.prototipo.publicidad.model;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_Publicidad")
public class Publicidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank( message = "The title cannot be empty")
    @Column(name = "title")
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "publicidad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images;    

    @NotBlank( message = "The redirectURL cannot be empty")
    @URL
    private String redirectUrl;

    @NotNull( message = "The active cannot be empty")
    private boolean active;

    @CreationTimestamp
    @Column(name = "AD_CREATED")
    private LocalDateTime createdAd;

    @UpdateTimestamp
    @Column(name = "AD_UPDATED")
    private LocalDateTime updatedAd;
}
