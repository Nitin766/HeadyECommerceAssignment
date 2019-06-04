package com.assignment.ecommerce.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseMapper {

    @SerializedName("categories")
    private ArrayList<Category> categoriesList;
    @SerializedName("rankings")
    private  ArrayList<Ranking> rankingList;

    public ResponseMapper(ArrayList<Category> categoriesList, ArrayList<Ranking> rankingList) {
        this.categoriesList = categoriesList;
        this.rankingList = rankingList;
    }

    public ArrayList<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public ArrayList<Ranking> getRankingList() {
        return rankingList;
    }

    public void setRankingList(ArrayList<Ranking> rankingList) {
        this.rankingList = rankingList;
    }
}
