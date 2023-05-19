package com.example.presentation.ui.analysis.datedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.presentation.databinding.FragmentDateDetailAnswerBinding
import com.example.presentation.model.analysis.Answer
import com.example.presentation.ui.analysis.OnClickAnswerListener
import kotlinx.coroutines.launch

class DateDetailAnswerFragment : Fragment(), OnClickAnswerListener {
    private var _binding: FragmentDateDetailAnswerBinding? = null
    private val binding: FragmentDateDetailAnswerBinding
        get() = _binding!!

    private val dateDetailAnswerListAdapter =
        DateDetailAnswerListAdapter(this@DateDetailAnswerFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateDetailAnswerBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = dateDetailAnswerListAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            dateDetailAnswerListAdapter.submitList(
                listOf(
                    Answer(
                        "OSI 7계층에 대해 설명하시오",
                        "osi 7계층은 7개로 이루어져있습니다",
                        "OSI (Open Systems Interconnection) 모델은 컴퓨터 네트워크 아키텍처의 개념을 나타내는 일곱 개의 계층으로 구성된 표준 모델입니다. " +
                                "각 계층은 고유한 역할을 수행하며 상위 계층에서 하위 계층으로 데이터를 전달하고, 하위 계층에서는 상위 계층으로부터 받은 데이터를 처리하여 다음 계층으로 전달합니다."
                    ),

                    Answer(
                        "Array와 LinkedList의 차이점이 무엇인가요?",
                        "array는 크기가 불변. LinkedList는 크기가 변할 수 있다.",
                        "배열은 인덱스를 사용하여 데이터에 빠르게 접근 가능하고 삽입/삭제가 느리지만, 연결리스트는 데이터의 삽입/삭제가 빠르고 메모리 사용이 유연하지만 탐색 속도가 느리다는 것이 가장 큰 차이입니다."
                    )
                )
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onClickAnswer(answer: Answer) {
        TODO("Not yet implemented")
    }
}