import models.entities.WorkingDateTime;
import models.enums.Month;
import models.enums.PartOfDay;
import utilities.DateTimeUtil;

public class Main {

  public static void main(String[] args) {
    WorkingDateTime submitDateTime = new WorkingDateTime(2022, Month.JAN, 24, 9, PartOfDay.AM, 20);
    int turnaroundTime = 4444;

    System.out.print(submitDateTime);
    System.out.print(" + ");
    System.out.println(turnaroundTime + " hours");

    DateTimeUtil dateTimeUtil = new DateTimeUtil();

    WorkingDateTime resolveDateTime = dateTimeUtil.CalculateDueDate(submitDateTime, turnaroundTime);
    System.out.println(resolveDateTime);
  }

}
