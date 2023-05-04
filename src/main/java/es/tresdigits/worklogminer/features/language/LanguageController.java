package es.tresdigits.worklogminer.features.language;

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
@RequestMapping("/language")
public class LanguageController {

  private final ILanguageService languageService;

  public LanguageController(ILanguageService languageService) {
    this.languageService = languageService;
  }

  @PreAuthorize("hasAuthority('LANGUAGE_READ')")
  @PostMapping("/listItems")
  public ResponseEntity<CommonRs<List<LanguageDto>>> list(
      @RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<LanguageDto> response = languageService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('LANGUAGE_READ')")
  @GetMapping("/listSelectOptions")
  public ResponseEntity<CommonRs<List<SelectOption>>> listTableEditorSelectOptions(
      BasicRq rq) {
    List<SelectOption> languageDtoList = languageService.listTableEditorSelectOptions();

    return CommonUtil.processResponse(rq, StatusResponse.GET, languageDtoList);
  }

  @PreAuthorize("hasAuthority('LANGUAGE_READ')")
  @GetMapping("/getItem/{id}")
  public ResponseEntity<CommonRs<LanguageDto>> findById(BasicRq rq,
      @PathVariable("id") Integer id) {
    LanguageDto lang = languageService.findById(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, lang);
  }

  @PreAuthorize("hasAuthority('LANGUAGE_ALLOWDELETE')")
  @DeleteMapping("/deleteItem/{id}")
  public void delete(@PathVariable("id")Integer id){
    languageService.delete(id);
  }

  @PreAuthorize("hasAuthority('LANGUAGE_COLABORATOR')")
  @PostMapping("/createItem")
  public ResponseEntity<CommonRs<LanguageDto>> create(@RequestBody CommonRq<LanguageDto> rq){
    LanguageDto langDto = languageService.create(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, langDto);
  }

  @PreAuthorize("hasAuthority('LANGUAGE_COLABORATOR')")
  @PutMapping("/updateItem/{id}")
  public ResponseEntity<CommonRs<LanguageDto>> update(@RequestBody CommonRq<LanguageDto> rq,
      @PathVariable Integer id) {
    rq.getData().setCode(id);
    LanguageDto langDto = languageService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.PUT, langDto);
  }

  @GetMapping("getTranslations/{langCode}")
  public Map<String, Map<String, String>> getTranslations(@PathVariable String langCode) {
    return languageService.getTranslations(langCode);
  }

}
