package com.company.kalasetu.ui.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.company.kalasetu.R
import com.company.kalasetu.model.Report
import com.google.firebase.firestore.FirebaseFirestore

class AdminReportsActivity :
    AppCompatActivity() {

    private lateinit var recycler:
            RecyclerView

    private lateinit var adapter:
            ReportAdapter

    private val list =
        ArrayList<Report>()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_admin_reports
        )

        recycler =
            findViewById(R.id.recyclerReports)

        adapter =
            ReportAdapter(list)

        recycler.layoutManager =
            LinearLayoutManager(this)

        recycler.adapter = adapter

        loadReports()
    }

    private fun loadReports() {

        FirebaseFirestore.getInstance()
            .collection("Reports")
            .get()
            .addOnSuccessListener {

                list.clear()

                for(doc in it.documents){

                    val report =
                        doc.toObject(
                            Report::class.java
                        )

                    if(report != null){

                        list.add(report)
                    }
                }

                adapter.notifyDataSetChanged()
            }
    }
}