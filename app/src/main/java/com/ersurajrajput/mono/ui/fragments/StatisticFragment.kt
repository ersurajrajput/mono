package com.ersurajrajput.mono.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.ersurajrajput.mono.R
import com.ersurajrajput.mono.adapters.TransactionAdapter
import com.ersurajrajput.mono.databinding.FragmentStatisticBinding
import com.ersurajrajput.mono.models.TransactionsModel
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class StatisticFragment : Fragment() {
    private lateinit var binding: FragmentStatisticBinding
    private lateinit var tListOriginal: ArrayList<TransactionsModel>
    private lateinit var tListFiltered: ArrayList<TransactionsModel>
    private lateinit var transactionAdapter: TransactionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatisticBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        DataSetup()
        dropDownSetup()
        filterSetup()









        // Make sure the dropdown shows when clicked




    }

    companion object {

    }
    private fun filterSetup(){
        binding.AutoCompleteView.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                var current = s.toString()
                if (current == "Expenses"){
                    tListFiltered.clear()
                    tListFiltered.addAll(tListOriginal.filter { it.tType=="d" })
                    transactionAdapter.notifyDataSetChanged()

                }else if (current=="Income"){
                    tListFiltered.clear()
                    tListFiltered.addAll(tListOriginal.filter { it.tType=="c" })
                    transactionAdapter.notifyDataSetChanged()
                }else if (current=="All"){
                    tListFiltered.clear()
                    tListFiltered.addAll(tListOriginal)
                    transactionAdapter.notifyDataSetChanged()
                }
            }

        })
    }
    private fun dropDownSetup(){
        val list = arrayListOf( "All","Expenses","Income")
        // Use simple_dropdown_item_1line, not simple_list_item_1
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_dropdown_item_1line, list
        )

        // Set adapter to AutoCompleteTextView
        binding.AutoCompleteView.setAdapter(adapter)
        binding.AutoCompleteView.setOnClickListener {
            binding.AutoCompleteView.showDropDown()
        }
    }

    private fun DataSetup(){
        //init
        tListOriginal = ArrayList()
        tListFiltered = ArrayList()

        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))

        tListFiltered.addAll(tListOriginal)

        transactionAdapter = TransactionAdapter(requireContext(),tListFiltered)
        binding.transactionsRecyclerView.adapter = transactionAdapter

    }



//    private fun showChart(context: Context){
//        val entries = ArrayList<Entry>()
////        entries.add(Entry(, 500f))
//        entries.add(Entry(1f, 800f))
//        entries.add(Entry(2f, 600f))
//        entries.add(Entry(3f, 900f))
//        entries.add(Entry(4f, 700f))
//
//        // Simple LineDataSet
//        val dataSet = LineDataSet(entries, "Earnings")
////        dataSet.color = Color.BLUE
////        dataSet.setDrawCircles(true)
////        dataSet.circleRadius = 5f
////        dataSet.setDrawValues(false)
////        dataSet.lineWidth = 2f
//
//        // Set data to chart
//        val lineData = LineData(dataSet)
//        binding.lineChart.data = lineData
//
//        // Chart customization
////        binding.lineChart.axisRight.isEnabled = false // remove right Y-axis
////        binding.lineChart.description.isEnabled = false // remove description
////        binding.lineChart.legend.isEnabled = false // remove legend
//
//        binding.lineChart.invalidate() // refresh chart
//
//    }
}