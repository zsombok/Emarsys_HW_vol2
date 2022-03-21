package models.entities;

import models.enums.Month;
import models.enums.PartOfDay;

public class WorkingDateTime extends DateTime {

  public static final int WORKING_HOURS_START = 9;
  public static final int WORKING_HOURS_END = 17;
  public static final int WORKING_HOURS_DURATION = WORKING_HOURS_END - WORKING_HOURS_START;

  public WorkingDateTime(int year, Month month, int dayOfMonth, int hour, PartOfDay partOfDay, int minute) throws IllegalArgumentException {
    super(year, month, dayOfMonth, hour, partOfDay, minute);
    dateTimeUtil.validateWorkingDateTime(this);
  }

  public WorkingDateTime(WorkingDateTime workingDateTime) {
    super(workingDateTime);
  }

  public void addTurnaroundTime(int turnaroundTime) {
    int potentialDueTime = hour + turnaroundTime;
    while (potentialDueTime >= WORKING_HOURS_END) {
      potentialDueTime -= WORKING_HOURS_DURATION;
      addWeekday();
    }
    setHour(potentialDueTime);
  }

  private void addWeekday() {
    do {
      addDay();
    } while (dayOfWeek.isWeekend());
  }

}
