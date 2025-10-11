package by.internship.practical_course.sales_and_customer_analysis.service;

import by.internship.practical_course.sales_and_customer_analysis.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SalesAndCustomerAnalysisTest {

    private SalesAndCustomerAnalysis analysis;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private OrderItem laptop;
    private OrderItem phone;
    private OrderItem book;
    private OrderItem shirt;

    @BeforeEach
    void setUp() {
        analysis = new SalesAndCustomerAnalysis();

        customer1 = new Customer("1", "Сергей", "serg@mail.by", LocalDateTime.now(), 33, "Минск");
        customer2 = new Customer("2", " Иван", "ivan@mail.by",  LocalDateTime.now(), 38, "Гродно");
        customer3 = new Customer("2", " Катя", "katia@mail.by",  LocalDateTime.now(), 30, "Минск");

        laptop = new OrderItem("Ноутбук", 1, 2500.0, Category.ELECTRONICS);
        phone = new OrderItem("Телефон", 1, 1500.0, Category.ELECTRONICS);
        book = new OrderItem("Книга", 1, 100.0, Category.BOOKS);
        shirt = new OrderItem("Футболка", 1, 80.0, Category.CLOTHING);
    }

    @Test
    void testGetUniqueCities() {
        Order order1 = new Order("1", LocalDateTime.now(), customer1, List.of(book, book), OrderStatus.DELIVERED);
        Order order2 = new Order("2",  LocalDateTime.now(), customer2, List.of(laptop), OrderStatus.DELIVERED);
        Order order3 = new Order("3", LocalDateTime.now(), customer3, List.of(phone), OrderStatus.DELIVERED);

        Set<String> cities = analysis.getUniqueCities(List.of(order1, order2, order3));
        assertEquals(2, cities.size());
        assertTrue(cities.contains("Минск"));
        assertTrue(cities.contains("Гродно"));
    }

    @Test
    void testGetTotalIncome() {
        Order order1 = new Order("1", LocalDateTime.now(), customer1, List.of(book, book), OrderStatus.DELIVERED);
        Order order2 = new Order("2",  LocalDateTime.now(), customer2, List.of(laptop), OrderStatus.PROCESSING);
        Order order3 = new Order("3", LocalDateTime.now(), customer3, List.of(phone), OrderStatus.CANCELLED);

        double totalIncome = analysis.getTotalIncome(List.of(order1, order2, order3));
        assertEquals(200, totalIncome, 0.001);
    }

    @Test
    void testGetMostPopularProduct() {
        Order order1 = new Order("1", LocalDateTime.now(), customer1, List.of(book, phone), OrderStatus.DELIVERED);
        Order order2 = new Order("2",  LocalDateTime.now(), customer2, List.of(laptop, book, phone), OrderStatus.PROCESSING);
        Order order3 = new Order("3", LocalDateTime.now(), customer3, List.of(phone), OrderStatus.DELIVERED);

        String mostPopularProduct = analysis.getMostPopularProduct(List.of(order1, order2, order3));
        assertEquals("Телефон", mostPopularProduct);
    }

    @Test
    void testGetAverageCheckForDeliveredOrders() {
        Order order1 = new Order("1", LocalDateTime.now(), customer1, List.of(book), OrderStatus.DELIVERED);
        Order order2 = new Order("2",  LocalDateTime.now(), customer2, List.of(shirt), OrderStatus.SHIPPED);
        Order order3 = new Order("3", LocalDateTime.now(), customer3, List.of(phone), OrderStatus.DELIVERED);

        double averageCheckForDeliveredOrders =  analysis.getAverageCheckForDeliveredOrders(List.of(order1, order2, order3));
        assertEquals(800.0, averageCheckForDeliveredOrders, 0.001);
    }

    @Test
    void testGetCustomerWithMoreThanFiveOrders() {
        Order order1 = new Order("1", LocalDateTime.now(), customer1, List.of(book), OrderStatus.DELIVERED);
        Order order2 = new Order("2",  LocalDateTime.now(), customer2, List.of(shirt), OrderStatus.DELIVERED);
        Order order3 = new Order("3", LocalDateTime.now(), customer3, List.of(laptop), OrderStatus.DELIVERED);
        Order order4 = new Order("1", LocalDateTime.now(), customer1, List.of(shirt), OrderStatus.DELIVERED);
        Order order5 = new Order("2",  LocalDateTime.now(), customer1, List.of(laptop), OrderStatus.DELIVERED);
        Order order6 = new Order("3", LocalDateTime.now(), customer1, List.of(phone), OrderStatus.DELIVERED);
        Order order7 = new Order("1", LocalDateTime.now(), customer1, List.of(book), OrderStatus.DELIVERED);
        Order order8 = new Order("2",  LocalDateTime.now(), customer2, List.of(shirt), OrderStatus.DELIVERED);
        Order order9 = new Order("3", LocalDateTime.now(), customer1, List.of(phone), OrderStatus.DELIVERED);

        List<Customer> customers = analysis.getCustomerWithMoreThanFiveOrders(List.of(order1, order2, order3, order4, order5, order6, order7, order8, order9
        ));
        assertEquals(1, customers.size());
        assertEquals(customer1, customers.get(0));
    }
}