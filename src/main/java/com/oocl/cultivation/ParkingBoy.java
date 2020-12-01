package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import com.oocl.cultivation.strategy.FetchStrategy;
import com.oocl.cultivation.strategy.ParkStrategy;
import com.oocl.cultivation.strategy.StandardFetchStrategy;
import com.oocl.cultivation.strategy.StandardParkStrategy;

import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;
    private ParkStrategy parkStrategy;
    private final FetchStrategy fetchStrategy;

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLots = parkingLotList;
        this.parkStrategy = new StandardParkStrategy();
        this.fetchStrategy = new StandardFetchStrategy();
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        ParkingLot availableParkingLot = this.getParkStrategy().getParkingLot(this.getParkingLots());
        if (availableParkingLot != null) {
            return availableParkingLot.park(car);
        }
        throw new NotEnoughPositionException();
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        ParkingLot targetParkingLot = this.getFetchStrategy().getParkingLot(this.getParkingLots(), ticket);
        if (targetParkingLot != null) {
            return targetParkingLot.fetch(ticket);
        }
        throw new UnrecognizedParkingTicketException();
    }

    public Boolean hasParkingLotNotFull() {
        return this.getParkingLots().stream().anyMatch(parkingLot -> parkingLot.getEmptyPosition() > 0);
    }

    public ParkStrategy getParkStrategy() {
        return parkStrategy;
    }

    public void setParkStrategy(ParkStrategy parkStrategy) {
        this.parkStrategy = parkStrategy;
    }

    public FetchStrategy getFetchStrategy() {
        return fetchStrategy;
    }

}
