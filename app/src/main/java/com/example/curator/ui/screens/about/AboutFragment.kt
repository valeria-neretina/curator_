package com.example.curator.ui.screens.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.curator.R
import com.example.curator.database.getCommonModel
import com.example.curator.models.CommonModel
import com.example.curator.ui.screens.base.BaseFragment
import com.example.curator.utilits.APP_ACTIVITY
import com.example.curator.utilits.AppValueEventListener
import com.example.curator.utilits.NODE_ABOUT
import com.example.curator.utilits.REF_DATABASE_ROOT
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.about_item.*
import kotlinx.android.synthetic.main.about_item.view.*
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment: BaseFragment(R.layout.fragment_about) {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FirebaseRecyclerAdapter<CommonModel, AboutFragment.AboutHolder>

    // Холдер для захвата ViewGroup
    class AboutHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question: TextView = view.question_program
        val answer: TextView = view.about_question
    }

    private lateinit var mRefAbout: DatabaseReference
    private lateinit var mRefQuestion: DatabaseReference
    private lateinit var mRefQuestionListener: AppValueEventListener
    private var mapListeners = hashMapOf<DatabaseReference, AppValueEventListener>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "О программе"
        initRecycleView()
    }

    private fun initRecycleView() {
        mRecyclerView = about_recycle_view
        mRefAbout = REF_DATABASE_ROOT.child(NODE_ABOUT)

        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(mRefAbout, CommonModel::class.java)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<CommonModel, AboutHolder>(options) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.about_item, parent, false)
                return AboutHolder(view)
            }

            override fun onBindViewHolder(
                holder: AboutHolder,
                position: Int,
                model: CommonModel
            ) {
                mRefQuestion = REF_DATABASE_ROOT.child(NODE_ABOUT).child(model.idquestion)
                mRefQuestionListener = AppValueEventListener {
                    val contact = it.getCommonModel()
                    holder.question.text = contact.question
                    holder.answer.text = contact.answer
                }
                mRefQuestion.addValueEventListener(mRefQuestionListener)
                mapListeners[mRefQuestion] = mRefQuestionListener
            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
        println()
        mapListeners.forEach {
            it.key.removeEventListener(it.value)
        }
        println()
    }
}

