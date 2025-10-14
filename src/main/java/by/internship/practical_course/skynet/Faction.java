package by.internship.practical_course.skynet;

public class Faction implements Runnable{
    protected String name;
    protected Storage storage;
    protected int heads = 0;
    protected int torsos = 0;
    protected int hands = 0;
    protected int feet = 0;
    protected int robotsBuilt = 0;

    public Faction(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int night = 0; night < 100; night++) {
            takeBodyPartsFromStorage();
            buildRobots();

            // имитация реального времени с паузой 200 милисекунд
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void takeBodyPartsFromStorage() {
        int bodyPartsTaken = 0;
        String[] bodyParts = {"head", "torso", "hand", "foot"};

        for (String partType : bodyParts) {
            while (bodyPartsTaken < 5 && storage.takePartOfBody(partType)) {
                addPartToInventory(partType);
                bodyPartsTaken++;
                System.out.println(name + " забрал " + partType + " (" + bodyPartsTaken + "/5)");
            }
            if (bodyPartsTaken >= 5) break;
        }

        if (bodyPartsTaken > 0) {
            System.out.println(name + " взял " + bodyPartsTaken + " деталей за эту ночь");
        } else {
            System.out.println(name + " не смог взять детали - склад пуст");
        }
    }

    protected void addPartToInventory(String partType) {
        switch (partType) {
            case "head": heads++; break;
            case "torso": torsos++; break;
            case "hand": hands++; break;
            case "foot": feet++; break;
        }
    }

    protected void buildRobots() {
        int possibleRobots = Math.min(
        Math.min(heads, torsos),
        Math.min(hands/2, feet/2)
        );

        if(possibleRobots > 0) {
            heads -= possibleRobots;
            torsos -= possibleRobots;
            hands -= possibleRobots * 2;
            feet -= possibleRobots * 2;
            robotsBuilt += possibleRobots;

            System.out.println(name + " собрал " + possibleRobots + " роботов! " +
                    "(Всего: " + robotsBuilt + ")");
        }
    }

    public String getName() {
        return name;
    }

    public int getRobotsBuilt() {
        return robotsBuilt;
    }
}
