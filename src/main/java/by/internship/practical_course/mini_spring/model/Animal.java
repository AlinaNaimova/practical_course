package by.internship.practical_course.mini_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Animal {
    private Long id;
    private String name;
    private String type;
    private int age;


}
