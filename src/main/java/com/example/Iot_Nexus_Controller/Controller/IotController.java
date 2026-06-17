package com.example.Iot_Nexus_Controller.Controller;

import com.example.Iot_Nexus_Controller.entity.Device;
import com.example.Iot_Nexus_Controller.entity.Room;
import com.example.Iot_Nexus_Controller.service.IoTService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@RestController
@CrossOrigin(origins="*")
//  base URL for all endpoints in this controller ("/api")
@RequestMapping("/api")
public class IoTController {

    // Dependency: Define the IoTService as a private final field to be injected via the constructor
    private final IoTService iotService;

    //  Injecting the IoTService using Constructor Injection
    public IoTController(IoTService iotService)
    {
        this.iotService=iotService;
    }

    // ENDPOINTS:

    // 1. Creating a Room
    // Mapping this method to HTTP POST requests at "/rooms"
    @PostMapping("/rooms")
    //  @RequestBody to convert the JSON coming from the user into a Java 'Room' object
    public Room createRoom(@RequestBody Room room) {
        // TODO: Call the service method to save the room and return the saved object
        return iotService.createRoom(room);
    }

    // 2. Get all Rooms
    // Mapping this method to HTTP GET requests at "/rooms"
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        // TODO: Call the service method to get all rooms and return the list
        return iotService.getAllRooms();
    }

    // 3. Adding a Device to a specific Room
    //  We need two pieces of data here:
    //    a. The 'roomId' from the URL path (e.g., /rooms/1/devices)
    //    b. The 'device' data from the Request Body
    @PostMapping("/rooms/{roomId}/devices")
    public Device addDeviceToRoom(@PathVariable Long roomId, @RequestBody Device device) {

        // TODO: Call the service method that links the device to the room
        return iotService.addDevice(roomId,device);
    }

    // 4. Delete a Room
    //  Mapping this to HTTP DELETE requests
    // Note: The "{id}" in the URL must match the variable name in the method arguments
    @DeleteMapping("/rooms/{id}")
    public String deleteRoom(@PathVariable Long id) {
        // TODO: Call the service to delete the room
        iotService.deleteRoom(id);

        // Return a success message
        return "Room deleted successfully.";
    }

    // 5. Deleting a Device
    //  Mapping this to HTTP DELETE requests for a specific device ID
    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable Long id) {
        // TODO: Call the service to delete the device
        iotService.deleteDevice(id);

        // Return a success message
        return "Device deleted successfully.";
    }
}



