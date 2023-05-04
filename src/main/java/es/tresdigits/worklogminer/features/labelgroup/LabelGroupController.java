package es.tresdigits.worklogminer.features.labelgroup;

import es.tresdigits.worklogminer.common.model.BasicRq;
import es.tresdigits.worklogminer.common.model.CommonRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.SelectOption;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/labelGroup")
public class LabelGroupController {

  private final LabelGroupService labelGroupService;

  @Autowired
  public LabelGroupController(LabelGroupService labelGroupService) {
    this.labelGroupService = labelGroupService;
  }

  @PreAuthorize("hasAuthority('LABELGROUP_READ')")
  @PostMapping("listItems")
  public ResponseEntity<CommonRs<List<LabelGroupDto>>> listItems(CommonRq<TableContextDto> rq) {
    PaginatedResponse<LabelGroupDto> response = labelGroupService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('LABELGROUP_READ')")
  @PostMapping("listSelectOptionsFiltered")
  public ResponseEntity<CommonRs<List<SelectOption>>> listSelectOptionsFiltered(
      @RequestBody CommonRq<Map<String, Object>> rq) {
    List<SelectOption> response = labelGroupService.listSelectOptionsFiltered(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response);
  }

  @PreAuthorize("hasAuthority('LABELGROUP_READ')")
  @GetMapping("getItem/{id}")
  public ResponseEntity<CommonRs<LabelGroupDto>> getItem(BasicRq rq, @PathVariable Integer id) {
    LabelGroupDto labelGroup = labelGroupService.get(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, labelGroup);
  }

  @PreAuthorize("hasAuthority('LABELGROUP_COLABORATOR')")
  @PostMapping("createItem")
  public ResponseEntity<CommonRs<LabelGroupDto>> createItem(
      @RequestBody CommonRq<LabelGroupDto> rq) {
    rq.getData().setCredat(LocalDateTime.now());
    LabelGroupDto createdLabelGroup = labelGroupService.create(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.CREATE, createdLabelGroup);
  }

  @PreAuthorize("hasAuthority('LABELGROUP_COLABORATOR')")
  @PutMapping("updateItem/{id}")
  public ResponseEntity<CommonRs<LabelGroupDto>> updateItem(
      @RequestBody CommonRq<LabelGroupDto> rq, @PathVariable Integer id) {
    rq.getData().setModdat(LocalDateTime.now());
    rq.getData().setCode(id);
    LabelGroupDto updatedLabelGroup = labelGroupService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.UPDATE, updatedLabelGroup);
  }

  @PreAuthorize("hasAuthority('LABELGROUP_ALLOWDELETE')")
  @DeleteMapping("deleteItem/{id}")
  public void deleteItem(@PathVariable Integer id) {
    labelGroupService.delete(id);
  }
}
