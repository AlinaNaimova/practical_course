package by.internship.practical_course.skynet;

public class Storage {
    private int heads = 0;
    private int torsos = 0;
    private int hands = 0;
    private int feet = 0;


    // так здесь мы добавляем части тела на склад
    public synchronized void addPartOfBody(String PartOfBody) {
        if("head".equals(PartOfBody)) {
            heads++;
            System.out.println("Добавлена ГОЛОВА. Всего голов: " + heads);
        }   else if("torso".equals(PartOfBody)) {
            torsos++;
            System.out.println("Добавлен ТОРС. Всего торсов: " + torsos);
        }   else if("hand".equals(PartOfBody)) {
            hands++;
            System.out.println("Добавлена РУКА. Всего рук: " + hands);
        }   else if("foot".equals(PartOfBody)) {
            feet++;
            System.out.println("Добавлена НОГА. Всего ног: " + feet);
        }
    }

    // а здесь фракция забирает часть тела из склада
    public synchronized boolean takePartOfBody(String PartOfBody) {
        if("head".equals(PartOfBody) && heads > 0) {
            heads--;
            System.out.println("Забрана ГОЛОВА со склада. Осталось: " + heads);
            return true;
        }   else if("torso".equals(PartOfBody) && torsos > 0) {
            torsos--;
            System.out.println("Забран ТОРС со склада. Осталось: " + torsos);
            return true;
        }   else if("hand".equals(PartOfBody) && hands > 0) {
            hands--;
            System.out.println("Забрана РУКА со склада. Осталось: " + hands);
            return true;
        }   else if("foot".equals(PartOfBody) && feet > 0) {
            feet--;
            System.out.println("Забрана НОГА со склада. Осталось: " + feet);
            return true;
        }
        return false;
    }
}
