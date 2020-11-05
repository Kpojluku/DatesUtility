public interface DatesUtility {
    //Определяет високосный год или нет.
    boolean isLeapYear(int year);

    //Осуществляет проверку даты на корректность.
    boolean isValidDate(int year, int month, int day);

    //Возвращает номер дня недели, где 0 – MON, 6- SUN
    int getDayOfWeek(int year, int month, int day);

    //Форматирует дату в красивом виде. Например Tuesday 14 Feb 2012
    String toString(int year, int month, int day);

    //Вычисляет сколько дней прошло с данной даты по сегодняшнюю
    int countDays(int year, int month, int day);
}
