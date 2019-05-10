package com.dngwjy.algocargo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.dngwjy.algocargo.R

class SplashActivity : AppCompatActivity() {
    private var hndlr: Handler? = null

    private val runnable:Runnable = Runnable{
        if(!isFinishing){
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        hndlr=Handler()
        hndlr!!.postDelayed(runnable,3000)
    }

    override fun onDestroy() {
        if(hndlr!=null){
            hndlr!!.removeCallbacks(runnable)
        }
        super.onDestroy()

    }
}
