package com.example.curator.ui.screens.base

import androidx.fragment.app.Fragment
import com.example.curator.utilits.APP_ACTIVITY
import com.example.curator.utilits.hideKeyboard

/* Базовый фрагмент, от него наследуются все фрагменты приложения, кроме главного */

open class BaseFragment( layout:Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
        hideKeyboard()
    }
}