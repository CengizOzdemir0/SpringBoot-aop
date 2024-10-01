package cengiz.service;

import cengiz.aop.RuleKurumIdGecerliOlmali;
import cengiz.dto.PersonelDto;
import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class PersonelService {


  @RuleKurumIdGecerliOlmali(kurumId = "kurumId")
  public PersonelDto save(@Valid PersonelDto personelDto) {
    return personelDto;
  }

}
