package com.wilber.eventos.controllers;

import com.wilber.eventos.entities.ListaEntity;
import com.wilber.eventos.repositories.IListaJpaRepo;
import com.wilber.eventos.services.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listaApi")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping("/getAllListas/evento/{eventoId}")
    public ResponseEntity<List<ListaEntity>> getAllListasByEventoId(
            @PathVariable Integer eventoId)
    {
        return new ResponseEntity<>(listaService.getAllListasByEventoId(eventoId), HttpStatus.OK);
    }

    @GetMapping("/getLista/evento/{eventoId}/lista/{listaId}")
    public ResponseEntity<ListaEntity> getListaByEventoIdAndListaId(
          @PathVariable Integer eventoId,
          @PathVariable Integer listaId
    )
    {
        return new ResponseEntity<>(listaService.getListaByEventoIdAndListaId(eventoId, listaId), HttpStatus.OK);
    }


    @PostMapping("/createLista/evento/{eventoId}")
    public ResponseEntity<ListaEntity> createListaForEvento(
            @RequestBody ListaEntity listaEntity,
            @PathVariable Integer eventoId
    )
    {
        return new ResponseEntity<>(listaService.createListaForEvent(listaEntity,eventoId), HttpStatus.CREATED);
    }


    @PutMapping("/updateLista/evento/{eventoId}/lista/{listaId}")
    public ResponseEntity<ListaEntity> updateListaForEvento(
            @RequestBody ListaEntity listaActualizada,
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId
    ) {
        return new ResponseEntity<>(listaService.updateListaForEvent(listaActualizada, eventoId, listaId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteLista/evento/{eventoId}/lista/{listaId}")
    public ResponseEntity<Void> deleteListaForEvento(
            @PathVariable Integer eventoId,
            @PathVariable Integer listaId
    ) {
        listaService.deleteListaForEvento(eventoId, listaId);
        return ResponseEntity.noContent().build(); // Retornamos 204 No Content al eliminar
    }




}
