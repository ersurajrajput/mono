package com.ersurajrajput.mono.ui.fragments

import android.content.Context
import android.graphics.Color
import android.icu.util.Calendar
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
import android.widget.Toast
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
import com.google.android.material.tabs.TabLayout
import java.time.LocalDate
import java.time.LocalTime

class StatisticFragment : Fragment() {
    private lateinit var binding: FragmentStatisticBinding
    private lateinit var tListOriginal: ArrayList<TransactionsModel>
    private lateinit var tListFiltered: ArrayList<TransactionsModel>
    private lateinit var transactionAdapter: TransactionAdapter
    private  var tType: String = "all"
    private var tTimeRange: String = "all"
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
                    tType = "d"
                    filterData()
                }else if (current=="Income"){
                    tType = "c"
                    filterData()
                }else if (current=="All"){
                    tType="all"
                    filterData()
                }
            }

        })

        binding.tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {

                tTimeRange = p0?.text.toString().lowercase()
                filterData()
                Toast.makeText(requireContext(),p0?.text.toString().lowercase(), Toast.LENGTH_SHORT).show()

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })
    }
    private fun filterData(){
        var calendar = Calendar.getInstance()
        var day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        var month = (calendar.get(Calendar.MONTH) + 1).toString()
        var year = calendar.get(Calendar.YEAR).toString()
        if (tTimeRange=="day"){
            if (tType == "c"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter { it.tDay==day && it.tMonth == month && it.tYear == year && it.tType =="c" })
                transactionAdapter.notifyDataSetChanged()
            }else if (tType == "d"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter { it.tDay==day && it.tMonth == month && it.tYear == year && it.tType=="d" })
                transactionAdapter.notifyDataSetChanged()
            }else{
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter { it.tDay==day && it.tMonth == month && it.tYear == year })
                transactionAdapter.notifyDataSetChanged()
            }


        }
        if (tTimeRange=="month"){
            if (tType == "c"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {   it.tMonth == month && it.tYear == year && it.tType =="c" })
                transactionAdapter.notifyDataSetChanged()
            }else if (tType == "d"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {  it.tMonth == month && it.tYear == year && it.tType=="d" })
                transactionAdapter.notifyDataSetChanged()
            }else{
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {  it.tMonth == month && it.tYear == year })
                transactionAdapter.notifyDataSetChanged()
            }

        }
        if (tTimeRange == "year"){
            if (tType == "c"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {  it.tYear == year && it.tType =="c" })
                transactionAdapter.notifyDataSetChanged()
            }else if (tType == "d"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {   it.tYear == year && it.tType=="d" })
                transactionAdapter.notifyDataSetChanged()
            }else{
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {   it.tYear == year })
                transactionAdapter.notifyDataSetChanged()
            }

        }
        if (tTimeRange == "all"){
            if (tType == "c"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter { it.tType =="c" })
                transactionAdapter.notifyDataSetChanged()
            }else if (tType == "d"){
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal.filter {  it.tType=="d" })
                transactionAdapter.notifyDataSetChanged()
            }else{
                tListFiltered.clear()
                tListFiltered.addAll(tListOriginal)
                transactionAdapter.notifyDataSetChanged()
            }

        }
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

        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "1", tMonth = "1", tYear = "2025", tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "1", tMonth = "1", tYear = "2025", tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "2", tMonth = "2", tYear = "2025", tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "6", tMonth = "9", tYear = "2025", tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "6", tMonth = "9", tYear = "2025", tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "1", tMonth = "9", tYear = "2025", tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "1", tMonth = "1", tYear = "2024", tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        tListOriginal.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDay = "1", tMonth = "1", tYear = "2024", tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))

        tListFiltered.addAll(tListOriginal)

        transactionAdapter = TransactionAdapter(requireContext(),tListFiltered)
        binding.transactionsRecyclerView.adapter = transactionAdapter

    }



}