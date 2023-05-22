package com.example.presentation.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.presentation.PortfolioActivity
import com.example.presentation.databinding.FragmentMyPageBinding
import com.example.presentation.model.mypage.Menu
import com.example.presentation.ui.MainViewModel
import com.example.presentation.ui.analysis.OnClickMyPageListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment : Fragment(), OnClickMyPageListener {
    private var _binding: FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = _binding!!

    private val myPageViewModel: MyPageViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

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

        initBinding()
        setRecyclerView()
        getUserInfo()
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.myPageViewModel = myPageViewModel
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

    private fun getUserInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            myPageViewModel.getUserInfo(mainViewModel.userAuth)
        }
    }

    override fun onClickMyPage(item: String) {
        if (item == Menu.PORTFOLIO.text) {
            val intent = Intent(activity, PortfolioActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}