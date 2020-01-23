package com.ashu.slot.repository

import com.ashu.slot.model.Slot
import com.ashu.slot.network.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SlotRepository(private val apiInterface: ApiInterface) {

    fun fetchSlots(): Observable<List<Slot>> {
        return Observable.create { emitter ->
            apiInterface.getSlots()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        emitter.onNext(it)
                    }
                }, {
                    it.printStackTrace()
                })

        }
    }

}


