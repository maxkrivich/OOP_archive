
class ShortDate {

    private int day;
    private int month;

    public ShortDate() {
        this(1, 1);
    }

    public ShortDate(int day, int month) {
        this.day = day;
        this.month = month;
        findError(day, month);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void print() {
        System.out.println(getDay() + "." + getMonth());
    }

    private boolean getError(int d, int m) {
        if (m < 0 | m > 12) {
            return false;
        } else if (d < 0 | d > getNumberOfDays(m)) {
            return false;
        }
        return true;
    }

    private void findError(int d, int m) {
        if (getError(d, m)) {
            return;
        } else {
            System.out.print("Error creating ");
            this.print();
        }

    }

    private int getNumberOfDays(int m) {
        return (int) (28 + (m + Math.floor(m / 8)) % 2 + 2 % m + 2 * Math.floor(1 / m));
    }

    public boolean set(int day, int month) {
        if (getError(day, month)) {
            this.day = day;
            this.month = month;
            return true;
        }
        return false;
    }

    public boolean increment() {
        if (day == 31 & month == 12) {
            day = 1;
            month = 1;
            return false;
        }
        if (day + 1 <= getNumberOfDays(month)) {
            day++;
            return true;
        } else if (day == getNumberOfDays(month)) {
            day = 1;
            month++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        ShortDate obj = new ShortDate(31, 12);

        for (int m = 0; m < 12; m++) {
            for (int d = 1; d <= days[m]; d++) {
                obj.increment();
                obj.print();
            }
        }
    }

}
