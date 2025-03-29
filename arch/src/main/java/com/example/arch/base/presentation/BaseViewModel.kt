package com.example.arch.base.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun initialisation(savedInstanceState: Bundle?)
}