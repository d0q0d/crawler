package com.github.alirezagolshan.crawler.api.controller;

import com.github.alirezagolshan.crawler.api.dto.AirportCodeInputModel;
import com.github.alirezagolshan.crawler.model.AirportCode;
import com.github.alirezagolshan.crawler.api.adapter.AirportCodeAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.github.alirezagolshan.crawler.util.Constants.*;

@RestController
@RequestMapping(AIRPORT_CODE_PATH)
@Tag(name = AIRPORT_CODE_CONTROLLER, description = AIRPORT_CODE_CONTROLLER_DESCRIPTION)
public class AirportCodeController {

  private final AirportCodeAdapter adapter;

  public AirportCodeController(AirportCodeAdapter adapter) {
    this.adapter = adapter;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public List<AirportCode> getAll() {
    return adapter.getAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public void save(@RequestBody @Valid AirportCodeInputModel airportCodeInputModel) {
    adapter.save(airportCodeInputModel);
  }

  @DeleteMapping(AIRPORT_CODE_DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public void delete(@PathVariable("airport-id") String id) {
    adapter.delete(id);
  }
}
