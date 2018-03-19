package com.example.nhem.story.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandle {
    private MyDatabase myDatabase;

    public DatabaseHandle(Context context) {
        myDatabase = new MyDatabase(context);
    }

    private static DatabaseHandle databaseHandle;
    public static DatabaseHandle getInstance(Context context) {
        if (databaseHandle == null) {
            databaseHandle = new DatabaseHandle(context);
        }
        return databaseHandle;
    }

    private SQLiteDatabase sqLiteDatabase;

    public List<StoryModel> getListStory() {
        List<StoryModel> storyModelsList = new ArrayList<>();

        sqLiteDatabase = myDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_short_story", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String content = cursor.getString(4);
            String author = cursor.getString(5);
            boolean bookmark = cursor.getInt(6) != 0;

            StoryModel storyModel = new StoryModel(id, image, title, description, content, author, bookmark);
            storyModelsList.add(storyModel);
            cursor.moveToNext();
        }
        return storyModelsList;
    }

    public void setBookmark(StoryModel storyModel, boolean bookmark) {
        sqLiteDatabase = myDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        if (bookmark) {
            contentValues.put("bookmark", 1);
        } else {
            contentValues.put("bookmark", 0);
        }

        sqLiteDatabase.update("tbl_short_story", contentValues, "id= ?", new String[] {String.valueOf(storyModel.getId())});
    }
}
