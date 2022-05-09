package com.PA;

import java.util.Objects;

public class Address {
    private String suite;
    private String houseNum;
    private String streetName;

    public Address(String suite, String houseNum, String streetName){
        this.houseNum = houseNum;
        this.streetName = streetName;
        this.suite = suite;
    }

    public String getSuite() {
        return this.suite;
    }

    public String getHouseNum() {
        return this.houseNum;
    }

    public String getStreetName() {
        return this.streetName;
    }

    @Override
    public String toString() {
        if (suite != "0"){
            return
                    suite +
                    " " + houseNum + " " + streetName;
        }
        else return houseNum + " " + streetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(suite, address.suite) && Objects.equals(houseNum, address.houseNum) && Objects.equals(streetName, address.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suite, houseNum, streetName);
    }
}

