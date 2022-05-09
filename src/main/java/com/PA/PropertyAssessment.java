package com.PA;

import java.util.Objects;

public class PropertyAssessment implements Comparable{
    private int acctNo;
    private Address address;
    private String garage;
    private Neighborhood neighborhood;
    private Location location;
    private int assessedValue;
    private String  assessClass1;
    private String assessClass2;
    private String assessClass3;
    private int assessClass1Percent;
    private int assessClass2Percent;
    private int assessClass3Percent;
    private ClassInfo classInfo;

    public PropertyAssessment(int acctNo, Address address, String garage,
                              Neighborhood neighborhood, int assessedValue, Location location,
                              int assessClass1Percent, int assessClass2Percent, int assessClass3Percent,
                              String assessClass1, String assessClass2, String assessClass3, ClassInfo classInfo)
    {
        this.acctNo = acctNo;
        this.address = address;
        this.garage = garage;
        this.neighborhood = neighborhood;
        this.location = location;
        this.assessedValue = assessedValue;
        this.assessClass1Percent = assessClass1Percent;
        this.assessClass2Percent = assessClass2Percent;
        this.assessClass3Percent = assessClass3Percent;
        this.assessClass1 = assessClass1;
        this.assessClass2 = assessClass2;
        this.assessClass3 = assessClass3;
        this.classInfo = classInfo;
    }

    public int getAcctNo() {
        return this.acctNo;
    }

    public Address getAddress() {
        return this.address;
    }

    public String getGarage() {
        return this.garage;
    }

    public Neighborhood getNeighborhood() {
        return this.neighborhood;
    }

    public Location getLocation() {
        return this.location;
    }

    public int getAssessedValue() {
        return this.assessedValue;
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
    public int compareTo(Object pa) {
        int compareProp = ((PropertyAssessment) pa).getAssessedValue();
        return this.assessedValue - compareProp;
    }

//    public boolean equals(Object pa){
//        if (!(pa instanceof PropertyAssessment)) return false;
//        PropertyAssessment other = (PropertyAssessment) pa;
//        return this.acctNo == other.acctNo && this.address == other.address &&
//                this.garage == other.garage && this.neighborhood == other.neighborhood &&
//                this.assessedValue == other.assessedValue &&
//                this.assessClass == other.assessClass;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAssessment that = (PropertyAssessment) o;
        return acctNo == that.acctNo && assessedValue == that.assessedValue && assessClass1Percent == that.assessClass1Percent && assessClass2Percent == that.assessClass2Percent && assessClass3Percent == that.assessClass3Percent && Objects.equals(address, that.address) && Objects.equals(garage, that.garage) && Objects.equals(neighborhood, that.neighborhood) && Objects.equals(location, that.location) && Objects.equals(assessClass1, that.assessClass1) && Objects.equals(assessClass2, that.assessClass2) && Objects.equals(assessClass3, that.assessClass3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acctNo, address, garage, neighborhood, location, assessedValue, assessClass1, assessClass2, assessClass3, assessClass1Percent, assessClass2Percent, assessClass3Percent);
    }

    @Override
    public String toString() {
        return "PropertyAssessment{" +
                "acctNo=" + acctNo +
                ", address=" + address +
                ", garage='" + garage + '\'' +
                ", neighborhood=" + neighborhood +
                ", location=" + location +
                ", assessedValue=" + assessedValue +
                ", assessClass1='" + assessClass1 + '\'' +
                ", assessClass2='" + assessClass2 + '\'' +
                ", assessClass3='" + assessClass3 + '\'' +
                ", assessClass1Percent=" + assessClass1Percent +
                ", assessClass2Percent=" + assessClass2Percent +
                ", assessClass3Percent=" + assessClass3Percent +
                '}';
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }
}
