package com.simplicity;

public class Job {
    private int salaryPerDay;
    private String name;

    private Job(String name, int salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
        this.name = name;
    }

    public static final Job clown       = new Job("badut", 15);
    public static final Job CLOWN       = clown;
    public static final Job cook        = new Job("koki", 30);
    public static final Job COOK        = cook;
    public static final Job police      = new Job("polisi", 35);
    public static final Job POLICE      = police;
    public static final Job programmer  = new Job("programmer", 45);
    public static final Job PROGRAMMER  = programmer;
    public static final Job doctor      = new Job("dokter", 50);
    public static final Job DOCTOR      = doctor;

    public int getSalaryPerDay() {
        return salaryPerDay;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Job: " + name;
    }
}
