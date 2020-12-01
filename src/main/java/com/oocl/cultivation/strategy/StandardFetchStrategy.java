package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;

import java.util.List;

public class StandardFetchStrategy implements FetchStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots, Ticket ticket) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.hasCar(ticket)).findFirst().orElse(null);
    }
}
