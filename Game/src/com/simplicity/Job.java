package com.simplicity;

public class Job {
    private int salaryPerDay;

    private Job(int salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }

    public static final Job clown   = new Job(15);
    public static final Job cook    = new Job(30);
    public static final Job police  = new Job(35);
    public static final Job programmer  = new Job(45);
    public static final Job doctor  = new Job(50);

    public int getSalaryPerDay() {
        return salaryPerDay;
    }
}
