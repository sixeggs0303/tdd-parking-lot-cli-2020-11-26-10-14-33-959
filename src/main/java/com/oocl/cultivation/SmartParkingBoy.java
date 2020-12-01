package com.oocl.cultivation;

import com.oocl.cultivation.strategy.SmartParkStrategy;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        this.setParkStrategy(new SmartParkStrategy());
    }

}
