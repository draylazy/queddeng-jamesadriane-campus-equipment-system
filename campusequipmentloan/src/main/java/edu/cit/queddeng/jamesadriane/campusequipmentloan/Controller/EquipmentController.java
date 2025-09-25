package edu.cit.queddeng.jamesadriane.campusequipmentloan.Controller;

import edu.cit.queddeng.jamesadriane.campusequipmentloan.Entity.Equipment;
import edu.cit.queddeng.jamesadriane.campusequipmentloan.Repository.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByAvailableTrue();
    }

    // Add new equipment
    @PostMapping
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        equipment.setAvailable(true); // default to available
        return equipmentRepository.save(equipment);
    }
}
