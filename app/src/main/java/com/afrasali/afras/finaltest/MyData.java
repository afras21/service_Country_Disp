package com.afrasali.afras.finaltest;

/**
 * Created by Afras Ali on 6/16/2017.
 */

class MyData {
    private int Rank;
    private String CountryName;
    private String CountryPopulation;
    private String img;

    public MyData(int rank,String country, String population, String flag) {

        this.Rank=rank;
        this.CountryPopulation=population;
        this.img=flag;
        this.CountryName=country;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryPopulation() {
        return CountryPopulation;
    }

    public void setCountryPopulation(String countryPopulation) {
        CountryPopulation = countryPopulation;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

