package com.tops.kotlin.rtodrivinglicensetest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.tops.kotlin.rtodrivinglicensetest.R
import com.tops.kotlin.rtodrivinglicensetest.databases.AppDatabase
import com.tops.kotlin.rtodrivinglicensetest.databinding.FragmentPracticeBinding
import com.tops.kotlin.rtodrivinglicensetest.models.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PracticeFragment : Fragment() {

    private lateinit var binding: FragmentPracticeBinding
    private var questions: List<Question> = emptyList()
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeBinding.inflate(inflater, container, false)

        // Load questions from the database randomly
        loadQuestionsRandomly()

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

    private fun loadQuestionsRandomly() {
        val database = Room.databaseBuilder(requireContext(), AppDatabase::class.java, "pquestions")
            .createFromAsset("rtodl.db")
            .build()

        CoroutineScope(Dispatchers.IO).launch {
            val loadedRandomQuestions = database.getPQDao().getRandomQuestions()

            withContext(Dispatchers.Main) {
                if (loadedRandomQuestions.isEmpty()) {
                    Toast.makeText(requireContext(), "No Questions Found", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    questions = loadedRandomQuestions
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

        // Clear previous states
        binding.rgOptions.clearCheck()
        resetOptionColors()
        enableOptions(true)

        // Check that selected answer is right or wrong and show right answer
        binding.rgOptions.setOnCheckedChangeListener { group, checkedId ->
            val selectedOption = when (checkedId) {
                R.id.rb_option_a -> question.option1
                R.id.rb_option_b -> question.option2
                R.id.rb_option_c -> question.option3
                else -> null
            }

            if (selectedOption != null) {
                checkAnswer(selectedOption, question.answer)
            }
        }

        // Enable/Disable navigation buttons
        binding.btnPrevious.isEnabled = index > 0
        binding.btnNext.isEnabled = index < questions.size - 1
    }

    private fun checkAnswer(selectedOption: String, answer: Int) {
        enableOptions(false) // Disable further selection

        val correctAnswer = when (answer) {
            1 -> binding.rbOptionA.text.toString()
            2 -> binding.rbOptionB.text.toString()
            3 -> binding.rbOptionC.text.toString()
            else -> ""
        }

        when (selectedOption) {
            correctAnswer -> {
                // Correct answer selected
                setOptionColor(selectedOption, R.color.green)
            }

            else -> {
                // Incorrect answer selected
                setOptionColor(selectedOption, R.color.red)
                setOptionColor(correctAnswer, R.color.green) // Highlight correct answer
            }
        }
    }

    private fun setOptionColor(option: String, colorResId: Int) {
        val color = requireContext().getColor(colorResId)

        when (option) {
            binding.rbOptionA.text -> binding.rbOptionA.setTextColor(color)
            binding.rbOptionB.text -> binding.rbOptionB.setTextColor(color)
            binding.rbOptionC.text -> binding.rbOptionC.setTextColor(color)
        }
    }

    private fun resetOptionColors() {
        val defaultColor = requireContext().getColor(R.color.black)
        binding.rbOptionA.setTextColor(defaultColor)
        binding.rbOptionB.setTextColor(defaultColor)
        binding.rbOptionC.setTextColor(defaultColor)
    }

    private fun enableOptions(enable: Boolean) {
        binding.rbOptionA.isEnabled = enable
        binding.rbOptionB.isEnabled = enable
        binding.rbOptionC.isEnabled = enable
    }
}