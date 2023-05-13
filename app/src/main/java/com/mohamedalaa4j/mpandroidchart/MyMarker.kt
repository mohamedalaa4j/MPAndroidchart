package com.mohamedalaa4j.mpandroidchart

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight

class MyMarker(context: Context) : MarkerView(context, R.drawable.marker_view) {

    override fun refreshContent(entry: Entry, highlight: Highlight) {
        super.refreshContent(entry, highlight)
//        rootView.tv.text = formatDate(entry.x.toLong())
//        valueView.text = formatCurrency(entry.y.toLong())
    }
}