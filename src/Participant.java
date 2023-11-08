public abstract class Participant {
    private String name;
    private int health;
    private int gold;
    private int skill;
    private int experience;
    private int power;
    public static final float HIT_CRITICAL = 20;

    public Participant(String name, int health, int gold, int skill, int experience, int power) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.skill = skill;
        this.experience = experience;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        if (getGold() <= 0) {
            setGold(0);
            System.out.println("Золото закончилось!");
        }
        this.gold = gold;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


    public void startGame() {
        System.out.println("\n" + "Ход персонажа: " + name);
    }

    public int attack() {
        if (skill * 3 > Math.random() * 50) {
            return power;
        } else if (Math.random() * 50 <= HIT_CRITICAL) {
            return power * 2;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return getName() + "\n" +
                "HEALTH: " + health +
                " POWER: " + power +
                " SKILL: " + skill +
                " EXPERIENCE: " + experience +
                " GOLD: " + gold;
    }
}
