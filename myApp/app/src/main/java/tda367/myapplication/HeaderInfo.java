package tda367.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tubas on 2017-05-21.
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
