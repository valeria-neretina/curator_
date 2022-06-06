package com.example.curator.ui.settings
import com.example.curator.R
import com.example.curator.database.setBioToDatabase
import com.example.curator.ui.screens.base.BaseChangeFragment
import com.example.curator.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }
}
