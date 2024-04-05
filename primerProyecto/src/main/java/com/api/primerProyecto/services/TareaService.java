package com.api.primerProyecto.services;

import com.api.primerProyecto.models.TareaModel;
import com.api.primerProyecto.repositories.ITareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TareaService {
    @Autowired
    ITareaRepository tareaRepository;

    public ArrayList<TareaModel> getTareas(){
        return (ArrayList<TareaModel>) tareaRepository.findAll();
    }

    public TareaModel saveTarea(TareaModel tarea){
        return tareaRepository.save(tarea);
    }

    public Optional<TareaModel> getById(Long id){
        return tareaRepository.findById(id);
    }

    public TareaModel updateById(TareaModel request, Long id){
        TareaModel tarea = tareaRepository.findById(id).get();

        tarea.setTitulo(request.getTitulo());
        tarea.setDescr(request.getDescr());
        tarea.setEstado(request.getEstado());
        tarea.setPrioridad(request.getPrioridad());

        return tarea;
    }

    public TareaModel completarTarea(Long id){
        Optional<TareaModel> tareaOptional = tareaRepository.findById(id);
        if (tareaOptional.isPresent()){
            TareaModel tarea = tareaOptional.get();
            tarea.setEstado("completada");
            return tareaRepository.save(tarea);
        } else {
            throw new RuntimeException("Tarea no encontrada");
        }
    }
}
