package com.ashu.slot.network

import com.ashu.slot.model.Slot
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/v2/booking/slots/all/?expert_username=sakshi.sharma%40healthifyme.com&username=hme-testmnn1937%40example.com&api_key=ea2b9e93ae899eb8f63f6dcc5995ef6409bf15f3")
    fun getSlots(): Observable<List<Slot>>
}