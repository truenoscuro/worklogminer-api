package es.tresdigits.worklogminer;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.tresdigits.worklogminer.common.model.Filter;
import es.tresdigits.worklogminer.common.util.DateUtil;
import es.tresdigits.worklogminer.common.util.JsonUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void testconvertDateToLocalDate() {
    Assertions.assertDoesNotThrow(() -> {
      Date date = new Date();
      LocalDate dateLocal = DateUtil.convertDateToLocalDate(date);
      log.info(String.valueOf(dateLocal));
    });
  }


  @Test
  void testconvertLocalDateToDate(){
    Assertions.assertDoesNotThrow( () -> {
      LocalDate date = LocalDate.now();
      Date dateF = DateUtil.convertLocalDateToDate( date );
      log.info( String.valueOf( dateF ) );
    });
  }

  @Test
  void testconvertStringToLocalDate(){
    Assertions.assertDoesNotThrow( () -> {
      String date = "23-11-2010";
      LocalDate dateLocal = DateUtil.convertStringToLocalDate( date,"dd-MM-yyyy" );
      log.info( String.valueOf( dateLocal ) );
    });
  }
  @Test
  void testconvertStringToDate(){
    Assertions.assertDoesNotThrow( () -> {
      String date = "23-11-2010";
      Date dateLocal = DateUtil.convertStringToDate( date,"dd-MM-yyyy" );
      log.info( String.valueOf( dateLocal ) );
    });
  }
  @Test
  void testConvertObjectoToJson() throws JsonProcessingException
  {
    Filter filter = new Filter();
    filter.setField( "Prueba" );
    filter.setOperator( "De" );
    filter.setValue( "Conversion" );
    List<String> list = new ArrayList<String>();
    list.add( "a ver" );
    list.add( "Si va" );
    filter.setValues( list );
  }

  @Test
  void testConvertJsonToGenericObject(){
    JsonUtil.jsonToObject(
        " {\"field\":\"Prueba\",\"operator\":\"De\",\"value\":\"Conversion\",\"values\":[\"a ver\",\"Si va\"]}",
        Filter.class);
  }

  @Test
  void testConvertStringToLocalDateTime(){
    String dateStr = "2020-08-17T10:11:16.908732";
    String format = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
    LocalDateTime localdateTime = DateUtil.converStringToLocalDateTime( dateStr,format );
    log.info( String.valueOf( localdateTime ) );
  }

  @Test
  void testDateToLocalDateTime(){
    Assertions.assertDoesNotThrow( () -> {
      Date date = new Date();
      LocalDateTime dateLocal = DateUtil.convertDateToLocalDateTime( date );
      log.info( String.valueOf( dateLocal ) );
    });
  }

  @Test
  void testLocalDateTimeToDate(){
    LocalDateTime date = LocalDateTime.now();
    log.info(String.valueOf(DateUtil.convertLocalDateTimeToDate( date )));
  }
}


