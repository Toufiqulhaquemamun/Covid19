package com.example.coronabd.model;

import java.util.Date;

public class Symptom {
    public int id;
    public String relationWithMobileOwner ;
    public String name ;
    public String age ;
    public String sex ;
    public String niDorBC;
    public String uploadNID ;
    public String symptomDuration ;
    public boolean isReturnFromAbroad ;
    public boolean isMeetWithImmigrantWhoReturned ;
    public String possibleReasonForInfection ;
    public String mostCommonSymptoms ;
    public String lessCommonSymptoms ;
    public String seriousSymptoms ;
    public boolean isCovidTested ;
    public String anyOtherDisease ;
    public Date testDate ;
    public Date createDate ;
    public Date updateDate ;
    public User user;
    public int userId;


}
