package com.example.test.controller;

import com.example.test.model.Location;
import com.example.test.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/location/all")
    public List<Location> getLocationList(){
        return locationService.getLocation();
    }

}
