package com.ashu.slot.repository

import com.ashu.slot.network.ApiInterface
import com.ashu.slot.network.RetrofitService

object RepositoryFactory {
    fun createSlotRepository() : SlotRepository {
        val githubApi = RetrofitService.instance.retrofit.create(ApiInterface::class.java)
        return SlotRepository(githubApi)
    }
}