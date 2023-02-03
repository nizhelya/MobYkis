package com.yuzhny.mykis.data.remote.service

import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    companion object{
        const val SERVER_URL = "https://is.yuzhny.com/YkisMobileRest"
//        const val SERVER_URL = "http://10.0.2.2/YkisMobileRest"
//        const val SERVER_URL = "http://195.138.91.67/YkisMobileRest"
        const val BASE_URL = "$SERVER_URL/rest_api/"
        const val GET_APPARTMENTS = "getAppartmentsById.php"
        const val DELETE_APPARTMENT = "deleteAppartment.php"
    }

    //appartment

    @FormUrlEncoded
    @POST(GET_APPARTMENTS)
    suspend fun getAppartments(@FieldMap params :Map<String , Int> ):List<AppartmentEntity>
}
