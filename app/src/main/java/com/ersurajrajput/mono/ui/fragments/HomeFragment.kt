package com.ersurajrajput.mono.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ersurajrajput.mono.R
import com.ersurajrajput.mono.adapters.TransactionAdapter
import com.ersurajrajput.mono.adapters.TransferAdapter
import com.ersurajrajput.mono.databinding.FragmentHomeBinding
import com.ersurajrajput.mono.models.TransactionsModel
import com.ersurajrajput.mono.models.TransferModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var transactionsList: ArrayList<TransactionsModel>
    private lateinit var transfersList: ArrayList<TransferModel>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var transferAdapter: TransferAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init
        transfersList = ArrayList()
        transactionsList = ArrayList()

        // prepare data
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "d", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))
        transactionsList.add(TransactionsModel(tName = "Youtube", tAmount = 12.1, tDate = "12/04/2025", tType = "c", tImg = "https://img.favpng.com/10/23/11/youtube-portable-network-graphics-logo-image-computer-icons-png-favpng-5k5DNc5DBpxFXsqScWJ07n9iV.jpg"))


        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))
        transfersList.add(TransferModel(tName = "Suraj", tImg = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"))

        // prepare adapter
        transactionAdapter = TransactionAdapter(requireContext(),transactionsList)
        binding.transactionsRecyclerView.adapter = transactionAdapter


        transferAdapter = TransferAdapter(requireContext(),transfersList)
        binding.transfersRecyclerView.adapter = transferAdapter

    }

    companion object {

    }
}