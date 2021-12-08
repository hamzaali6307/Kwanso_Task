package com.hamy.kwansoassiignment.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamy.kwansoassiignment.Model.Grocery
import com.hamy.kwansoassiignment.R
import com.hamy.kwansoassiignment.UI.Adapter.ItemViewAdapter
import com.hamy.kwansoassiignment.databinding.GrocerylistFragmentViewBinding
import com.hamy.kwansoassiignment.databinding.SplashViewBinding


class GroceryListFragmentView : Fragment() {
    private lateinit var binding: GrocerylistFragmentViewBinding
    private lateinit var navController: NavController
    lateinit var itemAdapter: ItemViewAdapter
    var itemArray = arrayListOf<Grocery>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GrocerylistFragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViews()
    }
    private fun initViews() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerview.layoutManager = layoutManager
        itemAdapter = ItemViewAdapter(itemArray)
        binding.recyclerview.adapter = itemAdapter
    }
}