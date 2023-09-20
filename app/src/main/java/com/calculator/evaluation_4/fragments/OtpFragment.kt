package com.calculator.evaluation_4.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.calculator.evaluation_4.databaseBuilder.OfferDatabase.Companion.getDataBaseDetails
import com.calculator.evaluation_4.OfferRepository.OfferRepository
import com.calculator.evaluation_4.R
import com.calculator.evaluation_4.databinding.FragmentOtpBinding
import com.calculator.evaluation_4.viewModel.OfferModelFactory
import com.calculator.evaluation_4.viewModel.OfferSharedViewModel

class OtpFragment : Fragment() {
    private lateinit var _binding: FragmentOtpBinding
    private val binding get() = _binding
    private lateinit var offerSharedViewModel: OfferSharedViewModel
    private val otpContainer = mutableListOf<EditText>()
    private lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = getDataBaseDetails(requireContext())
        val repository = OfferRepository(database.offerDao(), database.otpVerificationStatusDao())
        viewModelFactory = OfferModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupViewModel()

        binding.button.setOnClickListener { buttonView ->
            if (validateOtpFields()) {
                handleOtpVerification()
                buttonView.findNavController().navigate(R.id.action_otpFragment_to_otpSuccessfulFragment)
            }
        }
    }

    private fun initViews() {
        otpContainer.addAll(
            listOf(
                binding.editText,
                binding.editText1,
                binding.editText2,
                binding.editText3,
                binding.editText4,
                binding.editText5
            )
        )

        for (i in 0 until otpContainer.size - 1) {
            val currentEditText = otpContainer[i]
            val nextEditText = otpContainer[i + 1]

            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        nextEditText.requestFocus()
                        if (i == otpContainer.size - 2) {
                            offerSharedViewModel.hideKeyboard(nextEditText)
                        }
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    private fun setupViewModel() {
        offerSharedViewModel = ViewModelProvider(this, viewModelFactory)[OfferSharedViewModel::class.java]
    }

    private fun validateOtpFields(): Boolean {
        return otpContainer.all { it.text.toString().isNotEmpty() && it.text.toString().isDigitsOnly() }
    }

    private fun handleOtpVerification() {
        offerSharedViewModel.updateOtpStatus()
    }
}

