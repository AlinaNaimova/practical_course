package by.internship.practical_course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;

@Controller
public class TestController {
    @GetMapping("/hello")
    public String hello() {


        LinkedList <String> list = new LinkedList<>();
        list.add("hello");




        return "Hello World!";
    }
}
