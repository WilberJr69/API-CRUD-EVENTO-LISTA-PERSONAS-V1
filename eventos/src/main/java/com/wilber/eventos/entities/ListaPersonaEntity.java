package com.wilber.eventos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "lista_personas")
public class ListaPersonaEntity {

    @EmbeddedId
    private ListaPersonaId id;

    @ManyToOne
    @MapsId("listaId")// Mapeo del campo de la clave compuesta
    @JoinColumn(name = "lista_id")
    private ListaEntity lista;

    @ManyToOne
    @MapsId("personaId")// Mapeo del campo de la clave compuesta
    @JoinColumn(name = "persona_id")
    @JsonIgnore
    private PersonaEntity persona;

    public ListaPersonaEntity() {}

    public ListaPersonaEntity(ListaEntity lista, PersonaEntity persona) {
        this.lista = lista;
        this.persona = persona;
        this.id = new ListaPersonaId(lista.getListaId(), persona.getPersonaId());
    }



}
