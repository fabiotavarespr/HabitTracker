package br.com.fabiotavares.habittracker;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.fabiotavares.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> habitList = new ArrayList<String>();
    public static ArrayList<String> habitListName = new ArrayList<String>();
    private EditText habitNameTF;

    private Button submitBtn;
    private SQLiteDatabase myDatabase;
    private ListView habitListView;
    private ArrayAdapter<String> arrayAdapter;
    private HabitDbHelper mHelper;
    private Button deleteTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        habitListView = (ListView) findViewById(R.id.habitListView);
        habitNameTF = (EditText) findViewById(R.id.habitNameTF);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        deleteTable = (Button) findViewById(R.id.deleteTable);

        mHelper = new HabitDbHelper(this);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, habitList);
        habitListView.setAdapter(arrayAdapter);

        getFromDB();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String habitName = habitNameTF.getText().toString();
                mHelper.insert(habitName);
                getFromDB();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        deleteTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habitList.clear();
                habitListName.clear();
                mHelper.deleteHabitsDB();
                getFromDB();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHelper.update(position);
                getFromDB();
            }


        });
    }

    private void getFromDB() {
        habitList.clear();
        habitListName.clear();
        try {
            mHelper.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayAdapter.notifyDataSetChanged();
    }
}
