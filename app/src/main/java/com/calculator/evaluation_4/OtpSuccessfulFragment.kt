package com.calculator.evaluation_4



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.calculator.evaluation_4.databinding.FragmentOtpBinding
import com.calculator.evaluation_4.databinding.FragmentOtpSuccessfulBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtpSuccessfulFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class OtpSuccessfulFragment : Fragment() {
    private  lateinit var _binding: FragmentOtpSuccessfulBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOtpSuccessfulBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSuccess.setOnClickListener{view:View->

                view.findNavController().navigate(R.id.action_otpSuccessfulFragment_to_reFragment)
            }



        }
    override fun onDestroyView() {
        super.onDestroyView()

        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    }