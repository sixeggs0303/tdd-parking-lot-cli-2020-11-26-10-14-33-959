package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.ParkingBoyNotInManagementListException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private HashSet<ParkingBoy> managementList;

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList, HashSet<ParkingBoy> managementList) {
        super(parkingLotList);
        this.managementList = managementList;
    }

    public void addParkingBoysToManagementList(HashSet<ParkingBoy> managementList) {
        this.managementList.addAll(managementList);
    }

    public HashSet<ParkingBoy> getManagementList() {
        return managementList;
    }

    public Ticket assignParkingBoyToPark(ParkingBoy parkingBoy, Car car) throws NotEnoughPositionException, ParkingBoyNotInManagementListException {
        if (this.managementList.contains(parkingBoy)) {
            return parkingBoy.park(car);
        }
        throw new ParkingBoyNotInManagementListException();
    }
}
