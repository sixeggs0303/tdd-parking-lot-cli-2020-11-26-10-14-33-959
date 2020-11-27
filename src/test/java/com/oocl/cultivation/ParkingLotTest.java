package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking_lot() {
        //given
        Car car = new Car("A");
        ParkingLot parkingLot = new ParkingLot(1);
        //when
        final Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_only_return_one_ticket_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_1_capacity() {
        //given
        Car car1 = new Car("A");
        Car car2 = new Car("B");
        ParkingLot parkingLot = new ParkingLot(1);

        //when
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        //then
        assertNotNull(ticket1);
        assertNull(ticket2);
    }

    @Test
    public void should_return_multiple_ticket_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_enough_capacity() {
        //given
        Car car1 = new Car("A");
        Car car2 = new Car("B");
        ParkingLot parkingLot = new ParkingLot(2);

        //when
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        //then
        assertEquals(car1.getLicense(), ticket1.getLicense());
        assertEquals(car2.getLicense(), ticket2.getLicense());
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_valid_ticket_and_parking_lot_that_parked_the_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A");
        Ticket ticket = parkingLot.park(car);

        //when
        Car actual = parkingLot.fetch(ticket);

        //then
        assertEquals(car, actual);
    }

    @Test
    public void should_return_null_when_fetch_given_a_used_ticket_and_parking_lot_that_the_car_is_fetched() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A");
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        //when
        Car actual = parkingLot.fetch(ticket);

        //then
        assertNull(actual);
    }
}
