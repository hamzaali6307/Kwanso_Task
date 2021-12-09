package com.hamy.kwansoassiignment.ui.activities

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.hamy.kwansoassiignment.R
import kotlinx.android.synthetic.main.main_view.*

class MainViewController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)

        bubbleTabBar.addBubbleListener { id ->
            when(id){
                R.id.home->{
                    btn_add.visibility = View.VISIBLE
                    toolbar.title = getString(R.string.grocery_list)
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.groceryListFragment)
                }
                R.id.all_list->{
                    btn_add.visibility = View.VISIBLE
                    toolbar.title = getString(R.string.all_list)
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.groceryAllListFragmentView)
                }
            }
        }
        btn_add.setOnClickListener {
            btn_add.visibility = View.GONE
            toolbar.title = getString(R.string.create_list)
            Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.addGroceryFragmentView)
        }
    }
}