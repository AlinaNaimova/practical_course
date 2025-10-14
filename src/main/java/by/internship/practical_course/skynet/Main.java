package by.internship.practical_course.skynet;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        World world = new World(storage);
        Wednesday wednesday = new Wednesday(storage);
        Factory factory = new Factory(storage);

        Thread worldThread = new Thread(world);
        Thread wednesdayThread = new Thread(wednesday);
        Thread factoryThread = new Thread(factory);

        factoryThread.start();

        // даем фабрике немного поработать сначала
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worldThread.start();
        wednesdayThread.start();

        try {
            factoryThread.join();
            worldThread.join();
            wednesdayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== ИТОГИ ===");
        System.out.println(world.getName() + ": " + world.getRobotsBuilt() + " роботов");
        System.out.println(wednesday.getName() + ": " + wednesday.getRobotsBuilt() + " роботов");

        if(world.getRobotsBuilt() > wednesday.getRobotsBuilt()) {
            System.out.println("ПОБЕДИТЕЛЬ: " + world.getName());
        } else if (world.getRobotsBuilt() < wednesday.getRobotsBuilt()) {
            System.out.println("ПОБЕДИТЕЛЬ: " + wednesday.getName());
        } else {
            System.out.println("НИЧЬЯ!");
        }
    }
}
