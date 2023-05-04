package es.tresdigits.worklogminer.features.permission;

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
@RequestMapping("/permission")
public class PermissionController {

  private final IPermissionService permissionService;

  @Autowired
  public PermissionController(IPermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @PreAuthorize("hasAuthority('PERMISSION_READ')")
  @PostMapping("listItems")
  public ResponseEntity<CommonRs<List<PermissionDto>>> listItems(
      @RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<PermissionDto> response = permissionService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('PERMISSION_READ')")
  @PostMapping("/getRolePermissions/{id}")
  public ResponseEntity<CommonRs<List<PermissionDto>>> list(
      @RequestBody CommonRq<TableContextDto> rq, @PathVariable("id") Integer id) {
    PaginatedResponse<PermissionDto> response = permissionService.listByRole(id, rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.GET, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('PERMISSION_COLABORATOR')")
  @PostMapping("/manageRolePermission/{id}")
  public ResponseEntity<CommonRs<PermissionDto>> create(@RequestBody CommonRq<PermissionDto> rq,
      @PathVariable("id") Integer rolCode) {
    permissionService.manageRolePermissions(rq.getData(), rolCode);

    return CommonUtil.processResponse(rq, StatusResponse.POST, rq.getData());
  }

  @PreAuthorize("hasAuthority('PERMISSION_READ')")
  @GetMapping("listItemsActive")
  public ResponseEntity<CommonRs<List<PermissionDto>>> listItemsActive(BasicRq rq) {
    List<PermissionDto> list = permissionService.listActive();

    return CommonUtil.processResponse(rq, StatusResponse.GET, list);
  }

  @PreAuthorize("hasAuthority('PERMISSION_READ')")
  @GetMapping("getItem/{id}")
  public ResponseEntity<CommonRs<PermissionDto>> getItem(BasicRq rq, @PathVariable Integer id) {
    PermissionDto permissionDto = permissionService.get(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, permissionDto);
  }

  @PreAuthorize("hasAuthority('PERMISSION_COLABORATOR')")
  @PostMapping("createItem")
  public ResponseEntity<CommonRs<PermissionDto>> createItem(
      @RequestBody CommonRq<PermissionDto> rq) {
    rq.getData().setCredat(LocalDateTime.now());
    PermissionDto newPermission = permissionService.create(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, newPermission);
  }

  @PreAuthorize("hasAuthority('PERMISSION_ALLOWDELETE')")
  @DeleteMapping("deleteItem/{id}")
  public void deleteItem(@PathVariable Integer id) {
    permissionService.delete(id);
  }

  @PreAuthorize("hasAuthority('PERMISSION_COLABORATOR')")
  @PutMapping("updateItem/{id}")
  public ResponseEntity<CommonRs<PermissionDto>> updateItem(
      @RequestBody CommonRq<PermissionDto> rq, @PathVariable Integer id) {
    rq.getData().setModdat(LocalDateTime.now());
    rq.getData().setCode(id);
    PermissionDto updatedPermission = permissionService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.PUT, updatedPermission);
  }
}
