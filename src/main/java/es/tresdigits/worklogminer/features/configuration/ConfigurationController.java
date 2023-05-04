package es.tresdigits.worklogminer.features.configuration;

import es.tresdigits.worklogminer.common.model.BasicRq;
import es.tresdigits.worklogminer.common.model.CommonRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/configuration")
public class ConfigurationController {

  private final IConfigurationService configurationService;

  @Autowired
  public ConfigurationController(IConfigurationService configurationService) {
    this.configurationService = configurationService;
  }

  @PreAuthorize("hasAuthority('CONFIGURATION_READ')")
  @PostMapping("listItems")
  public ResponseEntity<CommonRs<List<ConfigurationDto>>> list(
      @RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<ConfigurationDto> response = configurationService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('CONFIGURATION_READ')")
  @GetMapping("getItem/{id}")
  public ResponseEntity<CommonRs<ConfigurationDto>> get(BasicRq rq, @PathVariable Integer id) {
    ConfigurationDto configuration = configurationService.get(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, configuration);
  }

  @PreAuthorize("hasAuthority('CONFIGURATION_COLABORATOR')")
  @PutMapping("updateItem/{id}")
  public ResponseEntity<CommonRs<ConfigurationDto>> update(
      @RequestBody CommonRq<ConfigurationDto> rq, @PathVariable Integer id) {
    rq.getData().setModdat(LocalDateTime.now());
    rq.getData().setCode(id);
    ConfigurationDto configuration = configurationService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.UPDATE, configuration);
  }
}
