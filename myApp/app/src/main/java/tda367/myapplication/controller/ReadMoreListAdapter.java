package tda367.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import tda367.myapplication.R;

/**
 * @author  Tobias Lindgren
 * source: https://www.codeproject.com/Articles/1151814/Android-ExpandablelistView-Tutorial-with-Android-C
 * Responsibility: Adapter for the ExpandableList
 * Used by: ReadMoreFragment
 * Uses: HeaderInfo and DetailInfo, child_row.xml, group_heading.xml
 */

public class ReadMoreListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<HeaderInfo> deptList;

    public ReadMoreListAdapter(Context context, List<HeaderInfo> deptList){
        this.context = context;
        this.deptList = deptList;
    }
    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<DetailInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<DetailInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        HeaderInfo headerInfo = (HeaderInfo) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_heading, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.group_heading);
        heading.setText(headerInfo.getName().trim());

        return convertView;    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DetailInfo detailInfo = (DetailInfo) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_row, null);
        }

        TextView childItem = (TextView) convertView.findViewById(R.id.child_row);
        childItem.setText(detailInfo.getName().trim());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
