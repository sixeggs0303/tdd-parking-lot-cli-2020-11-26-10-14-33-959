package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkStrategy implements ParkStrategy {
    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().max(Comparator.comparing(ParkingLot::getAvailablePositionRate)).orElse(null);
    }
}
