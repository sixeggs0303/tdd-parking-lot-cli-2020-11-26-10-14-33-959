package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.Ticket;

import java.util.List;

public interface FetchStrategy {
    ParkingLot getParkingLot(List<ParkingLot> parkingLots, Ticket ticket);
}
