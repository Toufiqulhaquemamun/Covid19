package com.example.coronabd.model;

import java.util.List;

public class Report {
    private String country;
    private String flag;
    private Integer cases;
    private Integer deaths;
    private Integer recovered;
    private List<Object> activeCases = null;
    private List<Object> closedCases = null;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public List<Object> getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(List<Object> activeCases) {
        this.activeCases = activeCases;
    }

    public List<Object> getClosedCases() {
        return closedCases;
    }

    public void setClosedCases(List<Object> closedCases) {
        this.closedCases = closedCases;
    }
}
