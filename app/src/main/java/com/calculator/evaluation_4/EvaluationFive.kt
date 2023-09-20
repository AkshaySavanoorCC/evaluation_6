package com.calculator.evaluation_4
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.calculator.evaluation_4.databaseBuilder.OfferDatabase.Companion.getDataBaseDetails
import com.calculator.evaluation_4.OfferRepository.OfferRepository
import com.calculator.evaluation_4.databinding.ActivityEval4Binding
import com.calculator.evaluation_4.viewModel.OfferModelFactory
import com.calculator.evaluation_4.viewModel.OfferSharedViewModel

class EvaluationFive : AppCompatActivity() {
    private lateinit var binding: ActivityEval4Binding
    private lateinit var offerSharedViewModel: OfferSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_eval4)
        showProgressBar()
        initializeViewModel()
        val navController = this.findNavController(R.id.myNavHostFragment)
        offerSharedViewModel.isVerified.observe(this) { isVerified ->
            if (isVerified) {
                navController.navigate(R.id.otpSuccessfulFragment)
            } else {
                navController.navigate(R.id.otpFragment)
            }
            hideProgressBar()
        }
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    private fun initializeViewModel() {
        binding.progressBar.visibility = View.VISIBLE
        val database = getDataBaseDetails(this)
        val repository = OfferRepository(database.offerDao(), database.otpVerificationStatusDao())
        val viewModelFactory = OfferModelFactory(repository)
        offerSharedViewModel = ViewModelProvider(this, viewModelFactory)[OfferSharedViewModel::class.java]
    }


    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
