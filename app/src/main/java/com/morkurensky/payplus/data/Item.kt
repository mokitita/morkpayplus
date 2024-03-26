package com.morkurensky.payplus.data

import java.text.SimpleDateFormat

data class Item(
     val id: Int,
     val price: Double,
     val created: Long,
     val entryNumber: Int,
     val totalEntryCount: Int,
     val cardType: String,
)


