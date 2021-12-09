package com.hamy.kwansoassiignment.utills

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

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

    fun resetTextInputErrorsOnTextChanged(vararg textInputLayouts: TextInputLayout) {
        for (inputLayout in textInputLayouts) {
            val editText = inputLayout.editText
            editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    if (inputLayout.error != null) {
                        inputLayout.error = null
                        inputLayout.isErrorEnabled = false
                    }
                }
            })
        }
    }

    fun timeStamp():String{
        val tsLong = System.currentTimeMillis() / 1000
        return tsLong.toString()
    }

}