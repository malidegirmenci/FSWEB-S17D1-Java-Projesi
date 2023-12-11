package com.workintech.S17D1.controller;

import com.workintech.S17D1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech/S17D1/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;

    @PostConstruct
    public void loadAll(){
        this.animals = new HashMap<>();
        Animal monkey = new Animal(1,"Monkey");
        this.animals.put(monkey.getId(),monkey);
    }
    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("All animals listed");
        return new ArrayList<>(this.animals.values());
    }
    @GetMapping(path = "/{id}")
    public Animal getAnimal(@PathVariable("id") @NotNull Integer id){
        System.out.println(this.animals.get(id).getName()+" triggered in list");
        return this.animals.get(id);
    }
    @PostMapping
    public void addAnimal(@RequestBody @NotNull Animal animal){
        this.animals.put(animal.getId(),animal);
    }
    @PutMapping(path = "/{id}")
    public Animal updateAnimal(@PathVariable("id") @NotNull Integer id, @RequestBody @NotNull Animal animal){
        this.animals.replace(id,animal);
        return this.animals.get(id);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteAnimal(@PathVariable("id") @NotNull Integer id){
        this.animals.remove(id);
    }
}
