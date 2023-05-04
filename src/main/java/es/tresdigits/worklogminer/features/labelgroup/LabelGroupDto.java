package es.tresdigits.worklogminer.features.labelgroup;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LabelGroupDto extends CommonDTO implements Serializable {

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "lgrCode");
    result.put("creuse", "lgrCreuse");
    result.put("credat", "lgrCredat");
    result.put("moduse", "lgrModuse");
    result.put("moddat", "lgrModdat");
    result.put("id", "lgrId");
    return result;
  }

}
