import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WorldOfMonstersAndHeroes {
    public static void main(String[] args) {
        Battle.finalBattle(Battle.createHeroe(), Battle.createMonster(), Battle.createTrader());
    }
}