@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.mvvmexample.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmexample.MainActivity
import com.example.mvvmexample.R
import kotlinx.android.synthetic.main.fragment_main.*

const val USER_TEXT_KEY = "com.example.mvvmexample.main.MainFragment.USER_TEXT_KEY"

private fun Bundle.putUserText(text: String) {
    putString(USER_TEXT_KEY, text)
}

class MainFragment : Fragment() {

    private val viewMode = MainViewModeImpl()
    private lateinit var observer: Observer<State>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observer = createObserver()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enter_but.setOnClickListener { viewMode.userPressEnterButt(main_input.text.toString()) }
    }

    override fun onResume() {
        super.onResume()
        viewMode.observe(this, observer)
    }

    override fun onPause() {
        super.onPause()
        viewMode.removeObserver(observer)
    }

    private fun createObserver(): Observer<State> {
        return Observer { state ->
            val activity = (activity as MainActivity)

            when (state) {
                is State.Success -> {
                    val bundle = Bundle().apply { putUserText(state.successMessage) }
                    activity.navigationController
                        .navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
                }
                is State.Error -> {
                    Toast.makeText(
                        activity,
                        state.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


}
