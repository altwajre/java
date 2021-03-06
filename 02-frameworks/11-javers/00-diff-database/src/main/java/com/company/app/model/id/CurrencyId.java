package com.company.app.model.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyId implements Serializable {

  private String name;

  private String countryName;
}
