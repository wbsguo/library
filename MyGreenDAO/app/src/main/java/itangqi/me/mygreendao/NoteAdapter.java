package itangqi.me.mygreendao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import itangqi.me.mygreendao.bean.Note;

@SuppressLint("NewApi")
public class NoteAdapter extends BaseAdapter {
	private static final String TAG="NoteAdapter";
	private List<Note> datas;
	private Context ctx;
	public NoteAdapter(List<Note> datas, Context ctx) {
		this.datas = datas;
		this.ctx = ctx;
	}
	public void setDatas(List<Note> datas){
		this.datas = datas;
	}
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.note_item, null);
			viewHolder.id = (TextView) convertView
					.findViewById(R.id.id);
			viewHolder.note_text = (TextView) convertView
					.findViewById(R.id.note_text);
			viewHolder.note_comment = (TextView) convertView
					.findViewById(R.id.note_comment);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final Note bean = datas.get(position);
		if(bean!=null){
			viewHolder.id.setText(String.valueOf(bean.getId()));
			viewHolder.note_text.setText(bean.getText());
			viewHolder.note_comment.setText(bean.getComment());
		}
		return convertView;
	}

	private class ViewHolder {
		TextView id,note_text,note_comment;
	}
}
