package com.example.mvvmexample.interfaces.main

import com.example.mvvmexample.interfaces.base.BaseViewModel

interface MainViewModel: BaseViewModel {
    fun userPressEnterButt(text: String)
}