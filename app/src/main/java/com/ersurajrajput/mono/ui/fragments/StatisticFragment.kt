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
import androidx.activity.OnBackPressedCallback
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


        binding.ivBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }








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
        var img = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"
        tListOriginal.add(TransactionsModel(tName = "Salary", tAmount = 50000.0, tDay = "1", tMonth = "9", tYear = "2025", tDate = "01/09/2025", tType = "c", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Netflix", tAmount = 499.0, tDay = "2", tMonth = "9", tYear = "2025", tDate = "02/09/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Zomato", tAmount = 250.0, tDay = "2", tMonth = "9", tYear = "2025", tDate = "02/09/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Petrol", tAmount = 1200.0, tDay = "3", tMonth = "9", tYear = "2025", tDate = "03/09/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Freelance Project", tAmount = 15000.0, tDay = "5", tMonth = "9", tYear = "2025", tDate = "05/09/2025", tType = "c", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Swiggy", tAmount = 350.0, tDay = "6", tMonth = "9", tYear = "2025", tDate = "06/09/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Electricity Bill", tAmount = 1800.0, tDay = "7", tMonth = "9", tYear = "2025", tDate = "07/09/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Youtube Premium", tAmount = 129.0, tDay = "7", tMonth = "9", tYear = "2025", tDate = "07/09/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Part-time Job", tAmount = 8000.0, tDay = "8", tMonth = "9", tYear = "2025", tDate = "08/09/2025", tType = "c", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Groceries", tAmount = 2200.0, tDay = "9", tMonth = "9", tYear = "2025", tDate = "09/09/2025", tType = "d", tImg = img))

        tListOriginal.add(TransactionsModel(tName = "Amazon Shopping", tAmount = 3500.0, tDay = "10", tMonth = "8", tYear = "2025", tDate = "10/08/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Dividends", tAmount = 2000.0, tDay = "15", tMonth = "8", tYear = "2025", tDate = "15/08/2025", tType = "c", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Phone Recharge", tAmount = 249.0, tDay = "20", tMonth = "8", tYear = "2025", tDate = "20/08/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Swiggy", tAmount = 450.0, tDay = "22", tMonth = "8", tYear = "2025", tDate = "22/08/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Internet Bill", tAmount = 999.0, tDay = "25", tMonth = "8", tYear = "2025", tDate = "25/08/2025", tType = "d", tImg = img))

        tListOriginal.add(TransactionsModel(tName = "Petrol", tAmount = 1000.0, tDay = "5", tMonth = "7", tYear = "2025", tDate = "05/07/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Salary", tAmount = 48000.0, tDay = "1", tMonth = "7", tYear = "2025", tDate = "01/07/2025", tType = "c", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Zomato", tAmount = 300.0, tDay = "8", tMonth = "7", tYear = "2025", tDate = "08/07/2025", tType = "d", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Freelance", tAmount = 12000.0, tDay = "15", tMonth = "7", tYear = "2025", tDate = "15/07/2025", tType = "c", tImg = img))
        tListOriginal.add(TransactionsModel(tName = "Amazon Shopping", tAmount = 2700.0, tDay = "20", tMonth = "7", tYear = "2025", tDate = "20/07/2025", tType = "d", tImg = img))
        tListFiltered.addAll(tListOriginal)

        transactionAdapter = TransactionAdapter(requireContext(),tListFiltered)
        binding.transactionsRecyclerView.adapter = transactionAdapter

    }



}