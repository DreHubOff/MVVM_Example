package com.example.mvvmexample.details

import com.example.mvvmexample.interfaces.details.RequestListener
import kotlinx.coroutines.*

object DemoRepository {

    var userData: String? = null
        set(value) {
            field = value?.let {
                it.trim().capitalize()
            }
            field?.let { generateText(it) }
            println(field)
        }

    lateinit var requestListener: RequestListener

    private fun generateText(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1 .. text.length){
                withContext(Dispatchers.Main){
                    requestListener.onRequest(userData?.let { it.substring(0, i) }?:"")
                }
                delay(1000)
            }
        }
    }


}