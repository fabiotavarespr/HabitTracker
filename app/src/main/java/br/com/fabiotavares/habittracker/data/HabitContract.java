package br.com.fabiotavares.habittracker.data;

import android.provider.BaseColumns;

public class HabitContract {
    public static final String DB_NAME = "br.com.fabiotavares.habittracker.data";
    public static final int DB_VERSION = 1;

    public class HabitEntry implements BaseColumns {
        public static final String TABLE = "habits";

        public static final String COL_TASK_HABIT_NAME = "habit";
        public static final String COL_TASK_HABIT_FREQ = "freq";
    }
}
