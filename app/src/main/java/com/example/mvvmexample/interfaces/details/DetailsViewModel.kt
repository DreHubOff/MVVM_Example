package com.example.mvvmexample.interfaces.details

import com.example.mvvmexample.interfaces.base.BaseViewModel

interface DetailsViewModel: BaseViewModel {
    fun setUserData(text: String)
}