package com.hamy.kwansoassiignment.Utills

object Utility {
    const val DATABASE_VERSION = 1  // db version
    const val DATABASE_NAME = "notes_db" // db name

    // table items on grocery
    const val TABLE_NAME = "grocery"
    const val COLUMN_ID = "id"
    const val COLUMN_ITEMNAME = "itemName"
    const val COLUMN_TIMESTAMP = "timestamp"
    const val COLUMN_AMOUNT = "amount"
    const val COLUMN_STATUS = "status"

    // query for  grocery
    const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ITEMNAME + " TEXT,"
            + COLUMN_AMOUNT + " TEXT,"
            + COLUMN_STATUS + " TEXT,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")")

    enum class ItemStatus() {
        COMPLETETD, PENDING
    }
}