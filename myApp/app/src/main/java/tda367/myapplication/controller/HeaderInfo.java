package tda367.myapplication.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tobias Lindgren
 * source: https://www.codeproject.com/Articles/1151814/Android-ExpandablelistView-Tutorial-with-Android-C
 * Responsibility:
 * Used by: ReadMoreFragment and ReadMoreListAdapter
 */

public class HeaderInfo {

    private String name;
    private List<DetailInfo> productList = new ArrayList<>();

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<DetailInfo> getProductList(){
        return productList;
    }

    public void setProductList(List<DetailInfo> detailList){
        this.productList = detailList;
    }
}
