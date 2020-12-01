package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;

import java.util.List;

public class StandardParkStrategy implements ParkStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().filter(parkingLot -> parkingLot.getEmptyPosition() > 0).findFirst().orElse(null);
    }
}
