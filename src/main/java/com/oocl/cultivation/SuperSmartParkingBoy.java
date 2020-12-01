package com.oocl.cultivation;

import com.oocl.cultivation.strategy.SuperSmartParkStrategy;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        this.setParkStrategy(new SuperSmartParkStrategy());
    }

}
