package com.bham.pij.assignments.meangrade;

import java.util.Scanner;

public class MeanGrade {

    private static final int MEAN_NUMBER = 4;

    public static void main(String[] args) {
        MeanGrade mg = new MeanGrade();
        int[] grades = mg.getInputs();
        if (grades == null) { return;}
        double mean = mg.computeMean(grades);
    }

    public double computeMean(int[] grades) {
        double mean = 0;
        for (int i : grades){
            if (!(isValid(i))){
                return -1;
            }
            mean += i;
        }
        mean = mean / grades.length;
        return mean;
    }

    private int[] getInputs() {
        int[] grades = new int[MEAN_NUMBER];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < grades.length; i++) {
            System.out.println("Input a grade to check.");
            String input = in.nextLine();
            int val = Integer.parseInt(input);
            if (!(isValid(val))){
                System.out.println("Invalid input.");
                return null;
            }
            grades[i] = val;
        }
        in.close();
        return grades;
    }

    private boolean isValid(int grade) {
        return (grade >= 0 && grade <= 100);
    }
}
