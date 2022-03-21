package utilities;

import java.time.LocalDate;
import models.entities.WorkingDateTime;
import models.enums.Day;
import models.enums.Month;

public class DateTimeUtil {

  public Day dayOfWeek(int year, Month month, int dayOfMonth) {
    int[] t = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
    if (month.ordinal() < 2) {
      year--;
    }
    return Day.values()[(year + year / 4 - year / 100 + year / 400 + t[month.ordinal()] + dayOfMonth - 1) % 7];
  }

  public boolean isLeapYear(int year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
  }

  public WorkingDateTime CalculateDueDate(WorkingDateTime submitDateTime, int turnaroundTime) {
    validateTurnaroundTime(turnaroundTime);
    WorkingDateTime resolveDateTime = new WorkingDateTime(submitDateTime);
    resolveDateTime.addTurnaroundTime(turnaroundTime);
    return resolveDateTime;
  }

  public void validateTurnaroundTime(int time) {
    if (time < 1) {
      throw new IllegalArgumentException("Turnaround time must be at least 1 hour!");
    }
  }

  public void validateWorkingDateTime(WorkingDateTime dateTime) throws IllegalArgumentException {
    validateYear(dateTime);
    validateDaysInMonth(dateTime);
    validateDayOfWeek(dateTime);
    validateHour(dateTime);
    validateMinute(dateTime);
  }

  private void validateYear(WorkingDateTime dateTime) throws IllegalArgumentException {
    if (dateTime.getYear() < LocalDate.now().getYear()) {
      throw new IllegalArgumentException("Set year at least to current one!");
    }
  }

  private void validateDaysInMonth(WorkingDateTime dateTime) throws IllegalArgumentException {
    int daysInMonth = dateTime.getMonth().getLength(dateTime.getDateTimeUtil().isLeapYear(dateTime.getYear()));
    if (dateTime.getDayOfMonth() < 1 || dateTime.getDayOfMonth() > daysInMonth) {
      throw new IllegalArgumentException("Given day of month is out of days of current month!");
    }
  }

  private void validateDayOfWeek(WorkingDateTime dateTime) throws IllegalArgumentException {
    if (dateTime.getDayOfWeek().isWeekend()) {
      throw new IllegalArgumentException("Given day is weekend!");
    }
  }

  private void validateHour(WorkingDateTime dateTime) throws IllegalArgumentException {
    if (dateTime.getHour() < WorkingDateTime.WORKING_HOURS_START || dateTime.getHour() >= WorkingDateTime.WORKING_HOURS_END) {
      throw new IllegalArgumentException(
          String.format("Given hour (%d) is out of working hours (%d-%d)!", dateTime.getHour(), WorkingDateTime.WORKING_HOURS_START,
              WorkingDateTime.WORKING_HOURS_END));
    }
  }

  private void validateMinute(WorkingDateTime dateTime) throws IllegalArgumentException {
    if (dateTime.getMinute() < 0 || dateTime.getMinute() >= 60) {
      throw new IllegalArgumentException(String.format("Given minute (%d) is out of bound!", dateTime.getMinute()));
    }
  }

}
