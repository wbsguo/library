package itangqi.me.mygreendao;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import itangqi.me.mygreendao.bean.Note;
import itangqi.me.mygreendao.db.factory.DataFactory;
import itangqi.me.mygreendao.db.util.DBOperation;

/**
 * Created by wangbs on 16/7/13.
 */
public class TestActivity extends Activity{
    public static final String TAG = "TestActivity";
    private EditText editText;
    private Button buttonAdd,buttonQuery,deleteAll,count;
    private ListView list_test;
    private NoteAdapter adapter;
    private List<Note> datas=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        editText = (EditText) findViewById(R.id.editTextNote);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonQuery = (Button) findViewById(R.id.buttonQuery);
        deleteAll = (Button) findViewById(R.id.deleteAll);
        count = (Button) findViewById(R.id.count);
        list_test = (ListView) findViewById(R.id.list_test);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNotes();
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
            }
        });
        list_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note=(Note) parent.getItemAtPosition(position);
                if(position%2==0){
                    delete(note.getId());
                }else{
                    note.setName("我们测试:"+position);
                    updataFood(note);
                }
            }
        });
        initView();
        getNotes();
    }
    private void initView(){
        adapter=new NoteAdapter(datas,this);
        list_test.setAdapter(adapter);
    }
    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");
        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = df.format(new Date());
        if(TextUtils.isEmpty(noteText)){
            Toast.makeText(this, "Please enter a note to add", Toast.LENGTH_SHORT).show();
            return;
        }
        String noteId=String.valueOf(System.currentTimeMillis());
        Note note = new Note(null,noteId, comment, new Date(),noteText);
        saveFood(note);
    }
    private void searchNotes(){
        String noteText = editText.getText().toString();
        editText.setText("");
        if(TextUtils.isEmpty(noteText)){
            Toast.makeText(this, "Please enter a note to add", Toast.LENGTH_SHORT).show();
            return;
        }
        search(noteText);
    }
    private void search(String textString){
        DataFactory.getInstance().getDbOperation(this)
                .getFoods(textString,new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        List<Note> notes =(List<Note>)object;
                        flashList(notes);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void saveFood(final Note note) {
        DataFactory.getInstance().getDbOperation(this)
                .saveFood(note, new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        getNotes();
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void updataFood(final Note note) {
        DataFactory.getInstance().getDbOperation(this)
                .updataFood(note, new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        getNotes();
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void getNotes(){
        DataFactory.getInstance().getDbOperation(this)
                .getFoods(new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        List<Note> notes =(List<Note>)object;
                        flashList(notes);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void delete(long id){
        DataFactory.getInstance().getDbOperation(this)
                .deleteFood(id,new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        getNotes();
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void deleteAll(){
        DataFactory.getInstance().getDbOperation(this)
                .deleteAll(new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        getNotes();
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void count(){
        DataFactory.getInstance().getDbOperation(this)
                .getCount(new DBOperation.CallBackListener() {
                    @Override
                    public void onComplete(Object object) {
                        long countValue=(Long)object;
                        count.setText("数量:"+countValue);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "失败");
                    }
                });
    }
    private void flashList(List<Note> notes){
        adapter.setDatas(notes);
        adapter.notifyDataSetChanged();
    }
}
