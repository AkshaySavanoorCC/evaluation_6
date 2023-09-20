package com.calculator.evaluation_4.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.calculator.evaluation_4.R
import com.calculator.evaluation_4.databinding.FragmentOtpSuccessfulBinding

class OtpSuccessfulFragment : Fragment() {
    private lateinit var _binding: FragmentOtpSuccessfulBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpSuccessfulBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSuccess.setOnClickListener {
            navigateToReFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    private fun navigateToReFragment() {
        // Navigate to ReFragment without Safe Args
        findNavController().navigate(R.id.reFragment)
    }
}
