package com.example.mvvmexample.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvmexample.MainActivity
import com.example.mvvmexample.R
import com.example.mvvmexample.main.MainFragment
import com.example.mvvmexample.main.USER_TEXT_KEY
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        private val viewModel = DetailsViewModelImpl()
    }

    private val observer: Observer<State> = createObserver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_but.setOnClickListener {
            (activity as MainActivity).navigationController
                .navigate(R.id.action_detailsFragment_to_mainFragment)
        }
            arguments?.run{viewModel.setUserData(getString(USER_TEXT_KEY).orEmpty())}
            arguments = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.observe(this, observer)
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeObserver(observer)
    }

    @SuppressLint("SetTextI18n")
    private fun createObserver(): Observer<State> {
        return Observer { state ->

            when (state) {
                is State.Success -> {
                    state.run {
                        single_char.text = singleChar.toString()
                        out_data_str.text = "${getString(R.string.out_data_str)} $text"
                    }
                }
                else -> Toast.makeText(
                    activity?.applicationContext,
                    "some error",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

}