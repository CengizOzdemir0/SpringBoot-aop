package cengiz.controller;

import cengiz.service.PersonelService;
import cengiz.dto.PersonelDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/personel")
@RestController
public class PersonelController {

  private final PersonelService personelService;

  @PostMapping
  public ResponseEntity<PersonelDto> save(@Valid @RequestBody PersonelDto personelDto){
    return ResponseEntity.ok(personelService.save(personelDto));
  }

}
