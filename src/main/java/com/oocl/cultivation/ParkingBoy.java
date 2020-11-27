package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;

import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLots = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (NotEnoughPositionException notEnoughPositionException) {
                // Do nothing
            }
        }
        throw new NotEnoughPositionException();
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (UnrecognizedParkingTicketException unrecognizedParkingTicketException) {
                // Do nothing
            }
        }
        throw new UnrecognizedParkingTicketException();
    }

}
