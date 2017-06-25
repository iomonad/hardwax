package com.iomonad.hardwax.database;

/*
 * Copyright (c) 2017 iomonad <iomonad@riseup.net>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_BOOKMARKS = "bookmarks";
    public static final String COLUMN_ID = "_id";


    private static final String DATABASE_NAME = "hardwax.db";
    private static final int DATABASE_VERSION = 1;

    private static final String database_create = "create table "
            + TABLE_BOOKMARKS + "( " + COLUMN_ID + " integer primary key autoincrement, "
            + "title" + " text not null,"
            + "desc"  + " text not null,"
            + "link"  + " text not null"
            +");";

    public boolean InsertItems(HashMap<String, String> k) {
        SQLiteDatabase db = this.getWritableDatabase();
        /* Using android columns data structure */
        ContentValues values = new ContentValues();
        values.put("title", k.get("title"));
        values.put("desc", k.get("desc"));
        values.put("link", k.get("link"));
        db.insert(TABLE_BOOKMARKS, null, values); /* Insert data structure */
        db.close(); /* Clean it */
        return true;
    }

    public List<HashMap<String,String>> getAllItems() {
        List<HashMap<String,String>> result = new LinkedList<HashMap<String, String>>();

        String query = "SELECT  * FROM " + TABLE_BOOKMARKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        HashMap<String, String> pr = null;

        if (cursor.moveToFirst()) {
            do {
                pr = new HashMap<String, String>();
                pr.put("title",cursor.getString(0));
                pr.put("desc", cursor.getString(1));
                pr.put("link", cursor.getString(2));

                result.add(pr); /* Add to result */
            } while(cursor.moveToNext());
        }
        return result;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(database_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        onCreate(db);
    }
}
