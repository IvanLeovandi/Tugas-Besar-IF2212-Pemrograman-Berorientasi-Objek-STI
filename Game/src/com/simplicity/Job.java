package com.simplicity;

import java.util.*;

public class Job {
    private String jobName;
    private int salary;
    private int durationOfWork;
    private int durationNotPaid;

    private static HashMap <String, Integer> jobList = new HashMap<String, Integer>(){{ 
        put("MAGIC CLOWN", 15); 
        put("CHEF", 30); 
        put("POLICE", 35);  
        put("PROGRAMMER", 45); 
        put("DOCTOR", 50);  
    }};  
    
    public Job() {
        String[] keys = jobList.keySet().toArray(new String[jobList.size()]);
        String randomKey = keys[new Random().nextInt(keys.length)];

        if (!jobList.containsKey(randomKey))
            throw new IllegalArgumentException("Job name is not valid");
        else {
            this.jobName = randomKey;
            this.salary = jobList.get(jobName);
        }

        this.durationOfWork = 0;
    }

    public Job(String jobName) {
        if (!jobList.containsKey(jobName))
            throw new IllegalArgumentException("Job name is not valid");
        else {
            this.jobName = jobName;
            this.salary = jobList.get(jobName);
        }

        this.durationOfWork = 0;
    }

    public String getJobName() {
        return jobName;
    }

    public int getSalary() {
        return salary;
    }

    public int getDurationOfWork() {
        return durationOfWork;
    }

    public int getDurationNotPaid() {
        return durationNotPaid;
    }

    public HashMap <String, Integer> getJobList() {
        return jobList;
    }

    public void setDurationOfWork(int durationOfWork) {
        this.durationOfWork = durationOfWork;
    }

    public void setDurationNotPaid(int durationNotPaid) {
        this.durationNotPaid = durationNotPaid;
    }
}


