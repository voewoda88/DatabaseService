package org.spring.bd.controllers.api.converter;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.spring.bd.services.converter.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
@Tag(name = "ConverterController", description = "Data converter from postgres to mongo")
public class ConverterPostgresToMongoApiController {
    private final ConverterService converterService;

    @Autowired
    public ConverterPostgresToMongoApiController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/convert")
    public ResponseEntity<Void> convert() {
        converterService.ConvertPostgresToMongo();

        return ResponseEntity.ok().build();
    }
}
