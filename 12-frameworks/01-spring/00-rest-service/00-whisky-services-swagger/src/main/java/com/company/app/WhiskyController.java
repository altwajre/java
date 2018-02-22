package com.company.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WhiskyController {
  private Map<Integer, Whisky> products = new LinkedHashMap<>();

  public WhiskyController() {
    createSomeData();
  }

  @RequestMapping(value = "/whiskies", method = RequestMethod.GET)
  public Collection<Whisky> getAll() {
    return products.values();
  }

  @RequestMapping(value = "/whiskies/{id}", method = RequestMethod.GET)
  public Whisky get(@PathVariable String id) {
    final Integer idAsInteger = Integer.valueOf(id);
    Whisky whisky = products.get(idAsInteger);

    return whisky;
  }

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
