package com.dgmoonlabs.testmethodologies.grade;

public class GradeJudgement {

    public String judge(float grade) {
        if (grade < 0 || grade > 10.0) {
            return "invalid";
        }

        if (grade >= 5.0) {
            return "pass";
        }

        return "fail";
    }

}
