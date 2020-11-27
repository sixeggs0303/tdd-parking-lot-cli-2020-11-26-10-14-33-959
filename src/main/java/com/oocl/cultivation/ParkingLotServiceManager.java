package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private List<ParkingBoy> managementList = new ArrayList<>();

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList, List<ParkingBoy> managementList) {
        super(parkingLotList);
        this.managementList = managementList;
    }

    public void addParkingBoysToManagementList(List<ParkingBoy> managementList) {
    }

    public List<ParkingBoy> getManagementList() {
        return managementList;
    }
}
