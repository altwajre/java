package gof.creational.prototype.java;

import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@Setter
@Getter
abstract class Cell implements Cloneable {
  public Cell() {
    System.out.println("Constructor of Cell is called ... ");
  }

  private String cellType;
  private String name;

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return cellType + " with id " + hashCode() + " name=" + name;
  }

  public Cell clone() {
    try {
      return (Cell)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}

class Amoeba extends Cell {
  public Amoeba() {
    setCellType("Amoeba");
    System.out.println("Constructor of Amoeba is called ... ");
  }
}

class Bacteria extends Cell {
  public Bacteria() {
    setCellType("Bacteria");
    System.out.println("Constructor of Bacteria is called ... ");
  }
}

class SingleCell extends Cell {
  public SingleCell() {
    setCellType("SingleCell");
    System.out.println("Constructor of SingleCell is called ... ");
  }
}

enum CellProtoTypes {
  SINGLE_CELL_ORG,
  BACTERIA,
  AMOEBA
}

class SpecimenCache {

  static SpecimenCache specimenCache = null;
  private Map<CellProtoTypes, Cell> prototypeSamples = new EnumMap<CellProtoTypes, Cell>(CellProtoTypes.class);

  static public SpecimenCache getInstance() {
    if (specimenCache == null) {
      specimenCache = new SpecimenCache();
    }
    return specimenCache;
  }

  public SpecimenCache() {
    loadSpecimenCache();
  }

  void loadSpecimenCache() {
    prototypeSamples.put(CellProtoTypes.SINGLE_CELL_ORG, new SingleCell());
    prototypeSamples.put(CellProtoTypes.BACTERIA, new Bacteria());
    prototypeSamples.put(CellProtoTypes.AMOEBA, new Amoeba());
  }

  public Cell getCellProtoType(CellProtoTypes cell) {
    return prototypeSamples.get(cell);
  }
}

public class Bonus {
  public static void main(String[] args) {
    System.out.println("Welcome to Prototype design patters Lab ");
    SpecimenCache specimenLab = SpecimenCache.getInstance();

    Cell subjectCell = specimenLab.getCellProtoType(CellProtoTypes.SINGLE_CELL_ORG);

    System.out.println("Culture process has started  ");
    System.out.println(" Cells are spliting/cloning and growing  ");

    for (int i = 0; i < 3; i++) {
      Cell cell = subjectCell.clone();
      System.out.println(cell);
      cell.setName("Tom");
      Cell clone = cell.clone();
      System.out.println(clone);
    }
  }
}
/*
Welcome to Prototype design patters Lab
Constructor of Cell is called ...
Constructor of SingleCell is called ...
Constructor of Cell is called ...
Constructor of Bacteria is called ...
Constructor of Cell is called ...
Constructor of Amoeba is called ...
Culture process has started
 Cells are spliting/cloning and growing
SingleCell with id 644117698 name=null
SingleCell with id 1872034366 name=Tom
SingleCell with id 1581781576 name=null
SingleCell with id 1725154839 name=Tom
SingleCell with id 1670675563 name=null
SingleCell with id 723074861 name=Tom
 */
