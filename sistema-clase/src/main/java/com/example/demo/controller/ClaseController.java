package com.example.demo.controller;


import com.example.demo.exception.AulaNotFoundException;
import com.example.demo.model.Clase;
import com.example.demo.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class ClaseController {

    @Autowired
    private ClaseRepository ClaseRepository;

    @PostMapping("/Clase")
    Clase newClase (@RequestBody Clase newClase){
        return ClaseRepository.save(newClase);
    }
    @GetMapping("/Clases")
    List<Clase> getAllClase(){
        return ClaseRepository.findAll();
    }

    @GetMapping("Clase/{id}")
    Clase getAulaById(@PathVariable Long id) {
        return ClaseRepository.findById(id)
                .orElseThrow(()->new AulaNotFoundException(id));
    }

    @PutMapping("/Clase/{id}")
    Clase updateClase(@RequestBody Clase newClase, @PathVariable Long id) {
        return ClaseRepository.findById(id)
                .map(Clase -> {
                    Clase.setHora_inicio(newClase.getHora_inicio());
                    Clase.setHora_fin(newClase.getHora_fin());
                    Clase.setMateria(newClase.getMateria());
                    Clase.setProfesor(newClase.getProfesor());
                    Clase.setCarrera(newClase.getCarrera());
                    Clase.setSede(newClase.getSede());
                    Clase.setAula(newClase.getAula());
                    return ClaseRepository.save(Clase);
                }).orElseThrow(() -> new AulaNotFoundException(id));
    }

    @DeleteMapping("/Clase/{id}")
    String deleteAula(@PathVariable Long id) {
        if (!ClaseRepository.existsById(id)) {
            throw new AulaNotFoundException(id);
        }
        ClaseRepository.deleteById(id);
        return "Clase con el id" + id + " ha sido eliminado con exito.";
    }
}
