package com.hamy.kwansoassiignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.hamy.kwansoassiignment.R
import com.hamy.kwansoassiignment.utills.Utility.resetTextInputErrorsOnTextChanged
import com.hamy.kwansoassiignment.databinding.AddGroceryViewBinding
import com.hamy.kwansoassiignment.model.Grocery
import com.hamy.kwansoassiignment.utills.DBHelper
import com.hamy.kwansoassiignment.utills.Utility
import com.hamy.kwansoassiignment.utills.Utility.timeStamp
import kotlinx.android.synthetic.main.add_grocery_view.*

class AddGroceryFragmentView : Fragment() {
    lateinit var dbHelper:DBHelper
    private lateinit var binding: AddGroceryViewBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddGroceryViewBinding.inflate(inflater, container, false)
        resetTextInputErrorsOnTextChanged(
            binding.txtInputName,
            binding.txtInputPrice
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        dbHelper = DBHelper(context)
        btn_add_grocery.setOnClickListener {
            validateView()
        }

    }

    private fun validateView() {
        val itemName = txt_input_editname.text.toString().trim()
        val itemPrice = txt_price_edit.text.toString().trim()

        if (itemName.isEmpty()) {
            txt_input_name.apply {
                isErrorEnabled = true
                error = "Enter Grocery Name"
                requestFocus()
            }
            return
        }
        if (itemPrice.isEmpty()) {
            txt_input_price.apply {
                isErrorEnabled = true
                error = "Price Never Be Empty"
                requestFocus()
            }
            return
        }
       dbHelper.insertGrocery(Grocery(
            0,itemName,itemPrice,timeStamp(), Utility.ItemStatus.PENDING.toString()))
        navController.navigate(R.id.action_addGroceryFragmentView_to_groceryListFragment)

    }
}


