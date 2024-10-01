package cengiz.aop;


import cengiz.dto.PersonelDto;
import cengiz.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RulePersonelNullOlmayacakAlanlarAspect {


  @Before("@annotation(RuleKurumIdGecerliOlmali)")
  public void ruleBefore(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    List<PersonelDto> personelDtoList = new ArrayList<>();


    if (joinPoint.getArgs()[0] instanceof Collection<?>) {
      personelDtoList.addAll((List<PersonelDto>) args[0]);
    } else {
      personelDtoList.add((PersonelDto) args[0]);
    }

    if (!personelDtoList.isEmpty() && personelDtoList.get(0).getKullaniciAdi() == null) {
      throw new NotFoundException("Ad bos olamaz");
    }
  }

}
