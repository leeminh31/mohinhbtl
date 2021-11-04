package com.example.mohinhbtl.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mohinhbtl.User;

@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    static Migration migration_from_1_to_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column cccd text");
            database.execSQL("alter table user add column phone text");
            database.execSQL("alter table user add column date text");
        }
    };

    private static final String DATABASE_NAME="user.db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                   .allowMainThreadQueries()
                    .addMigrations(migration_from_1_to_2)
                   .build();
        }
        return instance;
    }
    public abstract UserDAO userDAO();

}
