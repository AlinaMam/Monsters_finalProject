import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Battle extends Thread {
    private static final float NUM = 0.5f;

    public static Participant createMonster() {
        if (Math.random() >= NUM) {
            return new Goblin("Гоблин", 25, 5, 7, 8, 9);
        } else {
            return new Skeleton("Скелет", 36, 6, 9, 3, 5);
        }
    }

    public static Participant createHeroe() {
        return new Heroe("Герой", 17, 5, 3, 2, 5);
    }

    public static Trader createTrader() {
        return new Trader("Торговец зельем");
    }

    @Override
    public void run() {
        processButtle(createHeroe(), createMonster());
    }

    public static void finalBattle(Participant heroe, Participant monster, Trader trader) {
        bigMenu(heroe);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String num = br.readLine();

            while (true) {
                switch (num) {
                    case "1":
                        trader.sellPosion(heroe, monster, trader);
                        break;
                    case "2":
                        Battle.processButtle(heroe, monster);
                        break;
                    case "3":
                        System.out.println("Я выхожу из игры!");
                        System.exit(0);
                }
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

    public static void processButtle(Participant forward, Participant defender) {
        while (forward.getHealth() >= 0 && defender.getHealth() >= 0) {
            battle(forward, defender);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
