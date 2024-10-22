package com.wilber.eventos.controllers;

import com.wilber.eventos.entities.ListaPersonaEntity;
import com.wilber.eventos.entities.PersonaEntity;
import com.wilber.eventos.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personaApi")
public class PersonaController {


    @Autowired
    private PersonaService personaService;

    @PostMapping("/createPersona")
    public ResponseEntity<PersonaEntity> createPersona(
            @RequestBody PersonaEntity personaEntity
    ){
        return new ResponseEntity<>(personaService.createPersona(personaEntity), HttpStatus.CREATED);
    }

    @PostMapping("/asignarPersonaAEvento/evento/{eventoId}/lista/{listaId}/persona/{personaId}")
    public ResponseEntity<ListaPersonaEntity> agregarPersonaAListaDeEvento(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId,
            @PathVariable Integer personaId
    ){

        return new ResponseEntity<>(
                personaService.agregarPersonaAListaDeEvento(eventoId, listaId, personaId),
                HttpStatus.CREATED);
    }

    @GetMapping("/personasEnLista/evento/{eventoId}/lista/{listaId}")
    public ResponseEntity<List<PersonaEntity>> obtenerPersonasDeLista(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId
    ){
        return new ResponseEntity<>(personaService.obtenerPersonasDeLista(eventoId, listaId), HttpStatus.OK);
    }

    @PutMapping("/updatePersona/persona/{personaId}")
    public ResponseEntity<PersonaEntity> updatePersona(
            @RequestBody PersonaEntity personaActulizada,
            @PathVariable Integer personaId
    ){
        return new ResponseEntity<>(personaService.editarDatosPersona(personaActulizada, personaId), HttpStatus.OK);
    }

    @DeleteMapping("/deletePersona/persona/{personaId}")
    public ResponseEntity<Void> deletePersona(
            @PathVariable Integer personaId
    ){
        personaService.deletePersona(personaId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/deletePersona/evento/{eventoId}/lista/{listaId}/persona/{personaId}")
    public ResponseEntity<Void> deletePersonaByEventoIdAndListaId(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId,
            @PathVariable Integer personaId
    ){
        personaService.deletePersonaByEventoIdAndListaId(eventoId, listaId, personaId);
        return ResponseEntity.noContent().build();
    }

}
