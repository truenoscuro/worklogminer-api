package es.tresdigits.worklogminer.features.configuration;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationDto extends CommonDTO implements Serializable {

  private String label;
  private String key;
  private String value;
  private String validation;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValidation() {
    return validation;
  }

  public void setValidation(String validation) {
    this.validation = validation;
  }

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "conCode");
    result.put("creuse", "conCreuse");
    result.put("credat", "conCredat");
    result.put("moduse", "conModuse");
    result.put("moddat", "conModdat");
    result.put("label", "conLabel");
    result.put("key", "conKey");
    result.put("value", "conValue");
    result.put("validation", "conValidation");
    return result;
  }

}
