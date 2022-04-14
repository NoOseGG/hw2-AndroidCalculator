package com.bignerdranch.android.hm2androidcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.hm2androidcalculator.databinding.HistoryFragmentBinding

class HistoryFragment() : Fragment() {

    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return HistoryFragmentBinding.inflate(layoutInflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val history = arguments?.getStringArray(histories)

        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = history?.let { HistoryAdapter(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val histories = "historyArray"

        fun newInstance(history: Array<String>): HistoryFragment {
            val args = Bundle().apply {
                putStringArray(histories, history)
            }
            val fragment = HistoryFragment()
            fragment.arguments = args
            return fragment
        }
    }
}