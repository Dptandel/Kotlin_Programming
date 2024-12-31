package com.tops.kotlin.rtodrivinglicensetest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.tops.kotlin.rtodrivinglicensetest.adapters.OptionsAdapter
import com.tops.kotlin.rtodrivinglicensetest.databases.AppDatabase
import com.tops.kotlin.rtodrivinglicensetest.databinding.FragmentPracticeBinding
import com.tops.kotlin.rtodrivinglicensetest.models.PQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PracticeFragment : Fragment() {

    private lateinit var binding: FragmentPracticeBinding
    private var questions: List<PQuestion> = emptyList()
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeBinding.inflate(inflater, container, false)

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
        val database = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "questions")
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

    private fun showQuestion(index: Int) {
        val question = questions[index]

        // Update question number
        binding.tvQuestionNumber.text = "${index + 1}/${questions.size}"

        // Set the question text
        binding.tvQuestion.text = question.question

        // Set up options in RecyclerView
        val optionsAdapter = OptionsAdapter(
            listOf(question.option_1, question.option_2, question.option_3)
        )
        binding.rvOptions.adapter = optionsAdapter

        // Enable/Disable navigation buttons
        binding.btnPrevious.isEnabled = index > 0
        binding.btnNext.isEnabled = index < questions.size - 1
    }
}