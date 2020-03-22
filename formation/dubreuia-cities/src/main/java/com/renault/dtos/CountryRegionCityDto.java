package com.renault.dtos;

public class CountryRegionCityDto {

    private int id;

    private String countryName;

    private String countryLanguage;

    private String regionName;

    private String cityName;

    public CountryRegionCityDto(int id, String countryName, String countryLanguage, String regionName, String cityName) {
        this.id = id;
        this.countryName = countryName;
        this.countryLanguage = countryLanguage;
        this.regionName = regionName;
        this.cityName = cityName;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CountryRegionCityDto{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryLanguage='" + countryLanguage + '\'' +
                ", regionName='" + regionName + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }

}
