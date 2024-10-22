package com.wilber.eventos.services;

import com.wilber.eventos.entities.EventoEntity;
import com.wilber.eventos.repositories.IEventoJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    IEventoJpaRepo eventoJpaRepo;

    public List<EventoEntity> getAllEventos(){
        return eventoJpaRepo.findAll();
    }

    public Optional<EventoEntity> getEventoById(Integer eventoId){
        return eventoJpaRepo.findById(eventoId);
    }

    public EventoEntity createEvento(EventoEntity eventoEntity){
        return eventoJpaRepo.save(eventoEntity);
    }

    public EventoEntity editEvento(Integer eventoId, EventoEntity eventoEditado){
        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);

        if (eventoOptional.isPresent()){
            EventoEntity eventoExistente = eventoOptional.get();
            eventoExistente.setDia(eventoEditado.getDia());
            eventoExistente.setHora(eventoEditado.getHora());
            eventoExistente.setNombre(eventoEditado.getNombre());
            eventoExistente.setLugar(eventoEditado.getLugar());
            return eventoJpaRepo.save(eventoExistente);
        }else {
            throw new RuntimeException("No se encontró evento con esa ID");
        }
    }


    public void deleteEvento(Integer eventoId){
        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);

        if (eventoOptional.isPresent()){
            eventoJpaRepo.deleteById(eventoId);
        }else {
            throw new RuntimeException("No se puede eliminar evento con id: "+eventoId+" ya que no existe");
        }

    }


}
