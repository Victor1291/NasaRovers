package com.shu.nasarovers.models

import com.google.gson.annotations.SerializedName


data class NasaList(

    @SerializedName("photos") val photos: List<Photos>

)