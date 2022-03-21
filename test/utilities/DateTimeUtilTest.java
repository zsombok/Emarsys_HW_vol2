package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import models.entities.WorkingDateTime;
import models.enums.Day;
import models.enums.Month;
import models.enums.PartOfDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DateTimeUtilTest {

  DateTimeUtil dateTimeUtil;

  @BeforeEach
  public void setUp() {
    dateTimeUtil = new DateTimeUtil();
  }

  @Nested
  @DisplayName(value = "Day of week tests")
  public class DayOfWeekTests {

    @Test
    public void returnsCorrectDayOfWeek_when_calledWithValidArguments() {
      int year = 2022;
      Month month = Month.JAN;
      int dayOfMonth = 29;
      String expectedDay = "SAT";
      Day actualDay = dateTimeUtil.dayOfWeek(year, month, dayOfMonth);
      assertEquals(expectedDay, actualDay.toString());
    }

    @Test
    public void returnsIncorrectDayOfWeek_when_calledWithValidArguments() {
      int year = 2022;
      Month month = Month.JAN;
      int dayOfMonth = 29;
      String unexpectedDay = "MON";
      Day actualDay = dateTimeUtil.dayOfWeek(year, month, dayOfMonth);
      assertNotEquals(unexpectedDay, actualDay.toString());
    }

  }

  @Nested
  @DisplayName(value = "Is leap year tests")
  class IsLeapYearTests {


    @Test
    void returnsTrue_when_yearIsLeapYear() {
      int year = 2024;
      boolean condition = dateTimeUtil.isLeapYear(year);
      assertTrue(condition);
    }

    @Test
    void returnsTrue_when_yearIsDivisibleBy100And400() {
      int year = 2000;
      boolean condition = dateTimeUtil.isLeapYear(year);
      assertTrue(condition);
    }

    @Test
    void returnsFalse_when_yearIsDivisibleBy100But400() {
      int year = 2100;
      boolean condition = dateTimeUtil.isLeapYear(year);
      assertFalse(condition);
    }

    @Test
    void returnsFalse_when_yearIsNotLeapYear() {
      int year = 2023;
      boolean condition = dateTimeUtil.isLeapYear(year);
      assertFalse(condition);
    }

  }

  @Nested
  @DisplayName(value = "Calculate due date tests")
  class CalculateDueDateTests {

    WorkingDateTime dT;

    @BeforeEach
    public void init() {
      dT = new WorkingDateTime(2022, Month.JAN, 24, 9, PartOfDay.AM, 20);
    }

    @Test
    void throwsException_when_turnaroundTimeIsSmallerThanOne() {
      int turnaroundTime = 0;
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> dateTimeUtil.CalculateDueDate(dT, turnaroundTime));
      assertEquals("Turnaround time must be at least 1 hour!", exception.getMessage());
    }

    @Test
    void returnsCorrectDueDate_when_smallTurnaroundTimeIsGiven() {
      int turnaroundTime = 6;
      WorkingDateTime expectedDueDate = dateTimeUtil.CalculateDueDate(dT, turnaroundTime);
      assertEquals("2022-JAN-24, MON 3:20PM", expectedDueDate.toString());
    }

    @Test
    void returnsCorrectDueDate_when_hugeTurnaroundTimeIsGiven() {
      int turnaroundTime = 4444;
      WorkingDateTime expectedDueDate = dateTimeUtil.CalculateDueDate(dT, turnaroundTime);
      assertEquals("2024-MAR-11, MON 1:20PM", expectedDueDate.toString());
    }

  }

}