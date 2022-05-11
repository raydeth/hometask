package com.github.raydeth.workload;

import java.util.concurrent.ThreadLocalRandom;

public class WordGenerator {
    private static final String[] NAMES = {"Johny", "Mark", "William", "Robert", "Natasha", "Mary", "Donald", "Karolina"};
    private static final String[] SURNAMES = {"Smith", "Jackson", "Trump", "Jane", "Parker", "Hemsworth"};
    private static final String[] PRIMARY_SKILLS = {"Communication", "Teamwork", "Problem solving", "Public-Speaking"};

    public static String generateName() {
        return NAMES[ThreadLocalRandom.current().nextInt(0, NAMES.length)];
    }

    public static String generateSurname() {
        return SURNAMES[ThreadLocalRandom.current().nextInt(0, SURNAMES.length)];
    }

    public static String generatePrimarySkill() {
        return PRIMARY_SKILLS[ThreadLocalRandom.current().nextInt(0, PRIMARY_SKILLS.length)];
    }
}
