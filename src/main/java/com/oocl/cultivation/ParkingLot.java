package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final Integer capacity;
    private final List<Car> cars;

    public ParkingLot(Integer available) {
        this.capacity = available;
        this.cars = new ArrayList<>();
    }

    public Ticket park(Car car) {
        if (this.cars.size() >= capacity) {
            return null;
        }
        cars.add(car);
        return new Ticket();
    }
}
