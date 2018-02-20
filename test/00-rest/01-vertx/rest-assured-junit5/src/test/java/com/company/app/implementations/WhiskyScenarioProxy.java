package com.company.app.implementations;

import com.company.app.common.ResourceHelper;
import com.company.app.contracts.WhiskyScenario;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

// Proxy
public class WhiskyScenarioProxy implements WhiskyScenario {

  private WhiskyValidatorBase whisky;
  private JsonNode createBody;
  private JsonNode updateBody;
  private JsonNode emptyBody;
  private String id;

  public WhiskyScenarioProxy(WhiskyValidatorBase whisky) {
    this.whisky = whisky;

    try {
      createBody = ResourceHelper.get("data/whisky/create.json");
      updateBody = ResourceHelper.get("data/whisky/update.json");
      emptyBody = ResourceHelper.get("data/whisky/empty.json");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void get() {
    whisky.get(id);
  }

  @Override
  public void getAll() {
    whisky.getAll();
  }

  @Override
  public void create() {
    JsonNode createResp = whisky.create(createBody);
    id = Integer.toString(createResp.path("id").intValue());
  }

  @Override
  public void update() {
    whisky.update(id, updateBody);
  }

  @Override
  public void activate() {
    whisky.activate(id, emptyBody);
  }

  @Override
  public void suspend() {
    whisky.suspend(id, emptyBody);
  }

  @Override
  public void unsuspend() {
    whisky.unsuspend(id, emptyBody);
  }

  @Override
  public void cancel() {
    whisky.cancel(id, emptyBody);
  }

  @Override
  public void delete() {
    whisky.delete(id);
  }
}
