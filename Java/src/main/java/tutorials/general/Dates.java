package tutorials.general;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Dates {
  public static void main(String[] args) {
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();
    LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println("LocalDate.now(): " + localDate);
    System.out.println("LocalTime.now(): " + localTime);
    System.out.println("LocalDateTime.now(): " + localDateTime);

    // final DateTimeFormatter sdf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    final DateTimeFormatter sdf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final DateTimeFormatter sdf3 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    final DateTimeFormatter sdf4 = DateTimeFormatter.ofPattern("E, MMM dd yyyy");

    localDate = LocalDate.of(1988, 9, 29);
    System.out.println(localDate.format(sdf1));
    System.out.println(localDate.format(sdf2));
    System.out.println(localDate.format(sdf3));
    System.out.println(localDate.format(sdf4));
  }
}
