package com.wilber.eventos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListaPersonaId implements Serializable{

    @Column(name = "lista_id")
    private Integer listaId;

    @Column(name = "persona_id")
    private Integer personaId;

    public ListaPersonaId() {}

    public ListaPersonaId(Integer listaId, Integer personaId) {
        this.listaId = listaId;
        this.personaId = personaId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaPersonaId that = (ListaPersonaId) o;
        return Objects.equals(listaId, that.listaId) &&
                Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(listaId, personaId);
    }



}
