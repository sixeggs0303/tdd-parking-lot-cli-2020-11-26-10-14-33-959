package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotServiceManagerTest {
    @Test
    void should_add_parking_boy_to_management_list_when_add_parking_boy_given_service_manager_and_3_types_of_parking_boy() {
        //given

        List<ParkingBoy> managementList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>());
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>());

        managementList.add(parkingBoy);
        managementList.add(smartParkingBoy);
        managementList.add(superSmartParkingBoy);

        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(new ArrayList<>(), new ArrayList<>());

        // when
        parkingLotServiceManager.addParkingBoysToManagementList(managementList);

        // then
        assertTrue(parkingLotServiceManager.getManagementList().contains(parkingBoy));
        assertTrue(parkingLotServiceManager.getManagementList().contains(smartParkingBoy));
        assertTrue(parkingLotServiceManager.getManagementList().contains(superSmartParkingBoy));
    }
}
