package com.petProject.api.utilits.dataGenerator;

import com.github.javafaker.Faker;

import java.util.Random;

public class DataGenerator {

    private static Faker faker = new Faker();

    /**
     * Generate faker randomize number (int)
     *
     * @param length length string of digits which you want to get
     * @return a randomize int "1113167882"
     */
    public static int getFakerRandomNumberInt(int length) {
        String number = "";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            number += String.valueOf(rand.nextInt(8) + 1);
        }
        return Integer.parseInt(number);
    }

    /**
     * Generate faker randomize number with setting the minimum and maximum limits of values (int)
     *
     * @param min minimum limit of number which you want to get
     * @param max maximum limit of number which you want to get
     * @return a randomize string "1384"
     */
    public static int getFakerRandomIntNumberBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

}
