package com.morkurensky.payplus

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.morkurensky.payplus.adapter.ItemsAdapter
import com.morkurensky.payplus.data.Item
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("itemsData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>?) {
    val adapter = recyclerView.adapter as ItemsAdapter
    data?.let { data ->
        adapter.submitList(data.sortedBy { it.id })
    }
}
@BindingAdapter("roundOffDecimal")
fun roundOffDecimal(textView: TextView,number: Double) {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.UP
    textView.text= (df.format(number).toDouble()).toString()
}
@BindingAdapter("tsToDate")
fun tsToDate(textView: TextView, ts: Long)  {
    val simpleDate = SimpleDateFormat("dd/MM/yyyy")
    textView.text = simpleDate.format(ts* 1000)
}


