package com.mohamedalaa4j.mpandroidchart

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.mohamedalaa4j.mpandroidchart.databinding.ActivityMainBinding
import com.yabu.livechart.model.DataPoint
import com.yabu.livechart.model.Dataset
import com.yabu.livechart.view.LiveChartStyle

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pieChart: PieChart
    private lateinit var lineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pieChart = binding.pieChartView

        showPieChart()
        showLiveChart()

    }

    private fun showPieChart() {

        val pieEntries = ArrayList<PieEntry>()
        val typeAmountMap = HashMap<String, Int>()
        typeAmountMap["1"] = 40
        typeAmountMap["2"] = 20
        typeAmountMap["3"] = 25
        typeAmountMap["4"] = 15

        for (type in typeAmountMap) {
            pieEntries.add(PieEntry(typeAmountMap[type.key]?.toFloat()!!, type))
        }


        val label = "type"

        val pieDataSet = PieDataSet(pieEntries, label)

        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#335138"))
        colors.add(Color.parseColor("#E9CA5D"))
        colors.add(Color.parseColor("#FF6D68"))
        colors.add(Color.parseColor("#FF0800"))

        pieDataSet.valueTextSize = 12f
        pieDataSet.colors = colors

        val pieData = PieData(pieDataSet)

        pieData.setDrawValues(true)
        pieChart.maxAngle = 180f
        pieChart.rotationAngle = 180f
        pieChart.holeRadius = 0f
        pieChart.data = pieData


        pieChart.setBackgroundColor(Color.TRANSPARENT)
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(0)

        pieChart.holeRadius = 70f
        pieChart.setDrawCenterText(true)
        pieChart.isRotationEnabled
        pieChart.setDrawEntryLabels(false)
        pieChart.data.setDrawValues(false)
        pieChart.isHighlightPerTapEnabled
        pieChart.setCenterTextOffset(0f, -20f)
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT)
        pieChart.setEntryLabelTextSize(16f)
        pieChart.transparentCircleRadius = 11f
        pieChart.isDrawHoleEnabled = true

        // Remove description labels
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false

        pieChart.invalidate()
    }

    private fun showLineChart() {
        //Part1
        val entries = ArrayList<Entry>()

//Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 20f))
        entries.add(Entry(3f, 30f))
        entries.add(Entry(4f, 40f))
        entries.add(Entry(5f, 56f))

//Part3
        val vl = LineDataSet(entries, "My Type")

//Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.gray
        vl.fillAlpha = R.color.red

//Part5
        lineChart.xAxis.labelRotationAngle = 0f

//Part6
        lineChart.data = LineData(vl)

//Part7
        lineChart.axisRight.isEnabled = false
//        lineChart.xAxis.axisMaximum = j + 0.1f
        lineChart.xAxis.axisMaximum = 0.1f

//Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

//Part9
        lineChart.description.text = "Days"
        lineChart.setNoDataText("No forex yet!")

//Part10
        lineChart.animateX(1800, Easing.EaseInExpo)

//Part11
        val markerView = CustomMarker(this, R.drawable.marker_view)
        lineChart.marker = markerView
    }

    private fun showLineChart2() {

        with(binding.chartView) {
            // (1)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false

            // (2)
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(false)
            setPinchZoom(false)
        }

        val entries = ArrayList<Entry>()

        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 20f))
        entries.add(Entry(3f, 30f))
        entries.add(Entry(4f, 40f))
        entries.add(Entry(5f, 56f))

        val dataSet = LineDataSet(entries, "Unused label")
        dataSet.color = Color.BLACK
        dataSet.valueTextColor = Color.GRAY
        dataSet.highLightColor = Color.RED
        dataSet.setDrawValues(false)
        dataSet.lineWidth = 1.5f
        dataSet.isHighlightEnabled = true
        dataSet.setDrawHighlightIndicators(false)

        binding.chartView.data = LineData(dataSet)
        binding.chartView.marker = MyMarker(this)

        binding.chartView.invalidate()

    }

    private fun showLiveChart(){
        val dataset = Dataset(
            mutableListOf(
                DataPoint(0f, 50f),
                DataPoint(1f, 50f),
                DataPoint(2f, 60f),
                DataPoint(3f, 70f),
                DataPoint(4f, 60f),

            )
        )

        val style = LiveChartStyle().apply {
            overlayLineColor = ContextCompat.getColor(this@MainActivity,R.color.red)
            textColor = Color.BLUE
            textHeight = 30f
            mainColor = ContextCompat.getColor(this@MainActivity,R.color.red)
            mainFillColor = ContextCompat.getColor(this@MainActivity,R.color.red)
//            baselineColor = Color.BLUE
            pathStrokeWidth = 10f
//            baselineStrokeWidth = 60f
            overlayCircleColor = ContextCompat.getColor(this@MainActivity,R.color.red)

        }

        binding.liveChart.setDataset(dataset)
            .setLiveChartStyle(style)
            .drawHorizontalGuidelines(steps=20)
            .drawFill(withGradient = true)
            .drawSmoothPath()
            .drawDataset()
    }

}