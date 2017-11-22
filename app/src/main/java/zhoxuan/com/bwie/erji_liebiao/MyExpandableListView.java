package zhoxuan.com.bwie.erji_liebiao;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 周旋
 * 2017/11/22  19:22
 */

public class MyExpandableListView extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;


//    private String[] groups = {"A", "B", "C"};
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}*/
//    private String[][] childs={{"A1","A2","A3","A4"},{"A1","A2","A3", "B4"},{"A1","A2","A3","C4"}};
       List<CartBean.DataBean> groups;
      List<CartBean.DataBean.ListBean> childs;

    public MyExpandableListView(Context context,List<CartBean.DataBean> groups,List<CartBean.DataBean.ListBean> childs) {
        this.context = context;
        this.groups = groups;
        this.childs = childs;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
        Log.d("smyhvae", "-->" + groupPosition);
        return childs.size();
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs.get(childPosition);  //不要误写成groups[groupPosition][childPosition]
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定？一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_group, null);
        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        tv_group.setText(groups.get(groupPosition).getSellerName());
        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_child, null);
        }

        ImageView iv_child = (ImageView) convertView.findViewById(R.id.iv_child);
        TextView tv_child = (TextView) convertView.findViewById(R.id.tv_child);

        //iv_child.setImageResource(resId);
        tv_child.setText(childs.get(childPosition).getTitle());

        return convertView;
    }

    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

