package tda367.myapplication.controller;

import java.util.ArrayList;

/**
 * Created by: Tobias Lindgren on 2017-05-21.
 * source: https://www.codeproject.com/Articles/1151814/Android-ExpandablelistView-Tutorial-with-Android-C
 * Responsibility:
 * Used by: ReadMoreFragment and ReadMoreListAdapter
 * Uses:
 */

public class HeaderInfo {

    private String name;
    private ArrayList<DetailInfo> productList = new ArrayList<>();

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<DetailInfo> getProductList(){
        return productList;
    }

    public void setProductList(ArrayList<DetailInfo> detailList){
        this.productList = detailList;
    }
}
