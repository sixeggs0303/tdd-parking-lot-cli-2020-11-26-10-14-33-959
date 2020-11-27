package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private final Map<Ticket, Car> ticketCarHashMap;

    public ParkingLot(Integer available) {
        this.capacity = available;
        this.ticketCarHashMap = new HashMap<>();
    }

    public Ticket park(Car car) {
        if (this.ticketCarHashMap.size() >= capacity) {
            return null;
        }
        Ticket ticket = new Ticket(car);
        this.ticketCarHashMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketCarHashMap.get(ticket);
    }
}
