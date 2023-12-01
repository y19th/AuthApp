package com.example.authapp.presentation.fragment

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Suppress("PropertyName")
open class BaseFragment<T : ViewBinding> : Fragment() {

    protected var _binding: T? = null
    protected val binding get() = requireNotNull(_binding)


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}