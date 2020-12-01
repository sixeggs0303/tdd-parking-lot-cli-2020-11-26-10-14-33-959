package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private final Map<Ticket, Car> ticketCarHashMap;

    public ParkingLot(Integer capacity) {
        this.capacity = capacity;
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

    public Integer getEmptyPosition() {
        return this.capacity - this.ticketCarHashMap.size();
    }

    public Double getAvailablePositionRate() {
        if (this.capacity == 0) return 0.0;
        return (this.getEmptyPosition().doubleValue() / this.capacity);
    }

    public Boolean hasCar(Ticket ticket) {
        return this.ticketCarHashMap.get(ticket) != null;
    }
}
