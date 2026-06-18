package com.example.Iot_Nexus_Controller.Service;

import com.example.Iot_Nexus_Controller.entity.Device;
import com.example.Iot_Nexus_Controller.entity.Room;
import com.example.Iot_Nexus_Controller.repository.DeviceRepository;
import com.example.Iot_Nexus_Controller.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Mark this class as a Service component so Spring can manage it
@Service
public class IoTService {

    // Define the Repositories as private final fields to be injected via the constructor
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    // TODO: Inject the RoomRepository and DeviceRepository using Constructor Injection
    public IoTService(DeviceRepository deviceRepository,RoomRepository roomRepository)
    {
        this.deviceRepository=deviceRepository;
        this.roomRepository=roomRepository;
    }


    // 1. Create a Room
    // Challenge: Use the repository to save the room object.
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // 2. Get All Rooms
    // Challenge: Use the repository to find all records.
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // 3. Add a Device to a Specific Room
    // Challenge: We need to do 1 thing BEFORE saving the Device:
    // If the Room exists, set the Room in the Device object
    public Device addDevice(Long roomId, Device device) {
        // TODO: Try to find the Room in the database by its ID (using the RoomRepository)
        var roomOptional=roomRepository.findById(roomId);

        // TODO: Check if the Room actually exists
        if (roomOptional.isPresent()) {

            // TODO: Get the actual Room object out of the result
            Room foundRoom = roomOptional.get();

            // TODO: KEY STEP - LINK the Device to the Room (This sets the Foreign Key!)
            device.setRoom(foundRoom);


            // TODO: Save the Device to the database and return it
            return deviceRepository.save(device);
        }
        else{
            // If the Room wasn't found, stop and throw an error
            throw new RuntimeException("Room not found with id: " + roomId);
        }
    }

    // 4. Delete a Room
    // Challenge: Delete the room by its ID.
    // (Note: Because we used Cascading in the Room entity,
    // this will AUTOMATICALLY delete all devices in this room too!)
    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    // 5. Delete a Device
    public void deleteDevice(Long deviceId) {
        // TODO: Check if the device exists before deleting it.
        if(deviceRepository.existsById(deviceId)) {

            // TODO: If it exists, delete the device by its ID
            deviceRepository.deleteById(deviceId);
        } else {
            throw new RuntimeException("Device not found with id: " + deviceId);
        }
    }
}



