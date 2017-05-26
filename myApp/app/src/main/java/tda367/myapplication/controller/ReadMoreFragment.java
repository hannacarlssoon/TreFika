package tda367.myapplication.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tda367.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * @author  Hanna Carlsson, revised by Tobias Lindgren
 * Responsibility: Creating the ExpandableListView and setting the information
 * Used by: MainActivity
 * Uses: ReadMoreListAdapter, HeaderInfo and DetailInfo
 */
public class ReadMoreFragment extends Fragment {

    private Map<String, HeaderInfo> mySection = new LinkedHashMap<>();
    private List<HeaderInfo> SectionList = new ArrayList<>();

    private ReadMoreListAdapter listAdapter;
    private ExpandableListView expandableListView;

    public ReadMoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_more, container, false);

        //Just add some data to start with
        addProducts();
        //get reference to the ExpandableListView
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_list);
        //create the adapter by passing your ArrayList data
        listAdapter = new ReadMoreListAdapter(view.getContext(), SectionList);
        //attach the adapter to the list
        expandableListView.setAdapter(listAdapter);

        //expand all Groups
        collapseAll();

        // Inflate the layout for this fragment
        return view;
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void addProducts(){

        addProduct("Typer","I java sparas värden i variabler. Dessa variabler har en deklarerad typ. Det finns 8 primitiva typer, där de vanligaste är boolean, int, double och char. booleans kan hålla två olika värden, true och false. double och int håller båda tal, där double håller decimaltal och int håller heltal. char kan hålla ett tecken. De primitiva typerna skrivs alltid med liten inledande bokstav. Exempel på hur variabler av dessa typer deklareras och instansieras kan ses nedan:\n" +
                "\n" +
                "int i = 5;\n" +
                "char alpha = ‘a’;\n" +
                "boolean isThisTrue = false;\n" +
                "double decimals = 2.5;\n" +
                "\n" +
                "Det finns också andra typer i java, en väldigt användbar är String. Denna typen håller strängar av text. String skrivs med stor inledande bokstav. En String-variabel kan deklareras som String text;");
        addProduct("Boolean-logik och If-satser","Cabbage");
        addProduct("For- och While-loopar","Onion");

        addProduct("Arrayer","Apple");
    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product){

        int groupPosition;

        //check the hash map if the group already exists
        HeaderInfo headerInfo = mySection.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new HeaderInfo();
            headerInfo.setName(department);
            mySection.put(department, headerInfo);
            SectionList.add(headerInfo);
        }

        //get the children for the group
        List<DetailInfo> productList = headerInfo.getProductList();

        //create a new child and add that to the group
        DetailInfo detailInfo = new DetailInfo();
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = SectionList.indexOf(headerInfo);
        return groupPosition;
    }

}
