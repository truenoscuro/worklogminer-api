package es.tresdigits.worklogminer.features.label;


import es.tresdigits.worklogminer.common.model.CommonRq;
import es.tresdigits.worklogminer.common.model.CommonRs;
import es.tresdigits.worklogminer.common.model.PaginatedResponse;
import es.tresdigits.worklogminer.common.model.StatusResponse;
import es.tresdigits.worklogminer.common.model.TableContextDto;
import es.tresdigits.worklogminer.common.util.CommonUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/label")
public class LabelController {

  private final ILabelService labelService;

  @Autowired
  public LabelController(ILabelService labelService) {
    this.labelService = labelService;
  }

  @PreAuthorize("hasAuthority('LABEL_READ')")
  @PostMapping("/listItems")
  public ResponseEntity<CommonRs<List<LabelDto>>> listItems(
      @RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<LabelDto> response = new PaginatedResponse<>();
    response.setData(labelService.list());
    response.setTotalRows(999L);

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }


}
