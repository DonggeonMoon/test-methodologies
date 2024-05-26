package com.dgmoonlabs.testmethodologies.grade;

import net.jqwik.api.*;
import net.jqwik.api.constraints.FloatRange;
import net.jqwik.api.lifecycle.BeforeProperty;

import static org.assertj.core.api.Assertions.assertThat;

class GradeJudgementTest {

    private GradeJudgement gradeJudgement;

    @BeforeProperty
    void setUp() {
        gradeJudgement = new GradeJudgement();
    }

    @Property
    void fail(
            @ForAll
            @FloatRange(min = 0.0f, max = 5.0f, maxIncluded = false)
            float grade
    ) {
        assertThat(gradeJudgement.judge(grade))
                .isEqualTo("fail");
    }

    @Property
    void pass(
            @ForAll
            @FloatRange(min = 5.0f, max = 10.0f)
            float grade
    ) {
        assertThat(gradeJudgement.judge(grade))
                .isEqualTo("pass");
    }

    @Property
    void invalid(
            @ForAll("invalidValues")
            float grade
    ) {
        System.out.println("grade = " + grade);
        assertThat(gradeJudgement.judge(grade))
                .isEqualTo("invalid");
    }

    @Provide
    private Arbitrary<Float> invalidValues() {
        return Arbitraries.oneOf(
                Arbitraries.floats().lessThan(0.0f),
                Arbitraries.floats().greaterThan(10.0f)
        );
    }
}