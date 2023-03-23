package com.yuzhny.mykis.data.remote.api

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.data.remote.appartment.GetAppartmentsResponse
import com.yuzhny.mykis.data.remote.family.GetFamilyResponse
import com.yuzhny.mykis.data.remote.payment.GetPaymentResponse
import com.yuzhny.mykis.data.remote.service.GetServiceResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    companion object{
//        const val SERVER_URL = "https://is.yuzhny.com/YkisMobileRest"
//        const val SERVER_URL = "http://10.0.2.2/YkisMobileRest"
          const val SERVER_URL = "http://192.168.88.243/MobYkis/YkisMobileRest"

//        const val SERVER_URL = "http://192.168.0.177/MobYkis/YkisMobileRest"

//        const val SERVER_URL = "http://192.168.0.105/MobYkis/YkisMobileRest"

        const val BASE_URL = "$SERVER_URL/rest_api/"
        const val GET_FLAT = "getFlatById.php"
        const val GET_SERVICE_FLAT = "getFlatServices.php"
        const val GET_FLAT_PAYMENT = "getFlatPayments.php"
        const val GET_MY_FLATS="getAppartmentsByUser.php"
        const val DELETE_FLAT = "deleteFlatByUser.php"
        const val UPDATE_BTI = "updateBti.php"
        const val GET_BLOCKS="getBlocks.php"
        const val GET_STREETS="getStreetsFromBlock.php"
        const val GET_HOUSES="getHousesFromStreet.php"
        const val GET_FLATS="getFlatsFromHouse.php"
        const val ADD_FLAT_BY_USER="addMyFlatByUser.php"
        const val GET_FAMILY="getFamilyFromFlat.php"
        const val CHECK_ADD_FLAT="checkAddFlat.php"
        const val PARAM_ADDRESS_ID = "address_id"
        const val PARAM_USER_ID = "user_id"
        const val STREET_ID = "street_id"
        const val HOUSE_ID = "house_id"
        const val ADDRESS_ID = "address_id"
        const val KOD = "kod"
        const val QTY = "qty"
        const val SERVICE = "service"
        const val TOTAL = "total"
        const val PHONE = "phone"
        const val EMAIL = "email"
        const val BLOCK_ID = "raion_id"
        const val PARAM_TOKEN = "token"

    }

    //appartment

    @FormUrlEncoded
    @POST(GET_MY_FLATS)
    fun getAppartmentsByUser(@FieldMap params :Map<String , String> ):Call<GetAppartmentsResponse>
    @FormUrlEncoded
    @POST(GET_BLOCKS)
    fun getBlocks(@FieldMap params :Map<String , String> ):Call<GetAddressResponse>
    @FormUrlEncoded
    @POST(GET_STREETS)
    fun getStreetsFromBlock(@FieldMap params :Map<String , String> ):Call<GetAddressResponse>
    @FormUrlEncoded
    @POST(GET_HOUSES)
    fun getHousesFromStreet(@FieldMap params :Map<String , String> ):Call<GetAddressResponse>
    @FormUrlEncoded
    @POST(GET_FLATS)
    fun getFlatsFromHouse(@FieldMap params :Map<String , String> ):Call<GetAddressResponse>
    @FormUrlEncoded
    @POST(ADD_FLAT_BY_USER)
    fun addFlatsByUser(@FieldMap params :Map<String , String> ):Call<GetSimpleResponse>
    @FormUrlEncoded
    @POST(GET_FAMILY)
    fun getFamilyFromFlat(@FieldMap params :Map<String , String> ):Call<GetFamilyResponse>
    @FormUrlEncoded
    @POST(CHECK_ADD_FLAT)
    fun checkCode(@FieldMap params :Map<String , String> ):Call<GetSimpleResponse>
    @FormUrlEncoded
    @POST(DELETE_FLAT)
    fun deleteFlatByUser(@FieldMap params :Map<String , String> ):Call<GetSimpleResponse>
    @FormUrlEncoded
    @POST(UPDATE_BTI)
    fun updateBti(@FieldMap params :Map<String , String> ):Call<GetSimpleResponse>
    @FormUrlEncoded
    @POST(GET_FLAT)
    fun getFlatById(@FieldMap params :Map<String , String> ):Call<GetAppartmentsResponse>
    @FormUrlEncoded
    @POST(GET_SERVICE_FLAT)
    fun getFlatService(@FieldMap params :Map<String , String> ):Call<GetServiceResponse>
    @FormUrlEncoded
    @POST(GET_FLAT_PAYMENT)
    fun getFlatPayment(@FieldMap params :Map<String , String> ):Call<GetPaymentResponse>
}