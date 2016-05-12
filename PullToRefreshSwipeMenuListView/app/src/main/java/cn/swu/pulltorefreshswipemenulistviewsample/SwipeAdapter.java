package cn.swu.pulltorefreshswipemenulistviewsample;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class SwipeAdapter extends BaseAdapter {
	/**
	 * 上下文对象
	 */
	private Context mContext = null;

	/**
	 *
	 */
	private int mRightWidth = 0;

	/**
	 * 单击事件监听器
	 */
	private List<MessageBean> messageBeans;

	/**
	 */
	public SwipeAdapter(Context ctx,  List<MessageBean> messageBeans) {
		mContext = ctx;
		this.messageBeans=messageBeans;
	}

	public void setmRightWidth(int mRightWidth) {
		this.mRightWidth = mRightWidth;
	}

	public void setDatas(List<MessageBean> messageBeans){
		this.messageBeans=messageBeans;
	}
	@Override
	public int getCount() {
		return messageBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return messageBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder item;
		final int thisPosition = position;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_app, parent, false);
			item = new ViewHolder();
			item.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
			item.iv_icon = (ImageView)convertView.findViewById(R.id.iv_icon);
			convertView.setTag(item);
		} else {// 有直接获得ViewHolder
			item = (ViewHolder)convertView.getTag();
		}
		MessageBean messageBean=messageBeans.get(position);
		item.tv_name.setText(messageBean.getName() + messageBean.getContent());
		textClick(item.tv_name, messageBean);
//		convertView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(mContext, "item点击了", Toast.LENGTH_SHORT).show();
//			}
//		});
		item.iv_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "头像点击", Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	private class ViewHolder {
		ImageView iv_icon;
		TextView tv_name;
	}
	private void textClick(TextView text_click,MessageBean bean){
		SpannableString spannableString1 = new SpannableString(text_click.getText());
		String textString=text_click.getText().toString();
		if(textString.contains(bean.getName())){
			int start=textString.indexOf(bean.getName());
			int end=start+bean.getName().length();
			spannableString1.setSpan(new TextViewURLSpan(bean), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			text_click.setText(spannableString1);
			text_click.setMovementMethod(LinkMovementMethod.getInstance());
		}
	}
	private class TextViewURLSpan extends ClickableSpan {
		private MessageBean bean;
		public TextViewURLSpan(MessageBean bean) {
			this.bean=bean;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
		}

		@Override
		public void onClick(View widget) {
			Toast.makeText(mContext, "名字点击", Toast.LENGTH_SHORT).show();
		}
	}
}
