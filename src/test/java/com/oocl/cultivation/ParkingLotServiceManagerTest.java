package com.oocl.cultivation;

import com.oocl.cultivation.exception.NotEnoughPositionException;
import com.oocl.cultivation.exception.ParkingBoyNotInManagementListException;
import com.oocl.cultivation.exception.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotServiceManagerTest {
    @Test
    void should_add_parking_boy_to_management_list_when_add_parking_boy_given_service_manager_and_3_types_of_parking_boy() {
        //given

        HashSet<ParkingBoy> managementList = new HashSet<>();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>());
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>());

        managementList.add(parkingBoy);
        managementList.add(smartParkingBoy);
        managementList.add(superSmartParkingBoy);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), new HashSet<>());

        // when
        parkingLotServiceManager.addParkingBoysToManagementList(managementList);

        // then
        assertTrue(parkingLotServiceManager.getManagementList().contains(parkingBoy));
        assertTrue(parkingLotServiceManager.getManagementList().contains(smartParkingBoy));
        assertTrue(parkingLotServiceManager.getManagementList().contains(superSmartParkingBoy));
    }

    @Test
    public void should_return_a_ticket_when_assign_parking_boy_to_park_given_a_service_manager_with_management_list_and_a_car() throws NotEnoughPositionException, ParkingBoyNotInManagementListException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLots);
        HashSet<ParkingBoy> parkingBoys = new HashSet<>();
        parkingBoys.add(parkingBoy1);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);

        //when

        Ticket ticket = parkingLotServiceManager.assignParkingBoyToPark(parkingBoy1, new Car());

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_throw_parking_boy_not_in_management_list_exception_when_assign_parking_boy_to_park_given_a_service_manager_with_management_list_without_parking_boy_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLots);
        HashSet<ParkingBoy> parkingBoys = new HashSet<>();

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);

        //when
        final ParkingBoyNotInManagementListException parkingBoyNotInManagementListException =
                assertThrows(ParkingBoyNotInManagementListException.class, () -> parkingLotServiceManager.assignParkingBoyToPark(parkingBoy1, new Car()));

        //then
        assertEquals("Parking Boy Not In Management List", parkingBoyNotInManagementListException.getMessage());
    }

    @Test
    public void should_return_the_car_when_assign_parking_boy_to_fetch_given_a_service_manager_with_management_list_and_a_ticket() throws NotEnoughPositionException, UnrecognizedParkingTicketException, ParkingBoyNotInManagementListException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLots);
        HashSet<ParkingBoy> parkingBoys = new HashSet<>();
        parkingBoys.add(parkingBoy1);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);
        Car car = new Car();
        Ticket ticket = parkingLotServiceManager.assignParkingBoyToPark(parkingBoy1, car);

        //when
        Car actual = parkingLotServiceManager.assignParkingBoyToFetch(parkingBoy1, ticket);

        //then
        assertEquals(car, actual);
    }

    @Test
    public void should_throw_parking_boy_not_in_management_list_exception_when_assign_parking_boy_to_fetch_given_a_service_manager_with_management_list_without_parking_boy_and_a_ticket() throws NotEnoughPositionException, ParkingBoyNotInManagementListException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);

        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLots);
        HashSet<ParkingBoy> parkingBoys = new HashSet<>();
        parkingBoys.add(parkingBoy1);

        ParkingBoy parkingBoy2 = new ParkingBoy(new ArrayList<>());

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);
        Car car = new Car();
        Ticket ticket = parkingLotServiceManager.assignParkingBoyToPark(parkingBoy1, car);

        //when
        final ParkingBoyNotInManagementListException parkingBoyNotInManagementListException =
                assertThrows(ParkingBoyNotInManagementListException.class, () -> parkingLotServiceManager.assignParkingBoyToFetch(parkingBoy2, ticket));

        //then
        assertEquals("Parking Boy Not In Management List", parkingBoyNotInManagementListException.getMessage());
    }

    @Test
    public void should_throw_not_enough_position_exception_when_assign_parking_boy_to_park_given_a_service_manager_and_two_parking_lots_are_full_and_a_car() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(0));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        HashSet<ParkingBoy> parkingBoys = new HashSet<>();
        parkingBoys.add(parkingBoy);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);

        //when
        final NotEnoughPositionException notEnoughPositionException =
                assertThrows(NotEnoughPositionException.class, () -> parkingLotServiceManager.assignParkingBoyToPark(parkingBoy, new Car()));

        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    public void should_throw_unrecognized_parking_ticket_exception_when_assign_parking_boy_to_fetch_given_a_service_manager_and_a_used_ticket_and_multiple_parking_lots_that_the_car_is_fetched() throws Exception {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(5));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        HashSet<ParkingBoy> parkingBoys = new HashSet<>();
        parkingBoys.add(parkingBoy);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);

        Ticket ticket = parkingLotServiceManager.assignParkingBoyToPark(parkingBoy, new Car());
        parkingBoy.fetch(ticket);

        //when
        final UnrecognizedParkingTicketException unrecognizedParkingTicketException =
                assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLotServiceManager.assignParkingBoyToFetch(parkingBoy, ticket));

        //then
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    public void should_throw_unrecognized_parking_ticket_exception_when_assign_parking_boy_to_fetch_given_a_service_manager_and_a_fake_ticket_and_multiple_parking_lots_with_car_parked() throws NotEnoughPositionException, ParkingBoyNotInManagementListException {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(5));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        HashSet<ParkingBoy> parkingBoys = new HashSet<>();
        parkingBoys.add(parkingBoy);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoys);

        parkingLotServiceManager.assignParkingBoyToPark(parkingBoy, new Car());

        Ticket fakeTicket = new Ticket();

        //when
        final UnrecognizedParkingTicketException unrecognizedParkingTicketException =
                assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLotServiceManager.assignParkingBoyToFetch(parkingBoy, fakeTicket));

        //then
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    public void should_return_a_parking_ticket_when_park_given_a_car_and_manager() throws NotEnoughPositionException {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLots, new HashSet<>());

        //when
        final Ticket ticket = parkingLotServiceManager.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_manager_a_valid_ticket_and_parking_lot_that_parked_the_car() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLots, new HashSet<>());
        Ticket ticket = parkingLotServiceManager.park(car);

        //when
        Car actual = parkingLot.fetch(ticket);

        //then
        assertEquals(car, actual);
    }
}
