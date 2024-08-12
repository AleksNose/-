package com.aleksnose.hoteru.service;

import com.aleksnose.hoteru.exception.NotFoundException;
import com.aleksnose.hoteru.models.Component;
import com.aleksnose.hoteru.models.Room;
import com.aleksnose.hoteru.models.TargetRoom;
import com.aleksnose.hoteru.repository.TargetRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class TargetRoomService {

    private TargetRoomRepository targetRoomRepository;

    public TargetRoomService(TargetRoomRepository targetRoomRepository) {
        this.targetRoomRepository = targetRoomRepository;
    }

    public TargetRoom getTargetRoomById(Integer id) {
        return targetRoomRepository.findById(id).orElseThrow(() -> new NotFoundException("TargetRoom not found with id: " + id));
    }

    public Set<Room> getRoomsByIdTargetRoom(Integer id) {
        var targetRoom = getTargetRoomById(id);
        return targetRoom.getRooms();
    }

    public Set<Component> getComponentByIdTargetRoom(Integer id) {
        var targetRoom = getTargetRoomById(id);
        return targetRoom.getComponents();
    }
}
