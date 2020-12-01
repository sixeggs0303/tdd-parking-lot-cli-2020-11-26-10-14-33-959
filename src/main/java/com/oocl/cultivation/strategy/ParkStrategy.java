package com.oocl.cultivation.strategy;

import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.exception.NotEnoughPositionException;

import java.util.List;

public interface ParkStrategy {
    ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws NotEnoughPositionException;
}
