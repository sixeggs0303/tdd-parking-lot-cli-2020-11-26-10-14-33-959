package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
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

    @Test
    public void should_return_multiple_ticket_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_enough_capacity() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);

        //when
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNotEquals(ticket1, ticket2);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_valid_ticket_and_parking_lot_that_parked_the_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
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
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        //when
        Car actual = parkingLot.fetch(ticket);

        //then
        assertNull(actual);
    }

    @Test
    public void should_return_null_when_fetch_given_a_fake_ticket_and_parking_lot_with_car_parked() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);
        Ticket fakeTicket = new Ticket();

        //when
        Car actual = parkingLot.fetch(fakeTicket);

        //then
        assertNull(actual);
    }

    @Test
    public void should_throw_not_enough_position_exception_when_park_given_a_car_and_not_enough_capacity() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        //when
        final NotEnoughPositionException notEnoughPositionException =
                assertThrows(NotEnoughPositionException.class, () -> parkingLot.park(new Car()));

        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

}
