package com.tops.kotlin.rtodrivinglicensetest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.tops.kotlin.rtodrivinglicensetest.R
import com.tops.kotlin.rtodrivinglicensetest.databases.AppDatabase
import com.tops.kotlin.rtodrivinglicensetest.databinding.FragmentLearnBinding
import com.tops.kotlin.rtodrivinglicensetest.models.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LearnFragment : Fragment() {

    private lateinit var binding: FragmentLearnBinding
    private var questions: List<Question> = emptyList()
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnBinding.inflate(inflater, container, false)

        // Load questions from the database
        loadQuestions()

        // Navigation button click listeners
        binding.btnPrevious.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                showQuestion(currentQuestionIndex)
            }
        }

        binding.btnNext.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                showQuestion(currentQuestionIndex)
            }
        }

        return binding.root
    }

    private fun loadQuestions() {
        val database = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "pquestions")
            .createFromAsset("rtodl.db")
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            val loadedQuestions = database.getPQDao().getAllQuestions()

            withContext(Dispatchers.Main) {
                if (loadedQuestions.isEmpty()) {
                    Toast.makeText(requireContext(), "No Questions Found", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    questions = loadedQuestions
                    showQuestion(currentQuestionIndex) // Show the first question
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showQuestion(index: Int) {
        val question = questions[index]

        // Update question number
        binding.tvQuestionNumber.text = "${index + 1}/${questions.size}"

        // Set the question text
        binding.tvQuestion.text = question.question

        // Set up options according to questions
        binding.rbOptionA.text = question.option1
        binding.rbOptionB.text = question.option2
        binding.rbOptionC.text = question.option3

        // Set the right option
        when (question.answer) {
            1 -> binding.rbOptionA.isChecked = true
            2 -> binding.rbOptionB.isChecked = true
            3 -> binding.rbOptionC.isChecked = true
            else -> {
                binding.rbOptionA.isChecked = false
                binding.rbOptionB.isChecked = false
                binding.rbOptionC.isChecked = false
            }
        }

        // change the right option color and other option color remain same
        if (binding.rbOptionA.isChecked) {
            binding.rbOptionA.setTextColor(resources.getColor(R.color.green))
            binding.rbOptionB.setTextColor(resources.getColor(R.color.black))
            binding.rbOptionC.setTextColor(resources.getColor(R.color.black))
        } else if (binding.rbOptionB.isChecked) {
            binding.rbOptionA.setTextColor(resources.getColor(R.color.black))
            binding.rbOptionB.setTextColor(resources.getColor(R.color.green))
            binding.rbOptionC.setTextColor(resources.getColor(R.color.black))
        } else if (binding.rbOptionC.isChecked) {
            binding.rbOptionA.setTextColor(resources.getColor(R.color.black))
            binding.rbOptionB.setTextColor(resources.getColor(R.color.black))
            binding.rbOptionC.setTextColor(resources.getColor(R.color.green))
        }

        // Enable/Disable navigation buttons
        binding.btnPrevious.isEnabled = index > 0
        binding.btnNext.isEnabled = index < questions.size - 1
    }
}