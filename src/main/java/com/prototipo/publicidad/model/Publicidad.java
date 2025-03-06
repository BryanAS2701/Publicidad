package com.prototipo.publicidad.model;


import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_Publicidad")
public class Publicidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank( message = "The title cannot be empty")
    @Column(name = "title")
    private String title;
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
    @URL
    private String imageHorizontalLarge;
    @URL
    private String imageHorizontalSmall;
    @URL
    private String imageVerticalLarge;
    @URL
    private String imageVerticalSmall;
}
