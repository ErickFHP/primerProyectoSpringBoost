package com.api.primerProyecto.controllers;

import com.api.primerProyecto.models.TareaModel;
import com.api.primerProyecto.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController //Si es Controller entonces no se puede ver los JSON
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping
    public ArrayList<TareaModel> getTareas(){
        return this.tareaService.getTareas();
    }

    @PostMapping
    public TareaModel saveTarea(@RequestBody TareaModel tarea){
        return this.tareaService.saveTarea(tarea);
    }

    @GetMapping(path = "/{id}")
    public Optional<TareaModel> getTareaById(@PathVariable("id") Long id){
        return this.tareaService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public TareaModel updateTareaByID(@RequestBody TareaModel request, Long id){
        return this.tareaService.updateById(request, id);
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<?> completarTarea(@PathVariable("id") Long id){
        try{
            TareaModel tarea = this.tareaService.completarTarea(id);
            return ResponseEntity.ok(tarea);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/listar") //Solamente si no es RestController -> Controller
    public String listar(Model model){
        model.addAttribute("tareas", this.tareaService.getTareas());
        return "tareas";
    }
}
