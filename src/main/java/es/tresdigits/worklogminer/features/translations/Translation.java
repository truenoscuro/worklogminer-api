package es.tresdigits.worklogminer.features.translations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class Translation {

  private Integer code;
  private String labelName;
  private String labelGroupName;
  private List<TranslationValue> values;

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "labCode");
    result.put("labelName", "labId");
    result.put("labelGroupName", "labCodlgr");
    return result;
  }
}
