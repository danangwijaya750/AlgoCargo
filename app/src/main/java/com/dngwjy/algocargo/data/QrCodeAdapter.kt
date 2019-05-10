package com.dngwjy.algocargo.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dngwjy.algocargo.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_data.*

class QrCodeAdapter(val data:List<QrCode>,val listener:(QrCode)->Unit):RecyclerView.Adapter<QrCodeAdapter.QrHolder>() {
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
        holder.bindData(data[position],listener)
    }

    class QrHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindData(qrData: QrCode,listen:(QrCode)->Unit){
            qr_val.text=qrData.value
            itemView.setOnClickListener{listen(qrData)}
        }

    }
}