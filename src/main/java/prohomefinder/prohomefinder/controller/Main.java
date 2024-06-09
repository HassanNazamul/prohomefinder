
package prohomefinder.prohomefinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import prohomefinder.prohomefinder.model.House;
import prohomefinder.prohomefinder.repository.HomeRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class Main {

    @Autowired
    HomeRepository homeRepo;

    @GetMapping("/houseListing")
    public List<House> houseListing() {
        // Fetch all houses from the repository
        return (List<House>) homeRepo.findAll();
    }

    @PostMapping("/addHouse")
    public ResponseEntity<String> addHouse(@RequestBody House house) {
        try {
            homeRepo.save(house);
            return ResponseEntity.status(HttpStatus.CREATED).body("House added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    // New method for deleting a house by ID
    @Transactional
    @DeleteMapping("/deleteHouse/{id}")
    public ResponseEntity<String> deleteHouse(@PathVariable int id) {
        try {

            homeRepo.deleteByHouseid(id);

            return ResponseEntity.status(HttpStatus.OK).body("House deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
}
