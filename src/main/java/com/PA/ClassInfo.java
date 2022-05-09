package com.PA;


import java.util.Objects;

public class ClassInfo {
    private String assessClass1;
    private String assessClass2;
    private String assessClass3;
    private int assessClass1Percent;
    private int assessClass2Percent;
    private int assessClass3Percent;

    public ClassInfo(String assessClass1, String assessClass2, String assessClass3,
                     int assessClass1Percent, int assessClass2Percent, int assessClass3Percent) {
        this.assessClass1 = assessClass1;
        this.assessClass2 = assessClass2;
        this.assessClass3 = assessClass3;
        this.assessClass1Percent = assessClass1Percent;
        this.assessClass2Percent = assessClass2Percent;
        this.assessClass3Percent = assessClass3Percent;
    }

    public String getAssessClass1() {
        return assessClass1;
    }

    public String getAssessClass2() {
        return assessClass2;
    }

    public String getAssessClass3() {
        return assessClass3;
    }

    public int getAssessClass1Percent() {
        return assessClass1Percent;
    }

    public int getAssessClass2Percent() {
        return assessClass2Percent;
    }

    public int getAssessClass3Percent() {
        return assessClass3Percent;
    }

    @Override
    public String toString() {
        return "[" + assessClass1 + " " + assessClass1Percent + "%] " + "["  +assessClass2 + " " + assessClass2Percent +
                "%] " + "[" + assessClass3 + " " + assessClass3Percent + "%]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return assessClass1Percent == classInfo.assessClass1Percent && assessClass2Percent == classInfo.assessClass2Percent && assessClass3Percent == classInfo.assessClass3Percent && Objects.equals(assessClass1, classInfo.assessClass1) && Objects.equals(assessClass2, classInfo.assessClass2) && Objects.equals(assessClass3, classInfo.assessClass3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assessClass1, assessClass2, assessClass3, assessClass1Percent, assessClass2Percent, assessClass3Percent);
    }
}