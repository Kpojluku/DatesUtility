public class Main {
    public static void main(String[] args) {
        DatesUtilityImpl datesUtility = new DatesUtilityImpl();
        System.out.println(datesUtility.isLeapYear(2000));
        System.out.println(datesUtility.isValidDate(2019, 2, 29));
        System.out.println(datesUtility.getDayOfWeek(2021, 6, 16));
        System.out.println(datesUtility.toString(2021, 6, 16));
        System.out.println(datesUtility.countDays(2021, 10, 17));
    }
}