package com.example.authapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authapp.R
import com.example.authapp.databinding.FragmentRegistrationBinding
import com.example.authapp.domain.adapters.PaymentsAdapter
import com.example.authapp.domain.states.ProfieState
import com.example.authapp.domain.viewmodel.ProfileViewModel
import com.example.authapp.ext.navigateBack
import com.example.authapp.ext.string
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ProfileFragment: BaseFragment<FragmentRegistrationBinding>() {

    private val args: ProfileFragmentArgs by navArgs()
    private val viewModel: ProfileViewModel get() = getViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchPayments(args.token)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    attachUi(it)
                }
            }
        }
    }


    private fun attachUi(data: ProfieState) {
        binding.apply {
            loader(data.isLoading)
            logoutButton.setOnClickListener { it.navigateBack() }
            profileLoginText.text = requireContext().getString(R.string.login_text, args.login)
            paymentsRecyclerView.also {
                it.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }.adapter = PaymentsAdapter(data.payments)
        }
    }

    private fun loader(enabled: Boolean) {
        binding.apply {
            if (enabled) {
                logoutButton.isEnabled = false
                progressLoader.visibility = View.VISIBLE
                loader.isIndeterminate = true
            } else {
                logoutButton.isEnabled = true
                progressLoader.visibility = View.INVISIBLE
                loader.isIndeterminate = false
            }
        }
    }
}