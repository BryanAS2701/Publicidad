package com.prototipo.publicidad.model;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_image")
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size")
    @NotBlank(message = "The size cannot be empty")
    @Pattern(regexp = "(?i)MEDIUM|LARGE", message = "The size must be 'MEDIUM' or 'LARGE'")
    private String size; 

    @Column(name = "position")
    @NotBlank(message = "The position cannot be empty")
    @Pattern(regexp = "(?i)HORIZONTAL|VERTICAL", message = "Position must be 'HORIZONTAL' or 'VERTICAL'")
    private String position;

    @Column(name = "url", columnDefinition = "TEXT")
    @URL 
    private String url;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "publicidad_id")
    @JsonBackReference 
    private Publicidad publicidad; 
}
