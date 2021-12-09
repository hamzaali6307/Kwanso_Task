package com.hamy.kwansoassiignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hamy.kwansoassiignment.model.Grocery
import com.hamy.kwansoassiignment.R
import com.hamy.kwansoassiignment.ui.adapter.ItemViewAdapter
import com.hamy.kwansoassiignment.databinding.GrocerylistFragmentViewBinding
import com.hamy.kwansoassiignment.utills.DBHelper
import kotlinx.android.synthetic.main.grocerylist_fragment_view.*

import androidx.recyclerview.widget.ItemTouchHelper
import com.hamy.kwansoassiignment.utills.CompleteSwipe
import com.hamy.kwansoassiignment.utills.PendingSwipe
import com.hamy.kwansoassiignment.utills.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class GroceryAllListFragmentView : Fragment(),
    PendingSwipe.OnPendingSwipeListener {
    lateinit var dbHelper: DBHelper
    private lateinit var binding: GrocerylistFragmentViewBinding
    private lateinit var navController: NavController
    private lateinit var itemAdapter: ItemViewAdapter
    private var itemArray = arrayListOf<Grocery>()
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
        dbHelper = DBHelper(context)
        initViews()


    }

    private fun initViews() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerview.layoutManager = layoutManager
        itemAdapter = ItemViewAdapter(itemArray)
        binding.recyclerview.adapter = itemAdapter
        viewsUpdates()
        val callback: ItemTouchHelper.Callback =
            PendingSwipe(
                0,
                ItemTouchHelper.START,
                activity,
                this
            )
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerview)

    }

    private fun viewsUpdates() {
        GlobalScope.launch(Dispatchers.Main) {
            if (dbHelper.allGrocery as ArrayList<Grocery> != null &&
                dbHelper.allGrocery.isEmpty()
            ) {
                error_view.visibility = View.VISIBLE
            } else {
                error_view.visibility = View.GONE
                itemArray = dbHelper.allGrocery as ArrayList<Grocery>
                itemAdapter.updateList(itemArray)
            }
        }
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        dbHelper.updateGrocery(itemArray[position].id, Utility.ItemStatus.PENDING.toString())
        viewsUpdates()
    }
}