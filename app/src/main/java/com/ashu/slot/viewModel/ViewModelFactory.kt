package com.ashu.slot.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashu.slot.repository.SlotRepository


class ViewModelFactory(private val slotRepository: SlotRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SlotViewModel(slotRepository) as T
    }
}