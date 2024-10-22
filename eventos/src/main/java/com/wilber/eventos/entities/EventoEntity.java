package com.wilber.eventos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "evento")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id")
    private Integer eventoId;

    private String nombre;
    private LocalDate dia;
    private Time hora;
    private String lugar;

    // un evento puede tener varias listas
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonBackReference
    private List<ListaEntity> listas;

}
