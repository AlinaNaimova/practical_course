package by.internship.practical_course.mini_spring;

import by.internship.practical_course.mini_spring.context.MiniSpringApplicationContext;
import by.internship.practical_course.mini_spring.model.Animal;
import by.internship.practical_course.mini_spring.services.AnimalService;

public class AnimalApplication {
    public static void main(String[] args) {

        System.out.println("------Запускаем mini spring------");
        MiniSpringApplicationContext context = new MiniSpringApplicationContext("by.internship.practical_course.mini_spring");

        System.out.println("\n  ------Получаем сервис------");
        AnimalService animalService = context.getBean(AnimalService.class);

        System.out.println("\n++++++Добавляем новое животное++++++");
        Animal newAnimal = new Animal(null, "Кеша", "попугай", 2);
        Animal savedAnimal = animalService.save(newAnimal);
        System.out.println("Добавлено новое животное: " + savedAnimal);

        System.out.println("\n======Считаем животных======");
        int animalCount = animalService.countAnimals();
        System.out.println("Всего животных: " + animalCount);

        System.out.println("\n++++++Добавляем новое животное++++++");
        Animal newAnimal1= new Animal(null, "Кисуля", "кошка", 8);
        Animal savedAnimal1 = animalService.save(newAnimal1);
        System.out.println("Добавлено новое животное: " + savedAnimal1);

        System.out.println("------>Ищем животное по айди<------");
        Animal foundAnimal = animalService.findById(5L);;
        if (foundAnimal != null) {
            System.out.println("Найдено животное: " + foundAnimal);
        } else {
            System.out.println("Животное с id 5 не найдено");
        }

        System.out.println("\n/-----Показываем всех животных-----/");
        animalService.findAll().forEach(System.out::println);

        System.out.println("\n------Удаляем животное------");
        animalService.deleteById(4L);

        System.out.println("\n++++++Считаем животных++++++");
        int animalCount1 = animalService.countAnimals();
        System.out.println("Всего животных: " + animalCount1);

        System.out.println("\n------Проверяем после удаления------");
        animalService.findAll().forEach(System.out::println);

        System.out.println("\n------Mini Spring успешно завершил работу------");
    }
}
