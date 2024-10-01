package cengiz.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class PersonelDto {

  private String kullaniciAdi;
  private String kullaniciSoyadi;
  private Integer kurumId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime baslangicTarihi;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime bitisTarihi;
}
