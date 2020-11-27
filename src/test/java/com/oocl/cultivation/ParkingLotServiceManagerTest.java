package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void should_return_a_ticket_when_assign_parking_boy_to_park_given_a_service_manager_with_management_list_and_a_car() {
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

}
