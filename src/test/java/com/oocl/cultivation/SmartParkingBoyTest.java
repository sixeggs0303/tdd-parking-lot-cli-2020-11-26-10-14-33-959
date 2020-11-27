package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    public void should_be_parked_to_parking_lot_2_when_park_a_car_given_parking_lot_2_has_more_empty_position() throws Exception {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        Ticket ticket = smartParkingBoy.park(car);

        //then
        assertEquals(car, parkingLot2.fetch(ticket));
    }


    @Test
    public void should_throw_not_enough_position_exception_when_park_multiple_cars_given_two_parking_lots_are_full() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(0));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        final NotEnoughPositionException notEnoughPositionException =
                assertThrows(NotEnoughPositionException.class, () -> parkingBoy.park(new Car()));

        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    public void should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_used_ticket_and_multiple_parking_lots_that_the_car_is_fetched() throws Exception {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(5));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(new Car());
        parkingBoy.fetch(ticket);

        //when
        final UnrecognizedParkingTicketException unrecognizedParkingTicketException =
                assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));

        //then
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    public void should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_fake_ticket_and_multiple_parking_lots_with_car_parked() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(5));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());
        Ticket fakeTicket = new Ticket();
        //when
        final UnrecognizedParkingTicketException unrecognizedParkingTicketException =
                assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(fakeTicket));

        //then
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicketException.getMessage());
    }
}
