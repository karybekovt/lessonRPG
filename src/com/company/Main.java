package com.company;

import java.util.Random;

public class Main {
    // Карыбеков Темирлан
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenseType = "";
    //_______________________________________
    public static int[] heroesHealth = {300, 200, 250, 250};
    public static int[] heroesDamage = {20, 30, 25, 0};
    public static String[] heroesName = {"Warrior", "Magic", "Kenetick", "Medic"};
    public static String[] heroesAttackType = {"Physikal", "Magic", "Mental", "Medication"};

    public static void main(String[] args) {
        printStatistic();
        while (!isFinished()) {
            round();
        }
    }

    public static void round() {
        System.out.println("**************");
        System.out.println("HIT STATISTICKS: ");
        System.out.println("**************");
        changeBossDefense();
        heroesHit();
        bossHit();
        System.out.println("^^^^^^^^^^^^^");
        medicSkill();
        System.out.println("^^^^^^^^^^^^^");
        printStatistic();
    }

    public static void medicSkill() {
        Random r = new Random();
        int a = r.nextInt(3);
        int terapy = 40;
        if (heroesHealth[3] > 0 && heroesHealth[a] < 100) {
            if (heroesHealth[a] > 0) {
                heroesHealth[a] = heroesHealth[a] + terapy;
                System.out.println("Medic cured " + heroesName[a] + ": " + terapy);
            } else {
                heroesHealth[a] = 0;
            }
        }
    }


    public static void changeBossDefense() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefenseType = heroesAttackType[randomIndex];
    }


    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("~~~~~~~~~~~~~~");
            System.out.println("Heroes won!!!");
            System.out.println("~~~~~~~~~~~~~~");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("###########");
            System.out.println("Boss won!!!");
            System.out.println("###########");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random r = new Random();
            bossDamage = r.nextInt(60) + 30;
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if ((heroesHealth[i] - bossDamage) < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
                System.out.println("Boss hit " + heroesName[i] + ": " + bossDamage);
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (i == 3)
                continue;
            if (heroesHealth[i] > 0) {
                if (bossDefenseType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(10) + 4;
                    if ((bossHealth - heroesDamage[i] * coef) < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] + " criticaly hit: " + heroesDamage[i] * coef);
                } else {
                    if ((bossHealth - heroesDamage[i]) < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void printStatistic() {
        System.out.println("____________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kenetick health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("____________________");

    }
}
