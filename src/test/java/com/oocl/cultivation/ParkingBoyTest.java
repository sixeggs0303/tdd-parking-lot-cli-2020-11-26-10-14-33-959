package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() throws NotEnoughPositionException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car();

        //when
        parkingBoy.park(car);

        //then
        verify(parkingLot, times(1)).park(car);
    }

    @Test
    void should_parking_boy_call_parking_lot_fetch_function_once_when_fetch_a_car() throws UnrecognizedParkingTicketException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = new Ticket();

        //when
        parkingBoy.fetch(ticket);

        //then
        verify(parkingLot, times(1)).fetch(ticket);
    }

    @Test
    void should_park_a_car_to_second_parking_lot_when_park_a_car_given_a_car_and_two_parking_lots_with_one_full() throws Exception {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());

        //when
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_a_car_when_fetch_given_a_car_parked_in_one_of_the_multiple_lots() throws Exception {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());
        Ticket ticket = parkingBoy.park(new Car());

        //when
        parkingBoy.fetch(ticket);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_throw_not_enough_position_exception_when_park_multiple_cars_given_1_car_and_two_parking_lot_with_0_capacity() throws NotEnoughPositionException {
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
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(1));
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
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(1));
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
