package com.bruno.distancecalculator.services;

import com.bruno.distancecalculator.entities.CityEntity;
import com.bruno.distancecalculator.repositories.CityRepository;
import com.bruno.distancecalculator.services.exceptions.ResourceNotFoundException;
import com.bruno.distancecalculator.services.impl.CityServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        CityEntity city = CityEntity.builder().build();
        final Long from = 1L;
        final Long to = 2L;
        Double distance = 10.0;
        Double distanceInKm = distance * MILE;
        when(cityRepository.findById(from)).thenReturn(Optional.of(city));
        when(cityRepository.findById(to)).thenReturn(Optional.of(city));
        when(cityRepository.getDistance(from, to)).thenReturn(distance);
        String result = cityService.getDistance(from, to);
        assertThat(result, is(equalTo(String.format("The distance between the given cities is %.2f Km", distanceInKm))));
    }

    @Test
    @DisplayName("(2) When providing a bad city id, then throw ResourceNotFoundException exception")
    void whenProvidingABadCityIdThenThrowAnException() {
        final Long from = 1L;
        final Long to = 0L;
        assertThrows(ResourceNotFoundException.class, () -> cityService.getDistance(from, to));
    }
}
