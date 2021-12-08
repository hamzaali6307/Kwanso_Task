package com.hamy.kwansoassiignment.Model

import com.hamy.kwansoassiignment.Utills.Utility

data class Grocery(var itemName:String,var itemAmount:Double, var timeStamp:String,var status: Utility.ItemStatus)
