package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.StandardParkStrategy;

import java.util.HashSet;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private final HashSet<ParkingBoy> managementList;

    public ParkingLotServiceManager(List<ParkingLot> parkingLotList, HashSet<ParkingBoy> managementList) {
        super(parkingLotList);
        this.setParkStrategy(new StandardParkStrategy());
        this.managementList = managementList;
    }

    public void addParkingBoysToManagementList(HashSet<ParkingBoy> managementList) {
        this.managementList.addAll(managementList);
    }

    public HashSet<ParkingBoy> getManagementList() {
        return managementList;
    }

    public Ticket assignParkingBoyToPark(Car car) throws NotEnoughPositionException {
        ParkingBoy assignedParkingBoy = this.managementList.stream().filter(ParkingBoy::hasParkingLotNotFull).findAny().orElse(null);
        if (assignedParkingBoy != null) {
            return assignedParkingBoy.park(car);
        }
        throw new NotEnoughPositionException();
    }

    public Car assignParkingBoyToFetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        ParkingBoy assignedParkingBoy = this.managementList.stream()
                .filter(parkingBoy -> parkingBoy.getFetchStrategy().getParkingLot(parkingBoy.getParkingLots(), ticket) != null)
                .findAny()
                .orElse(null);
        if (assignedParkingBoy != null) {
            return assignedParkingBoy.fetch(ticket);
        }
        throw new UnrecognizedParkingTicketException();
    }
}
