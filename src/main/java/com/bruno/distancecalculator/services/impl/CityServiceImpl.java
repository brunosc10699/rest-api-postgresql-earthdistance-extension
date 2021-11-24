package com.bruno.distancecalculator.services.impl;

import com.bruno.distancecalculator.repositories.CityRepository;
import com.bruno.distancecalculator.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private static final double MILE = 1.60934;

    @Override
    public String getDistance(final Long from, final Long to) {
        Double distanceInKm = cityRepository.getDistance(from, to) * MILE;
        return String.format("The distance between the given cities is %.2f Km", distanceInKm);
    }
}
