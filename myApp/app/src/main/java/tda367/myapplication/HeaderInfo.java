package tda367.myapplication;

import java.util.ArrayList;

/**
 * Created by Tubas on 2017-05-21.
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
