package com.renault.dtos;

public class CountryRegionDto {

    private int id;

    private String countryName;

    private String countryLanguage;

    private String regionName;

    public CountryRegionDto(int id, String countryName, String countryLanguage, String regionName) {
        this.id = id;
        this.countryName = countryName;
        this.countryLanguage = countryLanguage;
        this.regionName = regionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryLanguage() {
        return countryLanguage;
    }

    public void setCountryLanguage(String countryLanguage) {
        this.countryLanguage = countryLanguage;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "CountryRegionDto{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryLanguage='" + countryLanguage + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }

}
