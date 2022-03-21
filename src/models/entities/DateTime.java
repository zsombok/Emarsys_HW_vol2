package models.entities;

import models.enums.Day;
import models.enums.Month;
import models.enums.PartOfDay;
import utilities.DateTimeUtil;

public class DateTime {

  public static final int HOUR_NOON = 12;
  public static final int HOUR_MIDNIGHT = 0;

  protected final DateTimeUtil dateTimeUtil;

  protected int year;
  protected Month month;
  protected int monthLength;
  protected int dayOfMonth;
  protected Day dayOfWeek;
  protected int hour;
  protected PartOfDay partOfDay;
  protected int minute;

  {
    this.dateTimeUtil = new DateTimeUtil();
  }

  public DateTime(int year, Month month, int dayOfMonth, int hour, PartOfDay partOfDay, int minute) {
    this.year = year;
    this.month = month;
    setMonthLength();
    this.dayOfMonth = dayOfMonth;
    setDayOfWeek();
    this.hour = convertHourFromStandardToMilitary(hour, partOfDay);
    this.partOfDay = partOfDay;
    this.minute = minute;
  }

  public DateTime(DateTime dateTime) {
    this.year = dateTime.year;
    this.month = dateTime.month;
    this.monthLength = dateTime.monthLength;
    this.dayOfMonth = dateTime.dayOfMonth;
    this.dayOfWeek = dateTime.dayOfWeek;
    this.hour = dateTime.hour;
    this.partOfDay = dateTime.partOfDay;
    this.minute = dateTime.minute;
  }

  public DateTimeUtil getDateTimeUtil() {
    return dateTimeUtil;
  }

  public int getYear() {
    return year;
  }

  public Month getMonth() {
    return month;
  }

  public int getMonthLength() {
    return monthLength;
  }

  public int getDayOfMonth() {
    return dayOfMonth;
  }

  public Day getDayOfWeek() {
    return dayOfWeek;
  }

  public int getHour() {
    return hour;
  }

  public PartOfDay getPartOfDay() {
    return partOfDay;
  }

  public int getMinute() {
    return minute;
  }

  public int convertHourFromStandardToMilitary(int hour, PartOfDay partOfDay) {
    return hour + (partOfDay.isPM() ? HOUR_NOON : HOUR_MIDNIGHT);
  }

  public int convertHourFromMilitaryToStandard(int hour) {
    return (hour > 12) ? (hour % HOUR_NOON) : hour;
  }

  public void setHour(int hour) {
    this.partOfDay = hour < HOUR_NOON ? PartOfDay.AM : PartOfDay.PM;
    this.hour = hour;
  }

  public void setMonth(Month month) {
    if (month.ordinal() == Month.JAN.ordinal()) {
      year++;
    }
    this.month = month;
    setMonthLength();
  }

  public void addMonth() {
    setMonth(month.getNext());
  }

  public void setMonthLength() {
    this.monthLength = month.getLength(dateTimeUtil.isLeapYear(year));
  }

  public void addDay() {
    this.dayOfMonth++;
    if (dayOfMonth > monthLength) {
      addMonth();
      this.dayOfMonth = 1;
    }
    setDayOfWeek();
  }

  public void setDayOfWeek() {
    this.dayOfWeek = dateTimeUtil.dayOfWeek(year, month, dayOfMonth);
  }

  @Override
  public String toString() {
    return String.format("%d-%s-%s, %s %d:%02d%s", year, month, dayOfMonth, dayOfWeek, convertHourFromMilitaryToStandard(this.hour), minute,
        partOfDay.toString());
  }
}
