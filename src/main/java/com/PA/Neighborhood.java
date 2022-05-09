package com.PA;

import java.util.Objects;

public class Neighborhood {
    private int id;
    private String name;
    private String ward;

    public Neighborhood(int id, String name, String ward){
        this.id = id;
        this.name = name;
        this.ward = ward;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getWard() {
        return this.ward;
    }

    @Override
    public String toString() {
        return
                name + "(" + ward +  ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighborhood that = (Neighborhood) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(ward, that.ward);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ward);
    }
}
