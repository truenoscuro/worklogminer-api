package es.tresdigits.worklogminer.features.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import es.tresdigits.worklogminer.common.deserializers.EpochToLocalDateTimeDeserializer;
import es.tresdigits.worklogminer.common.model.CommonDTO;
import es.tresdigits.worklogminer.common.serializers.LocalDateTimeToEpochSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ExampleDto extends CommonDTO implements Serializable {

  private String name;
  private String description;
  @JsonSerialize(using = LocalDateTimeToEpochSerializer.class)
  @JsonDeserialize(using = EpochToLocalDateTimeDeserializer.class)
  private LocalDateTime date;
  private Integer size;
  private Integer color;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getColor() {
    return color;
  }

  public void setColor(Integer color) {
    this.color = color;
  }

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "exaCode");
    result.put("name", "exaName");
    result.put("description", "exaDescription");
    result.put("date", "exaDate");
    result.put("size", "exaSize");
    result.put("color", "exaColor");
    result.put("creuse", "exaCreuse");
    result.put("credat", "exaCredat");
    result.put("moduse", "exaModuse");
    result.put("moddat", "exaModdat");
    return result;
  }

}
