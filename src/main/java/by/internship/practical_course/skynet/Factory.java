package by.internship.practical_course.skynet;

import java.util.Random;


public class Factory implements Runnable {
    private Storage storage;
    private Random random;
    private String[] bodyParts = {"head", "torso", "hand", "foot"};

    public Factory(Storage storage) {
        this.storage = storage;
        this.random = new Random();
    }
    @Override
    public void run() {
        for (int day = 0; day < 100; day++) {
            int partToProduce = random.nextInt(11);
            for (int i = 1; i <= partToProduce; i++) {
                String randomBodyPart = bodyParts[random.nextInt(bodyParts.length)];
                storage.addPartOfBody(randomBodyPart);
            }

            // имитация реального времени с паузой 200 милисекунд
            try {
                Thread.sleep(200);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}