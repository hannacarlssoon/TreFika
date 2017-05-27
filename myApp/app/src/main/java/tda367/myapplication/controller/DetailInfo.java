package tda367.myapplication.controller;

/**
 * @author  Tobias Lindgren
 * source: https://www.codeproject.com/Articles/1151814/Android-ExpandablelistView-Tutorial-with-Android-C
 * Responsibility:Setting deatil names in list
 * Used by: ReadMoreFragment and ReaMoreListAdapter
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
