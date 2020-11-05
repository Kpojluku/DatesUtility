public class DatesUtilityImpl implements DatesUtility {

    @Override
    public boolean isLeapYear(int year) {
        return (((double) year / 400) % 1 == 0) || ((double) year / 4) % 1 == 0
                && ((double) year / 100) % 1 != 0 && ((double) year / 400) % 1 != 0;
    }

    @Override
    public boolean isValidDate(int year, int month, int day) {

        if (month < 1 || month > 12 || day > 31 || day < 1 || year == 0) {
            return false;
        }
        if (month == 2 && day == 29 && !isLeapYear(year) || month == 2 && day > 29) {
            return false;
        }
        // если день месяца больше, чем может быть в этом месяце, то false
        if ((month <= 7 && month % 2 == 0) || month == 9 || month == 11) {
            return day != 31;
        }
        return true;
    }

    @Override
    public int getDayOfWeek(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            System.out.println("This date is not valid");
            return -1;
        }
        //Zeller's congruence
        if (month == 1) {
            month = 13;
            year--;
        }
        if (month == 2) {
            month = 14;
            year--;
        }

        int k = year % 100;
        int j = year / 100;
        int h = day + 13 * (month + 1) / 5 + k + k / 4 + j / 4 + 5 * j;
        h = h % 7;
        return h - 2;
    }

    @Override
    public String toString(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            System.out.println("This date is not valid");
            return null;
        }

        String sMonth = getStringMonth(month);
        String sDayOfWeek = getStringDayOfWeek(year, month, day);

        return sDayOfWeek + " " + day + " " + sMonth + " " + year;
    }

    @Override
    public int countDays(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            System.out.println("This date is not valid");
            return -1;
        }
        int absolutelyDaysCurrent = getAbsolutelyDaysCurrent();
        int absolutelyDaysGiven = getAbsolutelyDaysGiven(year, month, day);

        if (year >= 1970) {
            return abs(absolutelyDaysCurrent - absolutelyDaysGiven);
        } else {
            return abs(absolutelyDaysCurrent + absolutelyDaysGiven - 1);
        }
    }

    private int getAbsolutelyDaysCurrent() {

        int countYear = 0;
        int countDay = 0;
        long i = System.currentTimeMillis();

        //31536000000L - количество миллисекунд  в году
        while (i >= 31536000000L) {
            i = i - 31536000000L;
            countYear++;
        }
        //86400000 - количество миллисекунд  в сутках
        while (i > 0) {
            i = i - 86400000;
            countDay++;
        }
        return countYear * 365 + countDay;
    }

    private int getAbsolutelyDaysGiven(int year, int month, int day) {
        int[] monthDays = {31, 28, 31, 30, 31, 30,
                31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            monthDays[1] = 29;
        }
        int countDays = 0;
        for (int i = 0; i < month - 1; i++) {
            countDays += monthDays[i];
        }
        countDays += day;

        int countLeapYear = 0;
        if (year < 1970) {
            for (int i = year; i < 1970; i++) {
                if (isLeapYear(i)) {
                    countLeapYear++;
                }
            }
            countDays--;
        } else {
            for (int i = 1970; i < year; i++) {
                if (isLeapYear(i)) {
                    countLeapYear++;
                }
            }
        }
        if (year < 0) {
            countLeapYear--;
            year++;
        }

        int differentYear = abs(year - 1970);

        if (year < 1970) {
            return differentYear * 365 + countLeapYear - countDays;
        } else {
            return differentYear * 365 + countLeapYear + countDays;
        }
    }

    private String getStringMonth(int month) {
        String result = "";

        switch (month) {
            case 1:
                result = "Jan";
                break;
            case 2:
                result = "Feb";
                break;
            case 3:
                result = "Mar";
                break;
            case 4:
                result = "Apr";
                break;
            case 5:
                result = "May";
                break;
            case 6:
                result = "June";
                break;
            case 7:
                result = "July";
                break;
            case 8:
                result = "Aug";
                break;
            case 9:
                result = "Sept";
                break;
            case 10:
                result = "Oct";
                break;
            case 11:
                result = "Nov";
                break;
            case 12:
                result = "Dec";
                break;
        }

        return result;
    }

    private String getStringDayOfWeek(int year, int month, int day) {
        String result = "";
        switch (getDayOfWeek(year, month, day)) {
            case 0:
                result = "Monday";
                break;
            case 1:
                result = "Tuesday";
                break;
            case 2:
                result = "Wednesday";
                break;
            case 3:
                result = "Thursday";
                break;
            case 4:
                result = "Friday";
                break;
            case 5:
                result = "Saturday";
                break;
            case 6:
                result = "Sunday";
                break;
        }
        return result;
    }

    private int abs(int a) {
        return (a < 0) ? -a : a;
    }
}