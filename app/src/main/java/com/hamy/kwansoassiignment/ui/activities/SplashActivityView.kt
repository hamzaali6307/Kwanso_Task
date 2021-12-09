package com.hamy.kwansoassiignment.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.hamy.kwansoassiignment.R
import kotlinx.android.synthetic.main.splash_view.*

class SplashActivityView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_view)

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        img_logo.startAnimation(slideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainViewController::class.java))
            finish()
        }, 1500)
    }

}


