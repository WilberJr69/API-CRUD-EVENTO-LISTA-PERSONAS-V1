package com.wilber.eventos.repositories;

import com.wilber.eventos.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaJpaRepo extends JpaRepository<PersonaEntity, Integer> {
}
