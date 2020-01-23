package com.ashu.slot.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashu.slot.model.Slot
import com.ashu.slot.repository.SlotRepository


class SlotViewModel(private val slotRepository: SlotRepository) : ViewModel() {

    private val _githubAccount : MutableLiveData<List<Slot>> = MutableLiveData()
    val githubAccount : LiveData<List<Slot>> = _githubAccount

    fun getSlots() {
        slotRepository
            .fetchSlots()
            .subscribe {
                _githubAccount.postValue(it)
            }
    }

}