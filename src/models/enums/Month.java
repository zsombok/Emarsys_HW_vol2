package models.enums;

public enum Month {

  JAN(31, "January"),
  FEB(28, "February"),
  MAR(31, "March"),
  APR(30, "April"),
  MAY(31, "May"),
  JUN(30, "June"),
  JUL(31, "July"),
  AUG(31, "August"),
  SEP(30, "September"),
  OCT(31, "October"),
  NOV(30, "November"),
  DEC(31, "December");

  private final int length;
  private final String name;

  Month(int length, String name) {
    this.length = length;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getLength(boolean isLeapYear) {
    if (isLeapYear && this.ordinal() == Month.FEB.ordinal()) {
      return 29;
    }
    return length;
  }

  public Month getByIndex(int index) {
    return Month.values()[index - 1];
  }

  public Month getNext() {
    return Month.values()[(this.ordinal() + 1) % 12];
  }
}
