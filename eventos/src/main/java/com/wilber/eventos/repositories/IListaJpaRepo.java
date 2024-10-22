package com.wilber.eventos.repositories;

import com.wilber.eventos.entities.ListaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IListaJpaRepo extends JpaRepository<ListaEntity,Integer> {



}
