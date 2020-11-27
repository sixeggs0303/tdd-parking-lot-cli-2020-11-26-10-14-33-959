package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private final Map<Ticket, Car> ticketCarHashMap;

    public ParkingLot(Integer available) {
        this.capacity = available;
        this.ticketCarHashMap = new HashMap<>();
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        if (this.ticketCarHashMap.size() >= capacity) {
            throw new NotEnoughPositionException();
        }
        Ticket ticket = new Ticket();
        this.ticketCarHashMap.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        Car car = this.ticketCarHashMap.get(ticket);
        if (car == null) {
            throw new UnrecognizedParkingTicketException();
        }
        this.ticketCarHashMap.remove(ticket);
        return car;
    }
}
