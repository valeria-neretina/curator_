package com.example.curator.ui.screens.base

import android.view.*
import androidx.fragment.app.Fragment
import com.example.curator.R
import com.example.curator.activity.MainActivity
import com.example.curator.utilits.APP_ACTIVITY
import com.example.curator.utilits.hideKeyboard

open class BaseChangeFragment (layout:Int): Fragment(layout) {


    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        APP_ACTIVITY.mAppDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        APP_ACTIVITY.menuInflater.inflate(R.menu.settings_menu_confirm, menu) }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change() }
        return true }

    open fun change() {}
}
