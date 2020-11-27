package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking_lot() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        //when
        final Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_only_return_one_ticket_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_1_capacity() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        //when
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        //then
        assertNotNull(ticket1);
        assertNull(ticket2);
    }
}
