package com.anwar.simmonitor.view.satlocation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anwar.simmonitor.R
import com.anwar.simmonitor.model.response.SatelliteLocation



class SatAdapter(private val mContext: Context, private val mData: List<SatelliteLocation.Satpositions>) : RecyclerView.Adapter<SatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.item_layout_sat, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.tvLat.text = mData[position].satlatitude.toString()+", "

        holder.tvLon.text = mData[position].satlongitude.toString()




    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLat: TextView
        val tvLon: TextView







        init {

            tvLat = itemView.findViewById(R.id.tvLat)
            tvLon = itemView.findViewById(R.id.tvLon)



        }
    }
}
