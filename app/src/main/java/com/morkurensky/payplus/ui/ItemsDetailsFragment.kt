package com.morkurensky.payplus.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.morkurensky.payplus.databinding.FragmentItemDetailsBinding

class ItemsDetailsFragment : BaseFragment() {

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return FragmentItemDetailsBinding.inflate(inflater, container, false)
    }

    override fun initViews(viewBinding: ViewBinding?) {
    }
}