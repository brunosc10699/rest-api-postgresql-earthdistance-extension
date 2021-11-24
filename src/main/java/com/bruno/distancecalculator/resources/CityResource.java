package com.bruno.distancecalculator.resources;

import com.bruno.distancecalculator.services.impl.CityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/cities")
@RequiredArgsConstructor
public class CityResource {

    private final CityServiceImpl cityService;

    @GetMapping(value = "/distance")
    public ResponseEntity<String> getDistance(@RequestParam(name = "from") Long from, @RequestParam(name = "to") Long to) {
        return ResponseEntity.ok(cityService.getDistance(from, to));
    }
}
