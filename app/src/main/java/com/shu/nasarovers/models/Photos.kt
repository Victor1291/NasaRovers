package com.shu.nasarovers.models

import com.google.gson.annotations.SerializedName


data class Photos (

  @SerializedName("id"         ) val id        : Int,
  @SerializedName("sol"        ) val sol       : Int,
  @SerializedName("camera"     ) val camera    : Camera,
  @SerializedName("img_src"    ) val imgSrc    : String,
  @SerializedName("earth_date" ) val earthDate : String,
  @SerializedName("rover"      ) val rover     : Rover

)