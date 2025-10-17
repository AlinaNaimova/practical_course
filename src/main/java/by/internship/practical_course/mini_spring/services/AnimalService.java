package by.internship.practical_course.mini_spring.services;

import by.internship.practical_course.mini_spring.annotations.Autowired;
import by.internship.practical_course.mini_spring.annotations.Component;
import by.internship.practical_course.mini_spring.model.Animal;
import by.internship.practical_course.mini_spring.repository.AnimalRepository;

import java.util.List;

@Component
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal findById(Long id) {
        return animalRepository.findById(id);
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    public void deleteById(long id) {
        animalRepository.deleteById(id);
    }

    public int countAnimals() {
        return animalRepository.countAnimals();
    }
}
