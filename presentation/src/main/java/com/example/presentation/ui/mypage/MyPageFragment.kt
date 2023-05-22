package com.example.presentation.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMyPageBinding
import com.example.presentation.model.mypage.Menu
import com.example.presentation.ui.analysis.OnClickMyPageListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment : Fragment(), OnClickMyPageListener {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = _binding!!

    private val viewModel: MyPageViewModel by viewModels()

    private val myPageListAdapter = MyPageListAdapter(this@MyPageFragment)

    private val menuList = Menu.values().map { it.text }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = myPageListAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            myPageListAdapter.submitList(menuList)
        }
    }

    override fun onClickMyPage(item: String) {
        if (item == Menu.PORTFOLIO.text) {
            findNavController().navigate(R.id.action_myPageFragment_to_portfolioRegisterFragment)
        }
    }
}