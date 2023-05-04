package es.tresdigits.worklogminer.features.translations;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/translation")
public class TranslationController {

  private final ITranslationService translationService;

  @Autowired
  public TranslationController(ITranslationService translationService) {
    this.translationService = translationService;
  }

  @PreAuthorize("hasAuthority('LABELGROUP_READ') and hasAuthority('LABEL_READ') and hasAuthority('LANGUAGELABEL_READ')")
  @PostMapping("listItems")
  public ResponseEntity<CommonRs<List<Translation>>> listItems(
      @RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<Translation> paginatedResponse = translationService.listItems(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, paginatedResponse.getData(),
        paginatedResponse.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('LANGUAGELABEL_COLLABORATOR') and hasAuthority('LANGUAGELABEL_ALLOWDELETE')")
  @PutMapping("updateItem/{id}")
  public ResponseEntity<CommonRs<Translation>> update(@RequestBody CommonRq<Translation> rq,
      @PathVariable Integer id) {
    rq.getData().setCode(id);

    return CommonUtil.processResponse(rq, StatusResponse.PUT,
        translationService.update(rq.getData()));
  }

}
