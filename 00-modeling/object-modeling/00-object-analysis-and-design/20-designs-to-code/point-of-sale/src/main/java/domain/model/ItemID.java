package domain.model;

public class ItemID {
  private int id;

  public ItemID(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemID itemID = (ItemID) o;
    return id == itemID.id;
  }

  @Override
  public int hashCode() {
    return this.id % 7;
  }
}
