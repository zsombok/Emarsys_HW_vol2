package models.enums;

public enum PartOfDay {

  AM, PM;

  public boolean isPM() {
    return this.ordinal() == PartOfDay.PM.ordinal();
  }
}
