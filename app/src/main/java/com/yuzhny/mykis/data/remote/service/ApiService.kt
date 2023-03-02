package com.yuzhny.mykis.data.remote.service

import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.data.remote.appartment.GetAppartmentsResponse
import com.yuzhny.mykis.data.remote.family.GetFamilyResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {
    companion object{
//        const val SERVER_URL = "https://is.yuzhny.com/YkisMobileRest"
//        const val SERVER_URL = "http://10.0.2.2/YkisMobileRest"
        const val SERVER_URL = "http://192.168.88.243/MobYkis/YkisMobileRest"
//        const val SERVER_URL = "http://192.168.0.105/MobYkis/YkisMobileRest"

        const val BASE_URL = "$SERVER_URL/rest_api/"
        const val GET_APPARTMENTS = "getAppartmentsById.php"
        const val GET_MY_FLAT="getAppartmentsByUser.php"
        const val DELETE_APPARTMENT = "deleteAppartment.php"
        const val GET_BLOCKS="getBlocks.php"
        const val GET_STREETS="getStreetsFromBlock.php"
        const val GET_HOUSES="getHousesFromStreet.php"
        const val GET_FLATS="getFlatsFromHouse.php"
        const val ADD_FLAT_BY_USER="addMyFlatByUser.php"
        const val GET_FAMILY="getFamilyFromFlat.php"
        const val PARAM_ADDRESS_ID = "address_id"
        const val PARAM_USER_ID = "user_id"
        const val STREET_ID = "street_id"
        const val HOUSE_ID = "house_id"
        const val ADDRESS_ID = "address_id"
        const val BLOCK_ID = "raion_id"
        const val PARAM_TOKEN = "token"

    }

    //appartment

    @FormUrlEncoded
    @POST(GET_MY_FLAT)
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
    fun addFlatsByUser(@FieldMap params :Map<String , String> ):Call<GetAddressResponse>
    @FormUrlEncoded
    @POST(GET_FAMILY)
    fun getFamilyFromFlat(@FieldMap params :Map<String , String> ):Call<GetFamilyResponse>
}
