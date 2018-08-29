package br.com.fabiotavares.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habit_tracker.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(dropTable());
        sqLiteDatabase.execSQL(createTable());
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(dropTable());
        sqLiteDatabase.execSQL(createTable());
    }

    public void insertHabit(String date, int habit, String comment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_DATE, date);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT, habit);
        values.put(HabitContract.HabitEntry.COLUMN_COMMENT, comment);
        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor readHabits() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_DATE,
                HabitContract.HabitEntry.COLUMN_HABIT,
                HabitContract.HabitEntry.COLUMN_COMMENT
        };
        Cursor cursor = db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    private String createTable() {
        String createTable = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME +
                "(" + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitContract.HabitEntry.COLUMN_DATE + " STRING NOT NULL," +
                HabitContract.HabitEntry.COLUMN_HABIT + " INTEGER NOT NULL," +
                HabitContract.HabitEntry.COLUMN_COMMENT + " STRING NOT NULL);";
        Log.v("HabitDbHelper", "create table: " + createTable);
        return createTable;
    }

    private String dropTable() {
        String dropTable = "DROP TABLE " + HabitContract.HabitEntry.TABLE_NAME + ";";
        Log.v("HabitDbHelper", "drop table: " + dropTable);
        return dropTable;
    }
}