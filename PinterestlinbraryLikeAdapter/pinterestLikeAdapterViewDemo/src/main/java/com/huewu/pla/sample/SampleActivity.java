package com.huewu.pla.sample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.view.Menu;

import com.huewu.pla.R;
import com.huewu.pla.lib.MultiColumnListView;

import java.util.ArrayList;

public class SampleActivity extends Activity {
	private MultiColumnListView mAdapterView = null;
	private ArrayList<String> imageUrls;
	private ImageGridAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_act);
		//mAdapterView = (PLA_AdapterView<Adapter>) findViewById(R.id.list);

		mAdapterView = (MultiColumnListView) findViewById(R.id.list);
		imageUrls = new ArrayList<String>();
		adapter = new ImageGridAdapter(this, imageUrls);
		mAdapterView.setAdapter(adapter);
		queryMediaImages();
	}


	public void queryMediaImages() {
		Cursor c = getContentResolver().query( Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null );
		if ( c != null ) {
		    if (c.moveToFirst()) {            
		           do {         
						long id = c.getLong( c.getColumnIndex( Images.Media._ID ) );
						Uri imageUri = Uri.parse( Images.Media.EXTERNAL_CONTENT_URI + "/" + id );  	 
						imageUrls.add(imageUri.toString());
						//imageUrls.add(getRealFilePath(MainActivity.this, imageUri));
		          } while (c.moveToNext());         
		       }
		}	
		c.close();
		c = null;
		adapter.notifyDataSetChanged();
	}

}//end of class
