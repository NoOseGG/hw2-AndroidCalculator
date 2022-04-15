package com.bignerdranch.android.hm2androidcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.hm2androidcalculator.databinding.CalculatorFragmentBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class CalculatorFragment : Fragment() {

    private var _binding: CalculatorFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var result: Double = 0.0
    private val histories = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return CalculatorFragmentBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allButtonClick()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun allButtonClick() {
        with(binding) {
            buttonZero.setOnClickListener { textView.append("0") }
            buttonOne.setOnClickListener { textView.append("1") }
            buttonTwo.setOnClickListener { textView.append("2") }
            buttonThree.setOnClickListener { textView.append("3") }
            buttonFour.setOnClickListener { textView.append("4") }
            buttonFive.setOnClickListener { textView.append("5") }
            buttonSix.setOnClickListener { textView.append("6") }
            buttonSeven.setOnClickListener { textView.append("7") }
            buttonEight.setOnClickListener { textView.append("8") }
            buttonNine.setOnClickListener { textView.append("9") }
            buttonDot.setOnClickListener { textView.append(".") }

            buttonDelete.setOnClickListener { textView.text = "" }
            buttonParenthesesLeft.setOnClickListener { textView.append("(") }
            buttonParenthesesRight.setOnClickListener { textView.append(")") }

            buttonBack.setOnClickListener {
                if (textView.text.length < 2) {
                    textView.text = ""
                }

                val string = textView.text.toString()
                var result = ""

                for (index in 0..(string.length - 2)) {
                    result += string[index]
                }

                textView.text = result
            }

            buttonPlus.setOnClickListener {
                if (checkOperator(textView.text.toString())) {
                    return@setOnClickListener
                } else {
                    textView.append("+")
                }
            }

            buttonMinus.setOnClickListener {
                if (checkOperator(textView.text.toString())) {
                    return@setOnClickListener
                } else {
                    textView.append("-")
                }
            }
            buttonMulti.setOnClickListener {
                if (checkOperator(textView.text.toString())) {
                    return@setOnClickListener
                } else {
                    textView.append("*")
                }
            }
            buttonDiv.setOnClickListener {
                if (checkOperator(textView.text.toString())) {
                    return@setOnClickListener
                } else {
                    textView.append("/")
                }
            }

            buttonEquals.setOnClickListener {
                try {

                    val eb = ExpressionBuilder(textView.text.toString()).build()
                    result = eb.evaluate()

                    val resultLong = result.toLong()
                    if (result == resultLong.toDouble()) {
                        val history = "${textView.text}=$resultLong"
                        histories.add(history)
                        textView.text = resultLong.toString()
                    } else {
                        val history = "${textView.text}=$result"
                        histories.add(history)
                        textView.text = result.toString()
                    }

                } catch (e: Exception) {
                    Log.d("Error", "Message: ${e.message}")
                }
            }

            buttonHistory.setOnClickListener {
                val history = histories.toTypedArray()
                val fragment = HistoryFragment.newInstance(history = history)

                parentFragmentManager
                    .beginTransaction()
                    .add(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun checkOperator(string: String): Boolean {
        if (string.isNotBlank()) {
            val operator: Char = string[string.length - 1]
            when (operator) {
                '+', '-', '*', '/' -> return true
            }
        }
        return false
    }
}