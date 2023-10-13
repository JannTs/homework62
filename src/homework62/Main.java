package homework62;

/**
 * 1. Получить и вывести на экран:
 *      -текущую дату
 *      -текущий год, месяц и день
 * 2.Создать дату , например день рождения и вывести на экран
 * 3.Создать две даты и проверить на равенство
 * 4.Получить и вывести на экран текущее время
 * 5.текущее время + 3 часа
 * 6.Создать дату на неделю позже сегодняшней
 * 7.Создать дату, которая была на год раньше сегодняшней используя метод minus
 * 8.Создать дату на год позже сегодняшней используя plus метод
 * 9.Создать даты tomorrow и yesterday и проверить находятся ли они до или после
 *      сегодняшней
 * 10.Написать метод, принимающий лист из нескольких дат типа LocalDate и
 *      возвращающий количество дней между самой ранней и поздней датами
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1. Получить и вывести на экран текущую дату
        LocalDate currentDate = LocalDate.now();
        System.out.println("\n1. The current date: " + currentDate);
        System.out.println("    day :   " + currentDate.getDayOfMonth());
        System.out.println("    month:  " + currentDate.getMonth());
        System.out.println("    year:   " + currentDate.getYear());

        // 2. Создать дату (день рождения) и вывести на экран
        LocalDate birthday = LocalDate.of(1967, 1, 6);
        System.out.println("\n2. Birthday: " + birthday);

        // 3. Создать две даты и проверить на равенство
        System.out.println("\n3. Date matching");
        LocalDate date1 = LocalDate.of(2023, 9, 29);
        LocalDate date2 = LocalDate.of(2023, 10, 29);
        boolean areEqual = date1.equals(date2);
        System.out.println("    " + date1);
        System.out.println("    " + date2);
        System.out.println("The equivalence of these dates is " +  areEqual);

        // 4. Получить и вывести на экран текущее время
        System.out.println("\n4. Current time (\"hh:mm:ss\"): "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:m:s")));

        // 5. Текущее время + 3 часа
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime plus3hoursFromCurrentTime = currentTime.plusHours(3);
        System.out.println("\n5. Current time + 3 hours: " + plus3hoursFromCurrentTime);

        // 6. Создать дату на неделю позже сегодняшней
        LocalDate exactlyInAWeek = LocalDate.now().plusWeeks(1);
        System.out.println("\n6. Exactly in a week: " + exactlyInAWeek);

        // 7. Создать дату, которая была на год раньше сегодняшней
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        System.out.println("\n7. This was exactly a year ago: " + oneYearAgo);

        // 8. Создать дату на год позже сегодняшней
        LocalDate oneYearLater = LocalDate.now().plusYears(1);
        System.out.println("\n8. Exactly one year later: " + oneYearLater);

        // 9. Создать даты tomorrow и yesterday и проверить находятся ли они до или после сегодняшней
        System.out.println("\n9. tomorrow&yesterday" );
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        System.out.println(tomorrow+" this is tomorrow's date - " + tomorrow.isAfter(currentDate));
        System.out.println(yesterday+" it was yesterday - " + yesterday.isBefore(currentDate));

        // 10. Написать метод, принимающий лист из нескольких дат типа LocalDate и
        // возвращающий количество дней между самой ранней и поздней датами
        System.out.println("\n10. List of various dates: ");
        List<LocalDate> dates = List.of(LocalDate.now(),
                                LocalDate.now().plusDays(3),
                                LocalDate.now(),
                                LocalDate.now().plusDays(3),
                                LocalDate.now().minusWeeks(5),
                                LocalDate.now(),
                                LocalDate.now().plusMonths(2),
                                LocalDate.now().minusDays(11)
              );
        dates.forEach(date -> System.out.print(" "+date+" "));
        long daysBetween = maxDaysBetweenDatesV2(dates);
        System.out.println("\nMaximum period of days covered by dates from the list: "+daysBetween+" days period." );
   }
    //first release
    public static long maxDaysBetweenDates(List<LocalDate> dates) {
        if (dates == null || dates.size() < 2) {
            throw new IllegalArgumentException("List should contain at least two dates");
        }
        List<LocalDate> sortedDates = new ArrayList<>(dates);
        Collections.sort(sortedDates);
        LinkedList<LocalDate> chronologicalList = new LinkedList<>(sortedDates);
        return ChronoUnit.DAYS.between(chronologicalList.getFirst(),chronologicalList.getLast());
    }
    //last release  (return O instead exception)
    public static long maxDaysBetweenDatesV2(List<LocalDate> dates) {
        if (dates == null || dates.size() < 2) {
            return 0;
        }
        return ChronoUnit.DAYS.between(Collections.min(dates), Collections.max(dates));
    }
}
