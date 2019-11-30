package com.bham.pij.assignments.resultchecker;

public class ResultChecker {

    private static final int PASS_MARK = 40;

    public static void main(String[] args) {
        ResultChecker rc = new ResultChecker();
        int[] grades = {100,100,40,40,40,40,40,40};
        int pgrade = 100;
        System.out.println(rc.getResult(grades,pgrade));
    }

    public String getResult(int[] grades, int projectGrade) {
        if (!(checkError(grades,projectGrade))){
            return "ERROR";
        }
        if (!(checkFail(grades,projectGrade))){
            return "FAIL";
        }
        double mean = computeMean(grades);
        if (mean < 50 || projectGrade < 50){
            return "PASS";
        }
        if (mean >= 50 && projectGrade >= 50){
            return "MERIT";
        }
        return null;
    }

    private boolean checkError(int[] grades, int projectGrade){
        if (!(isValid(projectGrade))){
            return false;
        }
        for (int i : grades){
            if (!(isValid(i))){
                return false;
            }
        }
        return true;
    }
    private boolean checkFail(int[] grades, int projectGrade){
        if (!(isPass(projectGrade))){
            return false;
        }
        for (int i : grades){
            if (!(isPass(i))){
                return false;
            }
        }
        return true;
    }

    private double computeMean(int[] grades) {
        double mean = 0;
        for (int i : grades) {
            if (!(isValid(i))) {
                return -1;
            }
            mean += i;
        }
        mean = mean / grades.length;
        return mean;
    }

    private boolean isValid(int grade) {
        return (grade >= 0 && grade <= 100);
    }

    public boolean isPass(int grade) {
        return (grade >= PASS_MARK);
    }
}
