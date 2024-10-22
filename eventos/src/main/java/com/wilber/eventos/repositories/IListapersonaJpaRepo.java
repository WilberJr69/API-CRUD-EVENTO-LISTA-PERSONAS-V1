package com.wilber.eventos.repositories;

import com.wilber.eventos.entities.ListaPersonaEntity;
import com.wilber.eventos.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IListapersonaJpaRepo extends JpaRepository<ListaPersonaEntity, Integer> {

    List<ListaPersonaEntity> findByPersona(PersonaEntity persona);

}
