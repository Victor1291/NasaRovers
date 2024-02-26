package com.shu.nasarovers.models

import com.google.gson.annotations.SerializedName


data class Rover (

  @SerializedName("id"           ) val id          : Int,
  @SerializedName("name"         ) val name        : String,
  @SerializedName("landing_date" ) val landingDate : String,
  @SerializedName("launch_date"  ) val launchDate  : String,
  @SerializedName("status"       ) val status      : String

)