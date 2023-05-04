package es.tresdigits.worklogminer.features.screen_permission;

import es.tresdigits.worklogminer.common.model.BasicRq;
import es.tresdigits.worklogminer.common.model.CommonRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import es.tresdigits.worklogminer.features.screen.ScreenEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/screenPermission")
public class ScreenPermissionController {

  private final ScreenPermissionService screenPermissionService;

  @Autowired
  public ScreenPermissionController(ScreenPermissionService screenPermissionService) {
    this.screenPermissionService = screenPermissionService;
  }

  @PreAuthorize("hasAuthority('SCREENPERMISSION_READ')")
  @GetMapping("listItems")
  public ResponseEntity<CommonRs<List<ScreenPermissionDto>>> listItems(BasicRq rq) {
    List<ScreenPermissionDto> list = screenPermissionService.list();

    return CommonUtil.processResponse(rq, StatusResponse.GET, list);
  }

  @PreAuthorize("hasAuthority('SCREENPERMISSION_READ')")
  @GetMapping("listItemsScreen/{idScreen}")
  public ResponseEntity<CommonRs<List<ScreenPermissionDto>>> listItemsScreen(BasicRq rq,
      @PathVariable(name = "idScreen") ScreenEntity screen) {
    List<ScreenPermissionDto> list = screenPermissionService.listByScreen(screen);

    return CommonUtil.processResponse(rq, StatusResponse.GET, list);
  }

  @PreAuthorize("hasAuthority('SCREENPERMISSION_READ')")
  @GetMapping("getItem/{id}")
  public ResponseEntity<CommonRs<ScreenPermissionDto>> getItem(BasicRq rq, @PathVariable Integer id) {
    ScreenPermissionDto screenPermission = screenPermissionService.get(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, screenPermission);
  }

  @PreAuthorize("hasAuthority('SCREENPERMISSION_COLABORATOR')")
  @PostMapping("createItem")
  public ResponseEntity<CommonRs<ScreenPermissionDto>> createItem(
      @RequestBody CommonRq<ScreenPermissionDto> rq) {
    ScreenPermissionDto createdScreenPermission = screenPermissionService.create(
        rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.CREATE, createdScreenPermission);
  }

  @PreAuthorize("hasAuthority('SCREENPERMISSION_COLABORATOR')")
  @PutMapping("updateItem/{id}")
  public ResponseEntity<CommonRs<ScreenPermissionDto>> updateItem(
      @RequestBody CommonRq<ScreenPermissionDto> rq, @PathVariable Integer id) {
    rq.getData().setCode(id);
    ScreenPermissionDto updatedScreenPermission = screenPermissionService.update(
        rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.UPDATE, updatedScreenPermission);
  }

  @PreAuthorize("hasAuthority('SCREENPERMISSION_ALLOWDELETE')")
  @DeleteMapping("deleteItem/{id}")
  public void deleteItem(@PathVariable Integer id) {
    screenPermissionService.delete(id);
  }

}
