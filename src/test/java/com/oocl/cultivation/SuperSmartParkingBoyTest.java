package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_park_in_parking_lot_2_when_park_given_a_car_and_parking_lot_2_has_highest_available_position_rate_while_empty_position_less_than_parking_lot_1() throws NotEnoughPositionException, UnrecognizedParkingTicketException {
        //given
        ParkingLot parkingLot1 = new ParkingLot(5);
        parkingLot1.park(new Car());
        parkingLot1.park(new Car());
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot2.park(new Car());

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        Car car = new Car();

        //when
        Ticket ticket = superSmartParkingBoy.park(car);

        //then
        assertEquals(car, parkingLot2.fetch(ticket));

    }

    @Test
    public void should_throw_not_enough_position_exception_when_park_multiple_cars_given_two_parking_lots_are_full() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(0));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        final NotEnoughPositionException notEnoughPositionException =
                assertThrows(NotEnoughPositionException.class, () -> superSmartParkingBoy.park(new Car()));

        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    public void should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_used_ticket_and_multiple_parking_lots_that_the_car_is_fetched() throws Exception {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(5));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.fetch(ticket);

        //when
        final UnrecognizedParkingTicketException unrecognizedParkingTicketException =
                assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetch(ticket));

        //then
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    public void should_throw_unrecognized_parking_ticket_exception_when_fetch_given_a_fake_ticket_and_multiple_parking_lots_with_car_parked() throws NotEnoughPositionException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(5));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        Ticket fakeTicket = new Ticket();
        //when
        final UnrecognizedParkingTicketException unrecognizedParkingTicketException =
                assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetch(fakeTicket));

        //then
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicketException.getMessage());
    }

}
