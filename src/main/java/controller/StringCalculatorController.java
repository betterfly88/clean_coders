package controller;

import input.InputCalculator;

/**
 * Created by betterFLY on 2018. 2. 18.
 * Github : http://github.com/betterfly88
 */

public class StringCalculatorController {

    public static void main(String[] args) {
        double result = InputCalculator.executeCalculator();

        System.out.println("결과 : "+result);
    }
}