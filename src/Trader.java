import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trader{
    private String name;
    private int posion;
    private final String posion1 = "Очень сильный";
    private final String posion2 = "Сильный";

    public Trader(String name) {
        this.name = name;
        posion = 4;
    }

    public String getName() {
        return name;
    }

    public int getPosion() {
        return posion;
    }

    public String getPosion1() {
        return posion1;
    }

    public String getPosion2() {
        return posion2;
    }

    public void setPosion(int posion) {
        if (this.posion <= 0) {
            this.posion = 0;
            System.out.println("Зелье закончилось!");
        }
        this.posion = posion;
    }
    public static void sellPosion(Participant heroe, Participant monster, Trader trader) {
        System.out.println(heroe.getName() + ", чтобы продолжать сражаться мне нужно больше сил. Продай мне свое зелье.");
        System.out.println("Хорошо, у меня есть 2 вида зелья: ");
        System.out.println("1. " + trader.getPosion1());
        System.out.println("2. " + trader.getPosion2());
        System.out.println("Выпив первое твое здоровье увеличится втрое, но стоит оно 3 слитка золота.");
        System.out.println("Выпив второе твое здоровье увеличится вдвое, но стоит оно 2 слитка золота.");
        System.out.println("Выбери цифру 1 или 2");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String st = br.readLine();
            switch (st) {
                case "1":
                    System.out.println(heroe.getName() + ", твое здоровье было HEALTH: " + heroe.getHealth());
                    heroe.setHealth(heroe.getHealth() * 3);
                    System.out.println("Выпив зелья здоровье увеличилось вдвое HEALTH: " + heroe.getHealth());
                    heroe.setGold(heroe.getGold() - 3);
                    trader.setPosion(trader.getPosion() - 1);
                    Battle.finalBattle(heroe, monster, trader);
                    break;
                case "2":
                    System.out.println(heroe.getName() + ", твое здоровье было HEALTH: " + heroe.getHealth());
                    heroe.setHealth(heroe.getHealth() * 2);
                    System.out.println("Выпив зелья здоровье увеличилось вдвое HEALTH: " + heroe.getHealth());
                    heroe.setGold(heroe.getGold() - 2);
                    trader.setPosion(trader.getPosion() - 1);
                    Battle.finalBattle(heroe, monster, trader);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
