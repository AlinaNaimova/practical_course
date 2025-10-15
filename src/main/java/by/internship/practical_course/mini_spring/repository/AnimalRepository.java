package by.internship.practical_course.mini_spring.repository;

import by.internship.practical_course.mini_spring.annotations.Component;
import by.internship.practical_course.mini_spring.annotations.InitializingBean;
import by.internship.practical_course.mini_spring.model.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnimalRepository implements InitializingBean {
    private final Map<Long, Animal> animals = new HashMap<>();
    private Long nextId = 1L;

    public Animal findById(Long id) {
        return animals.get(id);
    }

    public List<Animal> findAll() {
        return new ArrayList<>(animals.values());
    }

    public Animal save(Animal animal) {
        if (animal.getId() == null) {
            animal.setId(nextId++);
        }
        animals.put(animal.getId(), animal);
        return animal;
    }

    public void deleteById(long id) {
        animals.remove(id);
    }

    public int countAnimals() {
        return animals.size();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        save(new Animal( 1L, "Мусик","кот", 2));
        save(new Animal( 2L, "Баксик","собака", 5));
        save(new Animal( 3L, "Мурка","кошка", 1));
        save(new Animal( 4L, "Мюкла","мышь", 7));

        nextId = 5L;

        System.out.println("AnimalRepository инициализирован с " + animals.size() + " животными");
        System.out.println("Тестовые животные:");
        animals.values().forEach(System.out::println);
    }
}
