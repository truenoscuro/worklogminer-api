package es.tresdigits.worklogminer.features.example;

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
@RequestMapping("/example")
public class ExampleController {

  private final IExampleService exampleService;

  public ExampleController(IExampleService exampleService) {
    this.exampleService = exampleService;
  }

  @PreAuthorize("hasAuthority('EXAMPLE_READ')")
  @PostMapping("/listItems")
  public ResponseEntity<CommonRs<List<ExampleDto>>> list(
      @RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<ExampleDto> response = exampleService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('EXAMPLE_READ')")
  @GetMapping("/getItem/{id}")
  public ResponseEntity<CommonRs<ExampleDto>> findById(BasicRq rq, @PathVariable("id") Integer id) {
    ExampleDto pos = exampleService.findById(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, pos);
  }

  @PreAuthorize("hasAuthority('EXAMPLE_COLABORATOR')")
  @PostMapping(value = "/createItem")
  public ResponseEntity<CommonRs<ExampleDto>> create(@RequestBody CommonRq<ExampleDto> rq) {
    ExampleDto posDto = exampleService.create(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, posDto);
  }

  @PreAuthorize("hasAuthority('EXAMPLE_ALLOWDELETE')")
  @DeleteMapping("/deleteItem/{id}")
  public void delete(@PathVariable("id") Integer id) {
    exampleService.delete(id);
  }

  @PreAuthorize("hasAuthority('EXAMPLE_COLABORATOR')")
  //Reseteo de creacion y seteo de modificacion con dummys en usuario
  @PutMapping("/updateItem/{id}")
  public ResponseEntity<CommonRs<ExampleDto>> update(@RequestBody CommonRq<ExampleDto> rq,
      @PathVariable Integer id) {
    rq.getData().setCode(id);
    ExampleDto posDto = exampleService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.PUT, posDto);
  }

  @PreAuthorize("hasAuthority('EXAMPLE_READ')")
  @GetMapping("listdropdownsize/{languageCode}")
  public ResponseEntity<CommonRs<List<SelectOption>>> listDropdownSize(
      @PathVariable String languageCode, BasicRq rq) {
    rq.setLanguageCode(languageCode);

    return CommonUtil.processResponse(rq, StatusResponse.GET, exampleService.getDropdownSize());
  }

  @PreAuthorize("hasAuthority('EXAMPLE_READ')")
  @GetMapping("listdropdowncolor/{languageCode}")
  public ResponseEntity<CommonRs<List<SelectOption>>> listDropdownColor(
      @PathVariable String languageCode, BasicRq rq) {
    rq.setLanguageCode(languageCode);

    return CommonUtil.processResponse(rq, StatusResponse.GET, exampleService.getDropdownColor());
  }
}
