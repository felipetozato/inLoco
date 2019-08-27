package com.felipe.test.inloco.di

import android.app.Activity
import androidx.fragment.app.Fragment

interface DaggerComponentProvider {

    val component: InLocoAppComponent
}

val Activity.injector get() = (application as DaggerComponentProvider).component

val Fragment.injector get() = requireActivity().injector