package com.yuzhny.mykis.data.remote.service

import com.yuzhny.mykis.data.remote.appartment.GetAppartmentsResponse
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    companion object{
       const val SERVER_URL = "http://10.0.2.2/YkisMobileRest"
        const val BASE_URL = "$SERVER_URL/rest_api/"
        const val GET_APPARTMENTS = "getAppartmentsById.php"
        const val DELETE_APPARTMENT = "deleteAppartment.php"
        const val PARAM_ADDRESS_ID = "address_id"
        const val PARAM_TOKEN = "token"

    }

    //appartment

    @FormUrlEncoded
    @POST(GET_APPARTMENTS)
    fun getAppartments(@FieldMap params :Map<String , String> ):Call<GetAppartmentsResponse>
}
