package models.enums;

public enum Day {

  MON(1, "Monday"),
  TUE(2, "Tuesday"),
  WED(3, "Wednesday"),
  THU(4, "Thursday"),
  FRI(5, "Friday"),
  SAT(6, "Saturday"),
  SUN(7, "Sunday");

  private final int index;
  private final String name;

  Day(int index, String name) {
    this.index = index;
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public String getName() {
    return name;
  }

  Day getDayByIndex(int index) {
    return Day.values()[index - 1];
  }

  public Day getNextWeekday() {
    int nextDayIndex = this.index >= 5 ? 1 : this.index + 1;
    return getDayByIndex(nextDayIndex);
  }

  public boolean isWeekend() {
    return this == SAT || this == SUN;
  }
}
