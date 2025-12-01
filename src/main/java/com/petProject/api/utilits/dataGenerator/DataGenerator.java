package com.petProject.api.utilits.dataGenerator;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

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

    /**
     * Generate randomize Lowercase letters
     *
     * @param len length +1 string of letters which you want to get
     * @return a randomize string "rygf"
     */
    public static String genRandomLetter(int len) {
        String s = "";
        for (int i = 1; i < len; i++)
            s += (char) (char) (Math.random() * ('z' - 'a' + 1) + 'a');
        return s;
    }

    /**
     * Generate faker first name
     *
     * @return a randomize string "Jamienpb"
     */
    public static String getFakerFirstName() {
        return faker.name().firstName() + genRandomLetter(4);
    }

    /**
     * Generate faker last name
     *
     * @return a randomize string "Jaskolski"
     */
    public static String getFakerLastName() {
        return faker.name().lastName() + genRandomLetter(6);
    }

    /**
     * Generate faker job
     *
     * @return a randomize string "Regional Farming Administratorfci"
     */
    public static String getFakerJob() {
        return faker.job().title() + genRandomLetter(4);
    }

    /**
     * Generate current time according Time zone
     * @return string in format yyyy-MM-dd hh:mm:ss, "2020-11-16 09:43:39"
     **/

    public static String getCurrentTimeByTimeZone(String timeFormat, String timeZone){
        Date date = new Date();
        DateFormat timeStamp = new SimpleDateFormat(timeFormat);

        // Use time zone to format the date in
        timeStamp.setTimeZone(TimeZone.getTimeZone(timeZone));

        System.out.println("Date and time: " + timeStamp.format(date));

        return timeStamp.format(date);
    }

}
