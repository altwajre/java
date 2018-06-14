package com.company.app.controller;

import com.company.app.State;
import com.company.app.Whisky;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Api(tags = "Whisky", value = "onlinestore", description = "Operations pertaining to products in Online Store")
@RestController
public class WhiskyController {
  private Map<Integer, Whisky> products = new LinkedHashMap<>();

  public WhiskyController() {
    createSomeData();
  }

//  @ApiOperation(value = "View a list of available products", response = Iterable.class, tags = {"item1", "name:Tom"})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
  }
  )
  @RequestMapping(value = "/whiskies", method = RequestMethod.GET)
  public Iterable<Whisky> getAll() {
    return products.values();
  }

  @RequestMapping(value = "/whiskies/{id}", method = RequestMethod.GET)
  public Whisky get(@PathVariable String id) {
    final Integer idAsInteger = Integer.valueOf(id);
    Whisky whisky = products.get(idAsInteger);

    return whisky;
  }

  @ApiOperation(value = "create a whisky")
  @PostMapping(value = "/whiskies")
  public ResponseEntity<Void> create(@RequestBody Whisky whisky) {
    whisky.setState(State.ACTIVE);
    int id = whisky.getId();
    products.put(id, whisky);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(whisky.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @PutMapping(value = "/whiskies/{id}")
  public ResponseEntity<Whisky> update(@PathVariable("id") String id, @RequestBody Whisky whisky) {
    final Integer idAsInteger = Integer.valueOf(id);
    Whisky currentWhisky = products.get(idAsInteger);

    currentWhisky.setName(whisky.getName());
    currentWhisky.setOrigin(whisky.getOrigin());
    currentWhisky.setFats(whisky.getFats());

    return new ResponseEntity<Whisky>(currentWhisky, HttpStatus.OK);
  }

  @DeleteMapping(value = "/whiskies/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") String id) {
    final Integer idAsInteger = Integer.valueOf(id);
    products.remove(idAsInteger);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  private void createSomeData() {
    Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay", Arrays.asList("fat_1", "fat_2"));
    bowmore.setState(State.ACTIVE);
    products.put(bowmore.getId(), bowmore);
    Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island", Arrays.asList("fat_a", "fat_b"));
    talisker.setState(State.ACTIVE);
    products.put(talisker.getId(), talisker);
  }
}
