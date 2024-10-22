package com.wilber.eventos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class PersonaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Integer personaId;

    private String nombres;
    private String apellidos;

    @Column(name = "numero_dni")
    private Integer numeroDni;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ListaPersonaEntity> listaDePersonas;




}
