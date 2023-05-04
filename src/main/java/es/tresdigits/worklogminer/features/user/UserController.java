package es.tresdigits.worklogminer.features.user;

import es.tresdigits.worklogminer.common.model.BasicRq;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@SecurityRequirement(name = "bearerAuth")
@RestController
public class UserController {

  private final IUserService userService;

  @Autowired
  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PreAuthorize("hasAuthority('USER_READ')")
  @PostMapping("/listItems")
  public ResponseEntity<CommonRs<List<UserDto>>> list(@RequestBody CommonRq<TableContextDto> rq) {
    PaginatedResponse<UserDto> response = userService.list(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, response.getData(),
        response.getTotalRows().intValue());
  }

  @PreAuthorize("hasAuthority('USER_READ')")
  @GetMapping("/getItem/{id}")
  public ResponseEntity<CommonRs<UserDto>> getItem(BasicRq rq, @PathVariable("id") Integer id) {
    UserDto userDto = userService.listUserById(id);

    return CommonUtil.processResponse(rq, StatusResponse.GET, userDto);
  }

  @PreAuthorize("hasAuthority('USER_COLABORATOR')")
  @PostMapping("/createItem")
  public ResponseEntity<CommonRs<UserDto>> post(@RequestBody CommonRq<UserDto> rq) {
    userService.create(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.POST, rq.getData());
  }

  @PreAuthorize("hasAuthority('USER_COLABORATOR')")
  @PutMapping("/updateItem/{id}")
  public ResponseEntity<CommonRs<UserDto>> update(@RequestBody CommonRq<UserDto> rq,
      @PathVariable Integer id) {
    rq.getData().setCode(id);
    userService.update(rq.getData());

    return CommonUtil.processResponse(rq, StatusResponse.PUT, rq.getData());
  }

  @PreAuthorize("hasAuthority('USER_ALLOWDELETE')")
  @DeleteMapping("/deleteItem/{id}")
  public void delete(@PathVariable("id") Integer id) {
    userService.delete(id);
  }

  @GetMapping("/getUserInfo")
  public ResponseEntity<CommonRs<UserInfo>> getLoggedUserInfo(BasicRq rq) {
    return CommonUtil.processResponse(rq, StatusResponse.GET,
        userService.getUserInfo(SecurityContextHolder.getContext().getAuthentication().getName()));
  }

}