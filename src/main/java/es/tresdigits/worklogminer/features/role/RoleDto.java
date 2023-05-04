package es.tresdigits.worklogminer.features.role;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class RoleDto extends CommonDTO implements Serializable {

  private String name;
  private Boolean active;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "rolCode");
    result.put("creuse", "rolCreuse");
    result.put("credat", "rolCredat");
    result.put("moduse", "rolModuse");
    result.put("moddat", "rolModdat");
    result.put("name", "rolName");
    result.put("active", "rolActive");
    return result;
  }

}
