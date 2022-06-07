package com.company;

public class Main {

    public static void main(String[] args) {

        String coords = "STWSWTPPTPTTPWPP";
        String race = Race.HUMAN.name();

        int result = Solution.getResult(coords, race);
        System.out.println(result);
    }
}
