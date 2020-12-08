package com.example.pandemic;

public class Calculations {
    public static String[] nextPart(String[] data, Boolean isRight) {

        //Assigning of variables
        Integer day = Integer.parseInt(data[0]);
        Integer amount = Integer.parseInt(data[1]);
        String part = data[2];
        Double percentage_to_get = Double.parseDouble(data[3]);
        Double percentage_to_amount = Double.parseDouble(data[4]);
        Boolean isIll = Boolean.parseBoolean(data[5]);
        Integer recover = Integer.parseInt(data[6]);

        //Checking if hero has done right stuff
        if (isRight) {
            percentage_to_amount *= 0.95;
            percentage_to_get *= 0.91;
        } else {
            percentage_to_amount *= 1.7;
            percentage_to_get *= 1.45;
        }

        //Adding part of day
        if (part.equals("3")) {
            part = "0";
            day++;
        } else {
            part = (Integer.parseInt(part) + 1) + "";
        }

        //Increasing amount of people who have ill
        if (part.equals("1") || part.equals("3")) {
            amount += (int) Math.round((day * (percentage_to_get / 100)) / 2);
            if (Math.random() < 0.15) {
                amount = amount > 0 ? amount - 1 : 0;
            }
        }

        //Check if hero is ill
        if (isIll) {
            isIll = day - recover < 3;
        } else {
            Double random_to_get = Math.random() * 100;
            if (random_to_get < percentage_to_get) {
                isIll = true;
                recover = day;
            } else {
                isIll = false;
                recover = 0;
            }
        }

        //Assigning new array and fill it
        String[] res = new String[7];
        res[0] = day + "";
        res[1] = amount + "";
        res[2] = part;
        res[3] = percentage_to_get + "";
        res[4] = percentage_to_amount + "";
        res[5] = isIll + "";
        res[6] = recover + "";
        return res;
    }

    public static String[] skipPart(String[] data) {

        //Assigning of variables
        Integer day = Integer.parseInt(data[0]);
        Integer amount = Integer.parseInt(data[1]);
        String part = data[2];
        Double percentage_to_get = Double.parseDouble(data[3]);
        Double percentage_to_amount = Double.parseDouble(data[4]);
        Boolean isIll = Boolean.parseBoolean(data[5]);
        Integer recover = Integer.parseInt(data[6]);

        //Check if hero has been recovered
        isIll = day - recover < 3;

        if (day.equals(recover)) {
            amount++;
        }

        if (day - recover == 2) {
            amount--;
        }

        //Increasing amount of people who have ill
        if (part.equals("1") || part.equals("3")) {
            amount += (int) Math.round((day * (percentage_to_get / 100)) / 2);
            if (Math.random() < 0.15) {
                amount = amount > 0 ? amount - 1 : 0;
            }
        }

        //Adding part of day
        if (part.equals("3")) {
            part = "0";
            day++;
        } else {
            part = (Integer.parseInt(part) + 1) + "";
        }

        //Assigning new array and fill it
        String[] res = new String[7];
        res[0] = day + "";
        res[1] = amount + "";
        res[2] = part;
        res[3] = percentage_to_get + "";
        res[4] = percentage_to_amount + "";
        res[5] = isIll + "";
        res[6] = recover + "";
        return res;
    }
}
