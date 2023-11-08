import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trader extends WorldOfMonstersAndHeroes {
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
        if (posion <= 0) {
            posion = 0;
            System.out.println("Зелье закончилось!");
        }
        this.posion = posion;
    }
}
