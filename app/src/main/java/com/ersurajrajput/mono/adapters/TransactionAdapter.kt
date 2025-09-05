package com.ersurajrajput.mono.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ersurajrajput.mono.R
import com.ersurajrajput.mono.databinding.LayoutTransactionBinding
import com.ersurajrajput.mono.models.TransactionsModel

class TransactionAdapter(var context: Context,var tList: ArrayList<TransactionsModel>): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
   private var selectedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        val binding = LayoutTransactionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {
        val item = tList[position]
        if (position == selectedPosition){
            holder.itemView.background.setTint(ContextCompat.getColor(context,R.color.startColor))
            holder.dSymbol.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.tSymbol.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.tAmount.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.tDate.setTextColor(ContextCompat.getColor(context,R.color.white))
            holder.tname.setTextColor(ContextCompat.getColor(context,R.color.white))
        }else{
            holder.itemView.background.setTint(ContextCompat.getColor(context,R.color.white))

            // Reset colors based on type
            if (item.tType == "c") {
                holder.tSymbol.setTextColor(ContextCompat.getColor(context, R.color.primaryColor))
                holder.dSymbol.setTextColor(ContextCompat.getColor(context, R.color.primaryColor))
                holder.tAmount.setTextColor(ContextCompat.getColor(context, R.color.primaryColor))
            } else {
                holder.tSymbol.setTextColor(ContextCompat.getColor(context, R.color.red))
                holder.dSymbol.setTextColor(ContextCompat.getColor(context, R.color.red))
                holder.tAmount.setTextColor(ContextCompat.getColor(context, R.color.red))
            }
            holder.tname.setTextColor(Color.BLACK)
            holder.tDate.setTextColor(Color.GRAY)
        }

        holder.tname.text = tList[position].tName
        holder.tAmount.text = tList[position].tAmount.toString()
        Glide.with(context).load(tList[position].tImg).placeholder(R.drawable.ic_launcher_background).into(holder.tImg)

        if (tList[position].tType == "c"){
            holder.dSymbol.setTextColor(ContextCompat.getColor(context,R.color.primaryColor))
            holder.tSymbol.setTextColor(ContextCompat.getColor(context,R.color.primaryColor))
            holder.tAmount.setTextColor(ContextCompat.getColor(context,R.color.primaryColor))

            holder.tSymbol.text = "+"
        }else if (tList[position].tType == "d"){
            holder.dSymbol.setTextColor(ContextCompat.getColor(context,R.color.red))
            holder.tSymbol.setTextColor(ContextCompat.getColor(context,R.color.red))
            holder.tAmount.setTextColor(ContextCompat.getColor(context,R.color.red))

            holder.tSymbol.text = "-"
        }


        holder.itemView.setOnClickListener {
            val previousSelected = selectedPosition
            selectedPosition = holder.adapterPosition
            if (previousSelected != -1) notifyItemChanged(previousSelected) // reset previous
            notifyItemChanged(position) // highlight current
        }


    }

    override fun getItemCount(): Int {
        return tList.size
    }

    class TransactionViewHolder(binding: LayoutTransactionBinding): RecyclerView.ViewHolder(binding.root){
        var tname = binding.tvTransactionName
        var tAmount = binding.tvTransactionAmount
        var tSymbol = binding.tSymbol
        var tImg = binding.ivTransactionImg
        var dSymbol = binding.dollarSysmbol
        var tDate = binding.tvTransactionDate
    }
}