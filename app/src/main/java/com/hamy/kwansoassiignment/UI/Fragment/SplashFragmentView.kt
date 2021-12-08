package com.hamy.kwansoassiignment.UI.Fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.hamy.kwansoassiignment.R
import kotlinx.android.synthetic.main.splash_view.*

class SplashFragmentView : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
        val slideAnimation = AnimationUtils.loadAnimation(requireActivity(), R.anim.side_slide)
        img_logo.startAnimation(slideAnimation)
    }
}


