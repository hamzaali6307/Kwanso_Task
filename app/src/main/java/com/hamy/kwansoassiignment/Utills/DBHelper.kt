package com.hamy.kwansoassiignment.Utills

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import com.hamy.kwansoassiignment.Model.Grocery


class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, Utility.DATABASE_NAME, null, Utility.DATABASE_VERSION) {
    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Utility.CREATE_TABLE)
    }

    // Upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Utility.TABLE_NAME)
        // Create tables again
        onCreate(db)
    }

    fun insertGrocery(grocery: String?): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Utility.COLUMN_AMOUNT, grocery)
        values.put(Utility.COLUMN_ITEMNAME, grocery)
        values.put(Utility.COLUMN_STATUS, grocery)
        val id = db.insert(Utility.TABLE_NAME, null, values)
        // close db connection
        db.close()
        // return newly inserted row id
        return id
    }

    @SuppressLint("Range")
    fun getGrocery(status: Long): Grocery {
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(
            Utility.TABLE_NAME,
            arrayOf(Utility.COLUMN_ID, Utility.COLUMN_ITEMNAME, Utility.COLUMN_STATUS,
            Utility.COLUMN_AMOUNT,Utility.COLUMN_TIMESTAMP),
            Utility.COLUMN_STATUS + "=?",
            arrayOf(status.toString()),
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()

        // prepare grocery object
        val grocery = Grocery(
            cursor!!.getInt(cursor.getColumnIndex(Utility.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(Utility.COLUMN_ITEMNAME)),
            cursor.getString(cursor.getColumnIndex(Utility.COLUMN_AMOUNT)),
            cursor.getString(cursor.getColumnIndex(Utility.COLUMN_TIMESTAMP)),
            cursor.getString(cursor.getColumnIndex(Utility.COLUMN_TIMESTAMP))
        )

        // close the db connection
        cursor.close()
        return grocery
    }

    // Select All Query
    val allGrocery:
            List<Any>
        @SuppressLint("Range")
        get() {
            val groceryList: MutableList<Grocery> = ArrayList()

            // Select All Query
            val selectQuery = "SELECT  * FROM " + Utility.TABLE_NAME + " ORDER BY " +
                    Utility.COLUMN_TIMESTAMP + " DESC"
            val db = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                   val grocery = Grocery(
                   cursor.getInt(cursor.getColumnIndex(Utility.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Utility.COLUMN_ITEMNAME)),
                    cursor.getString(cursor.getColumnIndex(Utility.COLUMN_AMOUNT)),
                    cursor.getString(cursor.getColumnIndex(Utility.COLUMN_TIMESTAMP)),
                    cursor.getString(cursor.getColumnIndex(Utility.COLUMN_STATUS)),
                    )
                    groceryList.add(grocery)
                } while (cursor.moveToNext())
            }
            // close db connection
            db.close()
            // return notes list
            return groceryList
        }

    // return count
    val groceryCount: Int
        get() {
            val countQuery = "SELECT  * FROM " + Utility.TABLE_NAME
            val db = this.readableDatabase
            val cursor: Cursor = db.rawQuery(countQuery, null)
            val count: Int = cursor.count
            cursor.close()
            // return count
            return count
        }

    fun updateGrocery(grocery: Grocery): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Utility.COLUMN_STATUS, grocery.status)

        // updating row
        return db.update(
            Utility.TABLE_NAME,
            values,
            Utility.COLUMN_STATUS + " = ?",
            arrayOf(java.lang.String.valueOf(grocery.status)
        ))
    }

    fun deleteNote(grocery: Grocery) {
        val db = this.writableDatabase
        db.delete(
            Utility.TABLE_NAME,
            Utility.COLUMN_STATUS + " = ?",
            arrayOf(java.lang.String.valueOf(grocery.status))
        )
        db.close()
    }


}