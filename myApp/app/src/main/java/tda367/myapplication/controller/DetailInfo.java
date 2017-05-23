package tda367.myapplication.controller;

/**
 * Created by: Tobias Lindgren on 2017-05-21.
 * source: https://www.codeproject.com/Articles/1151814/Android-ExpandablelistView-Tutorial-with-Android-C
 * Responsibility:
 * Used by: ReadMoreFragment and ReaMoreListAdapter
 * Uses:
 */

public class DetailInfo {

    private String name = "";

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
