package com.openclassrooms.arista.ui.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.openclassrooms.arista.databinding.FragmentSleepBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SleepFragment : Fragment() {

    private var _binding: FragmentSleepBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SleepViewModel by viewModels()
    private val sleepAdapter = SleepAdapter(emptyList())



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        binding.sleepRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.sleepRecyclerview.adapter = sleepAdapter
       collectFkId()

    }






    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sleeps.collect { sleeps ->
                sleepAdapter.updateData(sleeps)
            }
        }
    }


  private fun collectFkId() {
      viewLifecycleOwner.lifecycleScope.launch {
          viewModel.sleepFk.collect { userId ->
              userId?.let {
                  allSleeplist(it)
              }
          }
      }
  }



private fun allSleeplist(userId: Long) {
    viewModel.fetchSleeps(userId)
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}
