package com.bruno.distancecalculator.services.impl;

import com.bruno.distancecalculator.repositories.CityRepository;
import com.bruno.distancecalculator.services.CityService;
import com.bruno.distancecalculator.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private static final double MILE = 1.60934;

    @Override
    public String getDistance(Long from, Long to) {
        findById(from);
        findById(to);
        Double distanceInKm = cityRepository.getDistance(from, to) * MILE;
        return String.format("The distance between the given cities is %.2f Km", distanceInKm);
    }

    @Transactional(readOnly = true)
    private void findById(Long id) {
        cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found by id '" + id + "'"));
    }
}
