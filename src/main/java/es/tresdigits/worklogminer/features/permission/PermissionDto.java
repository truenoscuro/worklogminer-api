package es.tresdigits.worklogminer.features.permission;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PermissionDto extends CommonDTO implements Serializable {

  private String usecode;
  private String name;
  private Boolean active;

  public String getUsecode() {
    return usecode;
  }

  public void setUsecode(String usecode) {
    this.usecode = usecode;
  }

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
    result.put("code", "perCode");
    result.put("creuse", "perCreuse");
    result.put("credat", "perCredat");
    result.put("moduse", "perModuse");
    result.put("moddat", "perModdat");
    result.put("usecode", "perUsecode");
    result.put("name", "perName");
    result.put("active", "perActive");
    return result;
  }

}
