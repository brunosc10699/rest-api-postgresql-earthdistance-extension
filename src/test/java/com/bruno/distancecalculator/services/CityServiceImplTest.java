package com.bruno.distancecalculator.services;

import com.bruno.distancecalculator.repositories.CityRepository;
import com.bruno.distancecalculator.services.impl.CityServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    private static final double MILE = 1.60934;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @Test
    @DisplayName("(1) When providing ids of two cities, return the distance between them")
    void whenProvidingIdsOfTwoCitiesReturnTheDistanceBetweenThem() {
        final Long from = 1L;
        final Long to = 2L;
        Double distance = 10.0;
        Double distanceInKm = distance * MILE;
        when(cityRepository.getDistance(from, to)).thenReturn(distance);
        String result = cityService.getDistance(from, to);
        assertThat(result, is(equalTo(String.format("The distance between the given cities is %.2f Km", distanceInKm))));
    }
}
