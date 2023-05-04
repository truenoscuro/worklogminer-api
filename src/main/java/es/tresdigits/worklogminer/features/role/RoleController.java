package es.tresdigits.worklogminer.features.role;

import es.tresdigits.worklogminer.common.model.BasicRq;
import es.tresdigits.worklogminer.common.model.CommonRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/role")
public class RoleController {

  private final IRoleService roleService;

  public RoleController(IRoleService roleService) {
    this.roleService = roleService;
  }

  @PreAuthorize("hasAuthority('ROL_READ')")
  @PostMapping ("/listItems")
  public ResponseEntity<CommonRs<List<RoleDto>>> list(@RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<RoleDto> response = roleService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('ROL_READ')")
  @GetMapping("/listSelectOptions")
  public ResponseEntity<CommonRs<List<SelectOption>>> listTableEditorSelectOptions(
      BasicRq rq) {
    List<SelectOption> roleDtoList = roleService.listSelectOptions();

    return CommonUtil.processResponse(rq, StatusResponse.GET, roleDtoList);
  }

  @PreAuthorize("hasAuthority('ROL_READ')")
  @PostMapping("/listSelectOptionsFiltered")
  public ResponseEntity<CommonRs<List<SelectOption>>> listTableEditorSelectOptionsFiltered(
      @RequestBody CommonRq<Map<String, Object>> rq) {
    List<SelectOption> roleDtoList = roleService.listSelectOptionsFiltered(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.GET, roleDtoList);
  }

  @PreAuthorize("hasAuthority('ROL_READ')")
  @GetMapping("/getItem/{id}")
  public ResponseEntity<CommonRs<RoleDto>> findById(BasicRq rq,
      @PathVariable("id") Integer id) {
    RoleDto roleDto = roleService.findById(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, roleDto);
  }

  @PreAuthorize("hasAuthority('ROL_COLABORATOR')")
  @PostMapping(value = "/createItem")
  public ResponseEntity<CommonRs<RoleDto>> create(@RequestBody CommonRq<RoleDto> rq) {
    RoleDto roleDto = roleService.create(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, roleDto);
  }

  @PreAuthorize("hasAuthority('ROL_ALLOWDELETE')")
  @DeleteMapping("/deleteItem/{id}")
  public void delete(@PathVariable("id") Integer id) {
    roleService.delete(id);
  }

  //Reseteo de creacion y seteo de modificacion con dummys en usuario
  @PreAuthorize("hasAuthority('ROL_COLABORATOR')")
  @PutMapping("/updateItem/{id}")
  public ResponseEntity<CommonRs<RoleDto>> update(@RequestBody CommonRq<RoleDto> rq,
      @PathVariable Integer id) {
    rq.getData().setCode(id);
    RoleDto roleDto = roleService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.PUT, roleDto);
  }
}
