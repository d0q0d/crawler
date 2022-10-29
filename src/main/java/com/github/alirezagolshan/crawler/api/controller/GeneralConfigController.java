package com.github.alirezagolshan.crawler.api.controller;

import com.github.alirezagolshan.crawler.api.dto.ConfigRequestModel;
import com.github.alirezagolshan.crawler.api.dto.ConfigResponseModel;
import com.github.alirezagolshan.crawler.api.adapter.GeneralConfigAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.github.alirezagolshan.crawler.util.Constants.*;

@RestController
@RequestMapping(GENERAL_CONFIG_PATH)
@Tag(name = GENERAL_CONFIG_CONTROLLER, description = GENERAL_CONFIG_CONTROLLER_DESCRIPTION)
public class GeneralConfigController {

  private final GeneralConfigAdapter adapter;

  public GeneralConfigController(GeneralConfigAdapter adapter) {
    this.adapter = adapter;
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public void update(@RequestBody @Valid ConfigRequestModel requestModel) {
    adapter.update(requestModel);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public List<ConfigResponseModel> getAll() {
    return adapter.getAll();
  }
}
