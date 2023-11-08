import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Battle extends Thread{
    private static final float NUM = 0.5f;

    public Participant createMonster() {
        if (Math.random() >= NUM) {
            return new Goblin("Гоблин", 25, 5, 7, 8, 9);
        } else {
            return new Skeleton("Скелет", 36, 6, 9, 3, 5);
        }
    }

    public Participant createHeroe() {
        return new Heroe("Герой", 17, 5, 3, 2, 5);
    }

    @Override
    public void run() {
        finalBattle(createHeroe(), createMonster());
    }

    public static void finalBattle(Participant heroe, Participant monster) {
        Trader trader = new Trader("Торговец зельем");

        bigMenu(heroe);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String num = br.readLine();

            switch (num) {
                case "1":
                    sellPosion(heroe, monster, trader);
                    break;
                case "2":
                    Battle.processButtle(heroe, monster);
                    break;
                case "3":
                    System.out.println("Я выхожу из игры!");
                    System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bigMenu(Participant participant) {
        System.out.println(participant.getName() + ", куда ты хочешь пойти?");
        System.out.println("1. К торговцу");
        System.out.println("2. В тёмный лес");
        System.out.println("3. На выход");
        System.out.println("Укажи цифру 1 2 или 3\n");
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
                    finalBattle(heroe, monster);
                case "2":
                    System.out.println(heroe.getName() + ", твое здоровье было HEALTH: " + heroe.getHealth());
                    heroe.setHealth(heroe.getHealth() * 2);
                    System.out.println("Выпив зелья здоровье увеличилось вдвое HEALTH: " + heroe.getHealth());
                    heroe.setGold(heroe.getGold() - 2);
                    trader.setPosion(trader.getPosion() - 1);
                    finalBattle(heroe, monster);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processButtle(Participant forward, Participant defender) {
        while (forward.getHealth() >= 0 && defender.getHealth() >= 0) {
            battle(forward, defender);
            battle(defender, forward);
        }
    }

    public static void battle(Participant forward, Participant defender) {
        int power = forward.attack();
        if (power != 0) {
            defender.setHealth(defender.getHealth() - power);

            if (defender.getHealth() < 0) {
                defender.setHealth(0);
            }

            System.out.println(forward.getName() + " наносит удар по " + defender.getName() + " в размере " + power);
            System.out.println("Здоровье " + defender.getName() + " уменьшается на величину удара " + power);
            System.out.println("HEALTH " + defender.getName() + ": " + defender.getHealth());

            if ((defender.getHealth() == 0) && (forward instanceof Heroe)) {
                forward.setGold(forward.getGold() + 1);
                forward.setExperience(forward.getExperience() + 1);
                System.out.println(forward.getName() + " победил " + defender.getName() + " и получил GOLD: " + forward.getGold() + " и EXPERIENCE: " + forward.getGold());
            }
            if ((defender.getHealth() == 0) && (forward instanceof Goblin || forward instanceof Skeleton)) {
                System.out.println(forward.getName() + " одержал победу");
                System.exit(0);
            }
        }
    }
}
