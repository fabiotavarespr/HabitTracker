package br.com.fabiotavares.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.fabiotavares.habittracker.data.HabitContract;
import br.com.fabiotavares.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper habitDbHelper = new HabitDbHelper(this);

        habitDbHelper.insertHabit(createDate(), HabitContract.HabitEntry.HABIT_PROGRAMMING, "Android programming");
        habitDbHelper.insertHabit(createDate(), HabitContract.HabitEntry.HABIT_PROGRAMMING, "Java programming");
        habitDbHelper.insertHabit(createDate(), HabitContract.HabitEntry.HABIT_STUDYING, "Studying on udacity");
        habitDbHelper.insertHabit(createDate(), HabitContract.HabitEntry.HABIT_WORKOUT, "Swimming in the pool");

        Cursor cursor = habitDbHelper.readHabits();
        while (cursor.moveToNext()) {
            Log.v(TAG, "habit: " + cursor.getInt(0) + " " + cursor.getInt(1) +
                    " " + cursor.getInt(2) + " " + cursor.getString(3));
        }
    }

    private String createDate() {
        String formatDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return formatDate;
    }

}
