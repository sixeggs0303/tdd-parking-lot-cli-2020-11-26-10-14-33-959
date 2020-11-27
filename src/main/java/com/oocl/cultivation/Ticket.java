package com.oocl.cultivation;

public class Ticket {
    private String license;

    public Ticket(Car car){
        this.license = car.getLicense();
    }

    public String getLicense() {
        return this.license;
    }


}
