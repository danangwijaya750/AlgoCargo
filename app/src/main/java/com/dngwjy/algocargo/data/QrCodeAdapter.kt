package com.dngwjy.algocargo.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dngwjy.algocargo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_data.*

class QrCodeAdapter(val data:List<QrCode>,val mass:String,val listener:(QrCode)->Unit):RecyclerView.Adapter<QrCodeAdapter.QrHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrHolder {
        return QrHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_data,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: QrHolder, position: Int) {
        holder.bindData(data[position],mass,listener)
    }

    class QrHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindData(qrData: QrCode,mass: String,listen:(QrCode)->Unit){
            qr_val.text=when(qrData.value){
                "start"-> "Memulai algoritma"
                "input" -> "masukan berat = $mass"
                "proses-seq"-> "melakukan proses perhitungan berat kapal = $mass * 60"
                "proses-it"-> "melakukan proses perhitungan berat kapal = berat kapal + $mass"
                "condition" -> "melakukan cek kondisi kapasitas kargo kapal apabila kurang dari 60" +
                        "akan mengulangi proses perhitungan dan menambah kapasitas kargo kapal"
                "end" -> "Algoritma selesai"
                else->""
            }
            itemView.setOnClickListener{listen(qrData)}
        }

    }
}