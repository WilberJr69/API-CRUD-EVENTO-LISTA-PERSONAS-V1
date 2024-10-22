package com.wilber.eventos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lista")
public class ListaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lista_id")
    private Integer listaId;

    @Column(name = "nombre_lista")
    private String nombreLista;
    @Column(name = "cantidad_maxima")
    private Integer cantidadMaxima;

    // un evento puede tener varias listas
    @ManyToOne
    @JoinColumn(name = "evento_id")
    @JsonBackReference
    private EventoEntity evento;


    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ListaPersonaEntity> personasEnListas;


}
