package com.ersurajrajput.mono.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import com.ersurajrajput.mono.R
import com.ersurajrajput.mono.databinding.FragmentAddExpenseBinding
import com.ersurajrajput.mono.models.TransactionsModel
import com.ersurajrajput.mono.models.TransferModel

class AddExpenseFragment : Fragment() {
  private lateinit var binding: FragmentAddExpenseBinding
  private lateinit var transactionsModel: TransactionsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddExpenseBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init
        populateLabel()
        populateDate()

        binding.btnSave.setOnClickListener {
            if (!inputChacke()){
                return@setOnClickListener
            }
            saveItem()
            Log.d("myTag",transactionsModel.toString())
        }
    }

    private fun populateLabel(){
        var lableList = arrayListOf(
            "Food & Dining",
            "Transport",
            "Shopping",
            "Entertainment",
            "Bills & Utilities",
            "Health & Fitness",
            "Education",
            "Travel",
            "Rent & Housing",
            "Miscellaneous"
        )

        var adapter = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,lableList)
        binding.labelAutoTextComplete.setAdapter(adapter)

        binding.labelAutoTextComplete.setOnClickListener {
            binding.labelAutoTextComplete.showDropDown()
        }

    }
    private fun inputChacke(): Boolean{
        var name = binding.etName.text.toString().trim()
        var lable = binding.labelAutoTextComplete.text.toString().trim()
        var date = binding.etDate.text.toString().trim()
        var amount = binding.etAmount.text.toString().trim()
        if (name.isEmpty() || lable.isEmpty() || date.isEmpty() || amount.isEmpty()){
            showError("All Fields Are Required")
            return false
        }
        transactionsModel = TransactionsModel(tName = name, tLabel = lable, tDate = date, tAmount = amount.toDouble())
        return true

    }
    private fun populateDate(){
        binding.etDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val date = "${selectedDay}/${selectedMonth + 1}/${selectedYear}" // Month is 0-based
                binding.etDate.setText(date)
            }, year, month, day)

            datePicker.show()

        }

    }
    private fun saveItem(){
        if (::transactionsModel.isInitialized){
            showError(transactionsModel.toString())
        }else{
            showError("Item Not Initialized")
        }

    }
    private fun showError(error: String){
        AlertDialog.Builder(requireContext()).setTitle("Error").setMessage(error).setPositiveButton("Retry"){dialog, which ->
            dialog.dismiss()
        }.show()
    }
    companion object {

    }
}