package com.wilber.eventos.services;

import com.wilber.eventos.entities.EventoEntity;
import com.wilber.eventos.entities.ListaEntity;
import com.wilber.eventos.repositories.IEventoJpaRepo;
import com.wilber.eventos.repositories.IListaJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListaService {

    @Autowired
    private IListaJpaRepo iListaJpaRepo;

    @Autowired
    private IEventoJpaRepo eventoJpaRepo;


    public List<ListaEntity> getAllListasByEventoId(Integer eventoId){
        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);

        if (eventoOptional.isPresent()){
            EventoEntity evento = eventoOptional.get();
            return evento.getListas();
        }else {
            throw new RuntimeException("No se encontró el evento con id: "+eventoId);
        }
    }


    public ListaEntity getListaByEventoIdAndListaId(Integer eventoId, Integer listaId){
        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);

        if (eventoOptional.isPresent()){
            EventoEntity evento = eventoOptional.get();

            return evento.getListas().stream()
                    .filter(listaEntity -> listaEntity.getListaId().equals(listaId))
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("No se encontró una lista con el ID:" + listaId +" para el evento ID:" + eventoId));
        }else {
            throw new RuntimeException("No se encontró un evento con el ID: " + eventoId);
        }

    }

    public ListaEntity createListaForEvent(ListaEntity nuevaLista, Integer eventoId){

        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);

        if (eventoOptional.isPresent()){

            EventoEntity evento = eventoOptional.get();
            nuevaLista.setEvento(evento); //Establece la relacion con el evento
            ListaEntity listaGuardar = iListaJpaRepo.save(nuevaLista);


            //Estas dos lineas restantes es para actualizar el cuerpo del evento en la bd
            //y poder consultarlo inmediatamente sin tener que recargar la base de datos
            evento.getListas().add(listaGuardar);
            eventoJpaRepo.save(evento);

            return listaGuardar;

        } else {
            throw new RuntimeException("No existe el evento con el id: "+eventoId);
        }

    }



    public ListaEntity updateListaForEvent(ListaEntity listaActualizada, Integer eventoId, Integer listaId){

        //Reutilizamos el metodo getListaByEventoIdAndListaId para encontrar
        //una lista especifica de un evento, en caso no lo encuentre, lanza la excepcion de ese metodo
        ListaEntity listaEncontrada = getListaByEventoIdAndListaId(eventoId, listaId);

        listaEncontrada.setNombreLista(listaActualizada.getNombreLista());
        listaEncontrada.setCantidadMaxima(listaActualizada.getCantidadMaxima());

        return iListaJpaRepo.save(listaEncontrada);
    }


    public void deleteListaForEvento(Integer eventoId,Integer listaId){

        ListaEntity listaEncontrada = getListaByEventoIdAndListaId(eventoId, listaId);

        //Desvinculamos-Eliminamos la referencia
        // de la lista del evento para poder eliminar la lista
        EventoEntity evento = listaEncontrada.getEvento();
        evento.getListas().remove(listaEncontrada);

        //Luego guardamos el evento desvinculado de la referencia de esa lista
        eventoJpaRepo.save(evento);

        //Eliminamos la lista de la base de datos
        iListaJpaRepo.delete(listaEncontrada);


    }







}
