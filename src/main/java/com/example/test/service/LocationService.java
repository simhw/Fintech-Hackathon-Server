package com.example.test.service;

import com.example.test.model.Location;
import com.example.test.model.User;
import com.example.test.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getLocation() { //마커 위치 전체 리스트 가져옴
        return locationRepository.findAll();
    }
}
