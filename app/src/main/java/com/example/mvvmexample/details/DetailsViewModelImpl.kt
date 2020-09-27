package com.example.mvvmexample.details

import androidx.lifecycle.LiveData
import com.example.mvvmexample.interfaces.details.DetailsViewModel
import com.example.mvvmexample.interfaces.details.RequestListener

sealed class State {
    class Success(val text: String, val singleChar: Char):State()
    class Error(val errorMessage: String)
}

class DetailsViewModelImpl : DetailsViewModel, LiveData<State>(), RequestListener {

    override fun setUserData(text: String) {
        DemoRepository.userData = text
        DemoRepository.requestListener = this
    }

    override fun onRequest(text: String) {
        if (text.isNotEmpty()){
            value = State.Success(text, text[text.length - 1])
        }
    }
}