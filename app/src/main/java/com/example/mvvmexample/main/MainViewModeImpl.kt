package com.example.mvvmexample.main

import androidx.lifecycle.LiveData
import com.example.mvvmexample.interfaces.main.MainViewModel

sealed class State{
    class Error(val errorMessage: String):State()
    class Success(val successMessage: String):State()
}


class MainViewModeImpl:LiveData<State>(), MainViewModel {

    override fun userPressEnterButt(text: String) {
        if (text.isNotEmpty()){
            value = State.Success(text)
            return
        }
        value = State.Error("Enter text!!!")
    }
}