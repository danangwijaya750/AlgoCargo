package com.dngwjy.algocargo.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dngwjy.algocargo.data.QrCode
import com.dngwjy.algocargo.data.QrCodeAdapter
import com.dngwjy.algocargo.data.QuestionGenerator
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.otaliastudios.cameraview.CameraListener
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import android.util.SparseArray
import com.google.android.gms.vision.Frame
import android.R
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class MainCameraActivity : BaseCameraActivity() {

    private var data:MutableList<QrCode> = mutableListOf()
    private lateinit var adapter: QrCodeAdapter
    override fun onClick(v: View?) {
        progressBar.visibility = View.VISIBLE
        cameraView.captureSnapshot()
    }
    var mass:String="-"
    var questType="-"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        textView3.text= intent.getStringExtra("soal")
        mass= intent.getStringExtra("mass")
        questType=intent.getStringExtra("type")
        adapter= QrCodeAdapter(data){
            val alert=AlertDialog.Builder(this)
            alert.setTitle("Konfirmasi")
            alert.setMessage("Yakin Hapus Step Tersebut?")
            alert.setPositiveButton("Ya"){dialog, which ->
                data.remove(it)
                adapter.notifyDataSetChanged()
            }
           alert.setNegativeButton("Tidak"){dialog, which ->

           }
            alert.show()
        }
        rvQrCode.layoutManager=LinearLayoutManager(this)
        rvQrCode.adapter=adapter
        cameraView.addCameraListener(object:CameraListener(){
            override fun onPictureTaken(jpeg: ByteArray?) {
                val bitmap = jpeg?.size?.let { BitmapFactory.decodeByteArray(jpeg, 0, it) }
                bitmap?.let { scanBarcode(it) }
                showPreview()
                imagePreview.setImageBitmap(bitmap)
            }
        })
        fab_view_story.setOnClickListener {
            if(data.size>0) {
                var requestUrl=""
                var count=1
                data.forEach {
                    if(count==data.size){
                        requestUrl+="${it.value}"
                    }else{
                        requestUrl+="${it.value},"
                    }
                    count++
                }
                val url:String= when(questType){
                    "x"-> "http://edukit.unyku.id/startGame.php?berat=$mass&steps=$requestUrl"
                    "y"-> "http://edukit.unyku.id/startGame.php?berat=$mass&steps=$requestUrl&loopdo"
                    "z"-> "http://edukit.unyku.id/startGame.php?berat=$mass&steps=$requestUrl&doloop"
                    else ->{""}
                }
                Log.d("URLHelper",url)
                startActivity(Intent(this,ViewStory::class.java).putExtra("url",url))
            }else{
                Toast.makeText(this,"Steps tidak boleh kosong",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun scanBarcode(bitmap: Bitmap){
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_ALL_FORMATS
            )
            .build()
        val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)


        detector.detectInImage(image)
            .addOnSuccessListener {
                adapter.notifyDataSetChanged()
                it.forEach {
                            data.add(QrCode("Generic", it.displayValue))
                }
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
            }
            .addOnFailureListener {
                progressBar.visibility = View.GONE
                Toast.makeText(baseContext, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener {
                progressBar.visibility = View.GONE
            }
    }
    private fun checkStep():Boolean{
        var step=false
        var count=0

        return  step
    }

}
