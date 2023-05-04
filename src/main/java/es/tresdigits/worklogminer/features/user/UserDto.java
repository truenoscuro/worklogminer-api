package es.tresdigits.worklogminer.features.user;

import es.tresdigits.worklogminer.common.model.CommonDTO;
import es.tresdigits.worklogminer.features.language.LanguageDto;
import es.tresdigits.worklogminer.features.role.RoleDto;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserDto extends CommonDTO implements Serializable {

  private LanguageDto languageDto;
  private Integer codLan;
  private RoleDto roleDto;
  private Integer codRol;
  private String name;
  private String surname;
  private String email;
  private Boolean active;

  public LanguageDto getLanguageDto() {
    return languageDto;
  }

  public void setLanguageDto(LanguageDto languageDto) {
    this.languageDto = languageDto;
  }

  public Integer getCodLan() {
    return codLan;
  }

  public void setCodLan(Integer codLan) {
    this.codLan = codLan;
  }

  public RoleDto getRoleDto() {
    return roleDto;
  }

  public void setRoleDto(RoleDto roleDto) {
    this.roleDto = roleDto;
  }

  public Integer getCodRol() {
    return codRol;
  }

  public void setCodRol(Integer codRol) {
    this.codRol = codRol;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public static Map<String, String> getRelatedFields() {
    Map<String, String> result = new HashMap<>();
    result.put("code", "useCode");
    result.put("creuse", "useCreuse");
    result.put("credat", "useCredat");
    result.put("moduse", "useModuse");
    result.put("moddat", "useModdat");
    result.put("name", "useName");
    result.put("surname", "useSurname");
    result.put("email", "useEmail");
    result.put("codLan", "useCodlan");
    result.put("codRol", "useCodrol");
    result.put("active", "useActive");
    return result;
  }
}