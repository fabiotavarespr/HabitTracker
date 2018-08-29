package br.com.fabiotavares.habittracker.data;

import android.provider.BaseColumns;

public class HabitContract {

    public HabitContract() {
    }

    public class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habit_tracker";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_HABIT = "habit";
        public final static String COLUMN_COMMENT = "comment";

        public final static int HABIT_STUDYING = 0;
        public final static int HABIT_PROGRAMMING = 1;
        public final static int HABIT_WORKOUT = 2;
    }
}
