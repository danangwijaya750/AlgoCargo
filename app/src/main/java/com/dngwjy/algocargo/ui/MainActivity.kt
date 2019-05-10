package com.dngwjy.algocargo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.dngwjy.algocargo.R
import com.dngwjy.algocargo.data.QuestionGenerator

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
var generated=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        supportActionBar?.hide()
        generated=false
        var mass=""
        var typeQuestion=""
        fab.setOnClickListener { view ->
            when(!generated){
                true->{
                    Snackbar.make(view, "Sedang generate Soal", Snackbar.LENGTH_LONG)
                        .setAction("Generate", null).show()
                    val data =  "Soal : \n${QuestionGenerator.generate()}"
                    mass=data.substring(data.length-1,data.length)
                    typeQuestion=data.substring(data.length-2,data.length-1)
                    textView.text=data.removeRange(data.length-2,data.length)
                    Log.d("mass",mass)
                    Log.d("mass",typeQuestion)
                    generated=true
                }
                false->{
                    val builder=AlertDialog.Builder(this)
                    builder.setTitle("Konfirmasi")
                    builder.setMessage("Kerjakan Soal atau Generate Ulang?")
                    builder.setNegativeButton("Re-generate"){dialog, which ->
                        generated=false
                        fab.callOnClick()
                    }
                    builder.setPositiveButton("Kerjakan"){dialog, which ->
                        val intent=Intent(this,MainCameraActivity::class.java)
                        intent.putExtra("soal",textView.text.toString())
                        intent.putExtra("mass",mass.toString())
                        intent.putExtra("type",typeQuestion)
                        startActivity(intent)
                        finish()
                    }
                    builder.show()
                }
            }


        }
    }

}
