package com.morkurensky.payplus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.morkurensky.payplus.adapter.ItemsAdapter
import com.morkurensky.payplus.databinding.FragmentItemsBinding
import com.morkurensky.payplus.viewmodel.ItemsViewModel

class ItemsFragment : BaseFragment() {

    private lateinit var binding: FragmentItemsBinding

    private val itemsViewModel: ItemsViewModel by viewModels() {
        ItemsViewModel.ItemsViewModelFactory(
            findNavController(),
            requireActivity().application
        )
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return FragmentItemsBinding.inflate(inflater, container, false)
    }

    override fun initViews(viewBinding: ViewBinding?) {
        binding = viewBinding as FragmentItemsBinding

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the CategoriesViewModel
        binding.viewmodel = itemsViewModel

        val itemsAdapter = ItemsAdapter()
        binding.rvItems.adapter = itemsAdapter

    }
}