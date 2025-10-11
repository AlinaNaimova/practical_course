package by.internship.practical_course.sales_and_customer_analysis.service;

import by.internship.practical_course.sales_and_customer_analysis.model.Customer;
import by.internship.practical_course.sales_and_customer_analysis.model.Order;
import by.internship.practical_course.sales_and_customer_analysis.model.OrderItem;
import by.internship.practical_course.sales_and_customer_analysis.model.OrderStatus;

import java.util.*;
import java.util.stream.Collectors;

public class SalesAndCustomerAnalysis {

    //List of unique cities where orders came from
    public Set<String> getUniqueCities(List<Order> orders) {
        return orders.stream().map(order -> order.getCustomer().getCity()).filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    //Total income for all completed orders
    public double getTotalIncome(List<Order> orders) {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
    }

    //The most popular product by sales
    public String getMostPopularProduct(List<Order> orders) {
        Map <String, Integer> soldProducts = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems()
                        .stream()).collect(Collectors.groupingBy(OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)));
        return soldProducts.entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("no data");
    }


    //Average check for successfully delivered orders
    public double getAverageCheckForDeliveredOrders (List<Order> orders) {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .average().orElse(0.0);
    }

    //Customers who have more than 5 orders
    public List <Customer> getCustomerWithMoreThanFiveOrders(List<Order> orders) {
        Map <Customer, Long> customerOrderCount = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()));
        return customerOrderCount.entrySet().stream().filter(entry -> entry.getValue() > 5)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }
}

