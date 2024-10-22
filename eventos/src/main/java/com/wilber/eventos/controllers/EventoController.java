package com.wilber.eventos.controllers;

import com.wilber.eventos.entities.EventoEntity;
import com.wilber.eventos.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventoApi")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @GetMapping("/getAllEventos")
    public ResponseEntity<List<EventoEntity>> getAllEventos(){
        return new ResponseEntity<>(eventoService.getAllEventos(), HttpStatus.OK);
    }

    @GetMapping("/getEvento/{eventoId}")
    public ResponseEntity<Optional<EventoEntity>> getEventoById(
            @PathVariable Integer eventoId
    ){
        return new ResponseEntity<>(eventoService.getEventoById(eventoId),HttpStatus.OK);
    }

    @PostMapping("/createEvento")
    public ResponseEntity<EventoEntity> createEvento(
            @RequestBody EventoEntity eventoEntity)
    {
        return new ResponseEntity<>(eventoService.createEvento(eventoEntity), HttpStatus.CREATED);
    }

    @PutMapping("/editEvento/evento/{eventoId}")
    public ResponseEntity<EventoEntity> editEvento(
            @PathVariable Integer eventoId,
            @RequestBody EventoEntity eventoEntity
    ){
        return new ResponseEntity<>(eventoService.editEvento(eventoId, eventoEntity),HttpStatus.OK);
    }

    @DeleteMapping("/deleteEvento/evento/{eventoId}")
    public void deleteEvento(
            @PathVariable Integer eventoId
    ){
        eventoService.deleteEvento(eventoId);
    }


}
