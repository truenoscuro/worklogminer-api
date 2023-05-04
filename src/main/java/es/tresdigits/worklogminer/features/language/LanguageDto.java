package es.tresdigits.worklogminer.features.language;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LanguageDto extends CommonDTO implements Serializable {

  private String name;
  private String description;
  private Integer order;
  private Boolean active;

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

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "lanCode");
    result.put("creuse", "lanCreuse");
    result.put("credat", "lanCredat");
    result.put("moduse", "lanModuse");
    result.put("moddat", "lanModdat");
    result.put("name", "lanName");
    result.put("description", "lanDescription");
    result.put("order", "lanOrder");
    result.put("active", "lanActive");
    return result;
  }
}
