package com.bb.offerapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.option.Msg;

import java.util.List;

public class MsgAdapter extends ArrayAdapter<Msg> {
	private int resouceId;
	
	
	public MsgAdapter(Context context, int resource, List<Msg> objects) {
		super(context, resource, objects);
		resouceId=resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Msg msg = getItem(position);
		ViewHolder holder;
		View view;
		if(convertView==null){
			view= View.inflate(getContext(), resouceId, null);
			holder=new ViewHolder();
			holder.left_layout=(LinearLayout) view.findViewById(R.id.left_layout);
			holder.right_layout=(LinearLayout) view.findViewById(R.id.right_layout);
			holder.left_msg=(TextView) view.findViewById(R.id.left_msg);
			holder.right_msg=(TextView) view.findViewById(R.id.right_msg);
			view.setTag(holder);
		}else{
			view=convertView;
			holder = (ViewHolder) view.getTag();
			
		}
		if(msg.getType()==Msg.TYPE_RECEIVED){
			holder.left_layout.setVisibility(View.VISIBLE);
			holder.right_layout.setVisibility(View.GONE);
			holder.left_msg.setText(msg.getContent());
		}else if(msg.getType()==Msg.TYPE_SENT){
			holder.left_layout.setVisibility(View.GONE);
			holder.right_layout.setVisibility(View.VISIBLE);
			holder.right_msg.setText(msg.getContent());
		}
		
		return view;
		
		
		
	}
	class ViewHolder{
		LinearLayout left_layout,right_layout;
		TextView left_msg,right_msg;
	}
	

}











