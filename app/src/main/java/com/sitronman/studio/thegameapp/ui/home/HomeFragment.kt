package com.sitronman.studio.thegameapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sitronman.studio.thegameapp.databinding.FragmentHomeBinding
import com.sitronman.studio.thegameapp.ui.home.adapter.GameAdapter
import com.sitronman.studio.thegameapp.ui.home.intent.MainIntent
import com.sitronman.studio.thegameapp.ui.home.model.GameUiModel
import com.sitronman.studio.thegameapp.ui.home.model.GameViewModel
import com.sitronman.studio.thegameapp.ui.home.state.HomeState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val gameViewModel: GameViewModel by viewModel()
    private var gameAdapter = GameAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setupClicks()
        initRecyclerView()
        observeViewModel()
        return binding.root
    }

    private fun setupClicks() {
        binding.fetchGameButton.setOnClickListener {
            lifecycleScope.launch {
                gameViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = gameAdapter
        }
    }

    private fun observeViewModel() {
        binding.apply {
            lifecycleScope.launch {
                gameViewModel.state.collect {
                    when (it) {
                        is HomeState.Idle -> {
                        }
                        is HomeState.Loading -> {
                            fetchGameButton.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                        is HomeState.Games -> {
                            progressBar.visibility = View.GONE
                            fetchGameButton.visibility = View.GONE
                            renderList(it.game)
                        }
                        is HomeState.Error -> {
                            progressBar.visibility = View.GONE
                            fetchGameButton.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(), it.error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(users: List<GameUiModel>) {
        binding.recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers ->
            listOfUsers.let {
                gameAdapter.addData(it)
            }
        }
        gameAdapter.notifyDataSetChanged()
    }
}