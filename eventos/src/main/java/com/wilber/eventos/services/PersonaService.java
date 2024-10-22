package com.wilber.eventos.services;

import com.wilber.eventos.entities.EventoEntity;
import com.wilber.eventos.entities.ListaEntity;
import com.wilber.eventos.entities.ListaPersonaEntity;
import com.wilber.eventos.entities.PersonaEntity;
import com.wilber.eventos.repositories.IEventoJpaRepo;
import com.wilber.eventos.repositories.IListaJpaRepo;
import com.wilber.eventos.repositories.IListapersonaJpaRepo;
import com.wilber.eventos.repositories.IPersonaJpaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaJpaRepo personaJpaRepo;

    @Autowired
    private IEventoJpaRepo eventoJpaRepo;

    @Autowired
    private IListaJpaRepo listaJpaRepo;

    @Autowired
    private IListapersonaJpaRepo listapersonaJpaRepo;


    public PersonaEntity createPersona(PersonaEntity personaEntity){
        return personaJpaRepo.save(personaEntity);
    }

    public ListaPersonaEntity agregarPersonaAListaDeEvento(
            Integer eventoId,
            Integer listaId,
            Integer personaId
    ){

        // Verificar si el evento existe
        Optional<EventoEntity> eventoOptional =eventoJpaRepo.findById(eventoId);
        if (!eventoOptional.isPresent()){
            throw new RuntimeException("Evento no encontrado con ID: " + eventoId);
        }

        // Verificar si la lista existe
        Optional<ListaEntity> listaOptional = listaJpaRepo.findById(listaId);
        if (!listaOptional.isPresent()){
            throw new RuntimeException("Lista no encontrada con ID: " + listaId);
        }

        ListaEntity lista = listaOptional.get();
        EventoEntity evento = eventoOptional.get();

        // Verificar si la lista pertenece al evento
        if (!lista.getEvento().getEventoId().equals(evento.getEventoId())) {
            throw new RuntimeException("La lista con ID: " + listaId + " no pertenece al evento con ID: " + eventoId);
        }

        // Verificar si la persona existe
        Optional<PersonaEntity> personaOptional = personaJpaRepo.findById(personaId);
        if (!personaOptional.isPresent()) {
            throw new RuntimeException("Persona no encontrada con ID: " + personaId);
        }

        PersonaEntity persona = personaOptional.get();

        // Crear la relación en la tabla intermedia
        ListaPersonaEntity listaPersona = new ListaPersonaEntity(lista, persona);
        return listapersonaJpaRepo.save(listaPersona);


    }

    public List<PersonaEntity> obtenerPersonasDeLista(
            Integer eventoId,
            Integer listaId
    ){
        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);
        if (!eventoOptional.isPresent()){
            throw new RuntimeException("Evento no encontrado con el ID: "+eventoId);
        }

        Optional<ListaEntity> listaOptional = listaJpaRepo.findById(listaId);
        if (!listaOptional.isPresent()){
            throw new RuntimeException("Lista no encontrada con el ID: "+listaId);
        }

        EventoEntity evento = eventoOptional.get();
        ListaEntity lista = listaOptional.get();

        if (!lista.getEvento().getEventoId().equals(evento.getEventoId())){
            throw new RuntimeException("La lista con ID: " + listaId + " no pertenece al evento con ID: " + eventoId);
        }

        // Devolver todas las personas asociadas a la lista
        return lista.getPersonasEnListas().stream()
                .map(ListaPersonaEntity::getPersona)
                .toList();


    }


    public PersonaEntity editarDatosPersona(PersonaEntity personaActualizada, Integer personaId){

        Optional<PersonaEntity> personaOptional = personaJpaRepo.findById(personaId);

        if (!personaOptional.isPresent()){
            throw new RuntimeException("No se encuentra la persona con el ID: "+personaId);
        }

        PersonaEntity personaGuardar = personaOptional.get();
        personaGuardar.setNombres(personaActualizada.getNombres());
        personaGuardar.setApellidos(personaActualizada.getApellidos());
        personaGuardar.setNumeroDni(personaActualizada.getNumeroDni());


        return personaJpaRepo.save(personaGuardar);

    }

    @Transactional
    public void deletePersona(Integer personaId){

        Optional<PersonaEntity> personaOptional = personaJpaRepo.findById(personaId);
        if (!personaOptional.isPresent()) {
            throw new RuntimeException("Persona no encontrada con ID: " + personaId);
        }

        PersonaEntity persona = personaOptional.get();


        personaJpaRepo.delete(persona);
    }


    @Transactional
    public void deletePersonaByEventoIdAndListaId(
            Integer eventoId,
            Integer listaId,
            Integer personaId
    ){

        Optional<PersonaEntity> personaOptional = personaJpaRepo.findById(personaId);
        if (!personaOptional.isPresent()){
            throw new RuntimeException("No se encuentra la persona con ID: "+personaId);
        }

        Optional<EventoEntity> eventoOptional = eventoJpaRepo.findById(eventoId);
        if (!eventoOptional.isPresent()){
            throw new RuntimeException("No se encuentra el evento con el ID: "+eventoId);
        }


        Optional<ListaEntity> listaOptional = listaJpaRepo.findById(listaId);
        if (!listaOptional.isPresent()){
            throw new RuntimeException("No se encuentra la lista con el ID: "+listaId);
        }

        PersonaEntity persona = personaOptional.get();
        ListaEntity lista = listaOptional.get();
        EventoEntity evento = eventoOptional.get();

        if (!lista.getEvento().getEventoId().equals(evento.getEventoId())) {
            throw new RuntimeException("La lista con ID: " + listaId + " no pertenece al evento con ID: " + eventoId);
        }

        // Verificar si la persona está vinculada a la lista
        Optional<ListaPersonaEntity> listaPersonaOptional = lista.getPersonasEnListas().stream()
                .filter(lp -> lp.getPersona().getPersonaId().equals(personaId))
                .findFirst();
        if (!listaPersonaOptional.isPresent()) {
            throw new RuntimeException("La persona con ID: " + personaId + " no está vinculada a la lista con ID: " + listaId);
        }

        // Eliminar la relación en la tabla intermedia
        lista.getPersonasEnListas().remove(listaPersonaOptional.get());
        listapersonaJpaRepo.delete(listaPersonaOptional.get());

        // Eliminar la persona después de limpiar las relaciones
        personaJpaRepo.delete(persona);





    }


}
