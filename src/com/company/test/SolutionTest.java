package com.company.test;

import com.company.Race;
import com.company.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    void getResultForHuman() {
        String coords = "STWSWTPPTPTTPWPP";
        String race = Race.HUMAN.name();

        int result = Solution.getResult(coords, race);

        assertEquals(result, 10);
    }

    @Test
    void getResultForSwamper() {
        String coords = "STWSWTPPTPTTPWPP";
        String race = Race.SWAMPER.name();

        int result = Solution.getResult(coords, race);

        assertEquals(result, 15);
    }

    @Test
    void getResultForWoodman() {
        String coords = "STWSWTPPTPTTPWPP";
        String race = Race.WOODMAN.name();

        int result = Solution.getResult(coords, race);

        assertEquals(result, 12);
    }

    @Test
    void getResultForEmptyCoords() {
        String coords = "";
        String race = Race.HUMAN.name();

        int result = Solution.getResult(coords, race);

        assertEquals(result, -1);
    }
}