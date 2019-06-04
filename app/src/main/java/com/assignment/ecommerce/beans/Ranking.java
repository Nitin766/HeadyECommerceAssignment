package com.assignment.ecommerce.beans;

import java.util.ArrayList;

public class Ranking {

    private String rank;
    private ArrayList<RankingProduct> rankingProductList;

    public Ranking(String rank, ArrayList<RankingProduct> rankingProductList) {
        this.rank = rank;
        this.rankingProductList = rankingProductList;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public ArrayList<RankingProduct> getRankingProductList() {
        return rankingProductList;
    }

    public void setRankingProductList(ArrayList<RankingProduct> rankingProductList) {
        this.rankingProductList = rankingProductList;
    }
}
