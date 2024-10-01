package cengiz.aop;


import cengiz.exception.NotFoundException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RuleKurumIdGecerliOlmaliAspect {

  @Before("@annotation(rule)")
  public void ruleBefore(JoinPoint joinPoint, RuleKurumIdGecerliOlmali rule) throws Exception {

    String payload = rule.kurumId();
    Object arg = joinPoint.getArgs()[0];
    if (arg instanceof Collection<?> objects) {
      for (Object obj : objects) {
        checkField(payload, obj);
      }
    } else {
      checkField(payload, arg);
    }
  }

  private void checkField(String payload, Object obj) throws Exception {
    try {
      Class<?> aClass = obj.getClass();
      Field declaredField = aClass.getDeclaredField(payload); // Alanı kontrol et
      declaredField.setAccessible(true);
      Integer mhrsKurumId = (Integer) declaredField.get(obj); // Değeri al

      List<Integer> kurumIdList = new ArrayList<>();
      kurumIdList.add(1); // Geçerli kurum ID'leri burada olabilir
      kurumIdList.add(2);

      if (!kurumIdList.contains(mhrsKurumId)) {
        throw new NotFoundException("Kurum Id kayitli degil");
      }

    } catch (NoSuchFieldException e) {
      // Alan bulunamadığında daha açıklayıcı bir mesaj verebiliriz
      throw new Exception("Dto sınıfında '" + payload + "' adlı alan bulunamadı.", e);
    } catch (IllegalAccessException e) {
      // Alan erişilemediğinde açıklayıcı bir mesaj verebiliriz
      throw new Exception("Dto sınıfında '" + payload + "' alanına erişilemiyor.", e);
    }
  }
}