package com.company.kalasetu.ui.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Report

class ReportAdapter(
    private val list:ArrayList<Report>
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    class ViewHolder(view:View)
        : RecyclerView.ViewHolder(view){

        val txtProductId =
            view.findViewById<TextView>(
                R.id.txtProductId
            )

        val txtReason =
            view.findViewById<TextView>(
                R.id.txtReason
            )
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_report,
                    parent,
                    false
                )

        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val report = list[position]

        holder.txtProductId.text =
            "Product : ${report.productId}"

        holder.txtReason.text =
            "Reason : ${report.reason}"
    }
}