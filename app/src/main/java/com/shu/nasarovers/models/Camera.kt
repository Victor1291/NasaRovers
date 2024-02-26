package com.shu.nasarovers.models

import com.google.gson.annotations.SerializedName


data class Camera (

  @SerializedName("id"        ) val id       : Int,
  @SerializedName("name"      ) val name     : String,
  @SerializedName("rover_id"  ) val roverId  : Int,
  @SerializedName("full_name" ) val fullName : String

)