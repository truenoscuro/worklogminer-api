package es.tresdigits.worklogminer.features.screen;

import es.tresdigits.worklogminer.common.model.BasicRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/screen")
public class ScreenController {

  private final IScreenService screenService;

  @Autowired
  public ScreenController(ScreenService screenService) {
    this.screenService = screenService;
  }

  @PreAuthorize("hasAuthority('SCREEN_READ')")
  @GetMapping("listItems")
  public ResponseEntity<CommonRs<MenuContainer>> listItems(BasicRq rq) {
    MenuContainer menuContainer = screenService.list();

    return CommonUtil.processResponse(rq, StatusResponse.GET, menuContainer);
  }

}
