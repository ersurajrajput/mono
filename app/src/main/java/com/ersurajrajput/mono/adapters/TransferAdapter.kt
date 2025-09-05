package com.ersurajrajput.mono.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ersurajrajput.mono.R
import com.ersurajrajput.mono.databinding.LayoutTransferBinding
import com.ersurajrajput.mono.models.TransferModel

class TransferAdapter(var context: Context,var tList: ArrayList<TransferModel>): RecyclerView.Adapter<TransferAdapter.TransferViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransferViewHolder {
        val binding = LayoutTransferBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransferViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransferViewHolder,
        position: Int
    ) {
        holder.tName.text = tList[position].tName

        Glide.with(context).load(tList[position].tImg).placeholder(R.drawable.ic_launcher_background).into(holder.tImg)
    }

    override fun getItemCount(): Int {
        return tList.size
    }

    class TransferViewHolder(binding: LayoutTransferBinding): RecyclerView.ViewHolder(binding.root){
        var tName = binding.transferName
        var tImg = binding.transferImg
    }
}