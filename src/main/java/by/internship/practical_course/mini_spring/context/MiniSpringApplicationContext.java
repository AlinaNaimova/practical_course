package by.internship.practical_course.mini_spring.context;

import by.internship.practical_course.mini_spring.annotations.Autowired;
import by.internship.practical_course.mini_spring.annotations.Component;
import by.internship.practical_course.mini_spring.annotations.InitializingBean;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MiniSpringApplicationContext {
    // место, где будем искать аннотации
    private final String basePackage;
    // мапа для хранения бинов
    private final Map<Class<?>, Object> beanMap = new HashMap<>();

    public MiniSpringApplicationContext(String basePackage) {
        this.basePackage = basePackage;
        scanAndCreateBeans();
        injectDependencies();
        initializeBeans();
    }

    private void scanAndCreateBeans() {
        try {
            String path = basePackage.replace('.', '/');

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File directory = new File(classLoader.getResource(path).getFile());

            if (directory.exists()) {
                scanDirectory(directory, basePackage);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to scan package: " + basePackage, e);
        }
    }

    private void scanDirectory(File directory, String basePackage) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                scanDirectory(file, basePackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                // отрезаем .class у имени класса
                String clearClassName = file.getName().substring(0, file.getName().length() - 6);
                // полное имя класса
                String className = basePackage + '.' + clearClassName;
                try {
                    // находим класс по полному имени
                    Class<?> clas = Class.forName(className);
                    if (clas.isAnnotationPresent(Component.class)) {
                        Object object = clas.getDeclaredConstructor().newInstance();
                        beanMap.put(clas, object);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to load class: " + className);
                }
            }
        }
    }

    private void injectDependencies() {
        for (Object bean : beanMap.values()) {
            injectDependencies(bean);
        }
    }

    private void injectDependencies(Object bean) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                Object dependency = beanMap.get(fieldType);
                if (dependency == null) {
                    throw new RuntimeException("Dependency not found for field: " + field.getName());
                }

                try {
                    field.set(bean, dependency);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency", e);
                }
            }
        }
    }

    private void initializeBeans() {
        for (Object bean : beanMap.values()) {
            if (bean instanceof InitializingBean) {
                try {
                    ((InitializingBean) bean).afterPropertiesSet();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to initialize bean", e);
                }
            }
        }
    }

    public <T> T getBean(Class<T> type) {
        Object bean = beanMap.get(type);
        if (bean == null) {
            throw new RuntimeException("Bean not found: " + type.getName());
        }
        return (T) bean;
    }
}
