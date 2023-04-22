package com.simplicity;

import java.util.*;

public class Job {
    private String jobName;
    private int salary;

    private static HashMap <String, Integer> jobList = new HashMap<String, Integer>(){{ 
        put("Badut Sulap", 15); 
        put("Koki", 30); 
        put("Polisi", 35);  
        put("Programmer", 45); 
        put("Dokter", 50);  
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
    }

    public String getJobName() {
        return jobName;
    }

    public int getSalary() {
        return salary;
    }
}


