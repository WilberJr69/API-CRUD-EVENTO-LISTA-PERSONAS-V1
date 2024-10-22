package com.wilber.eventos.repositories;

import com.wilber.eventos.entities.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventoJpaRepo extends JpaRepository<EventoEntity, Integer> {
}
