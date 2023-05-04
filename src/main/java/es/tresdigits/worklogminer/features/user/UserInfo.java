package es.tresdigits.worklogminer.features.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

  private String displayname;
  private String email;
}
