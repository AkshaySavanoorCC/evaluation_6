package com.calculator.evaluation_4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.calculator.evaluation_4.databinding.FragmentOtpBinding

class OtpFragment : Fragment() {
    private lateinit var _binding: FragmentOtpBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTexts = listOf(
            binding.editText,
            binding.otpDigit2,
            binding.editText2,
            binding.editText3,
            binding.editText4,
            binding.editText5

        )


        for (i in 0 until editTexts.size - 1) {
            val currentEditText = editTexts[i]
            val nextEditText = editTexts[i + 1]

            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        nextEditText.requestFocus()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        binding.button.setOnClickListener { view: View ->

            val allFieldsAreValid = editTexts.all { it.text.toString().isNotEmpty() && it.text.toString().isDigitsOnly() }

            if (allFieldsAreValid) {
                view.findNavController().navigate(R.id.action_otpFragment_to_otpSuccessfulFragment)
            }
        }
    }
}
