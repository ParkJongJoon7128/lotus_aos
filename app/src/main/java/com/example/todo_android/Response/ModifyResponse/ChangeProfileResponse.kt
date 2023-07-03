package com.example.todo_android.Response.ModifyResponse

import com.google.gson.annotations.SerializedName

data class ChangeProfileResponse(
    @SerializedName("resultCode")
    val resultCode: Int,
    @SerializedName("data")
    val data: CPResponse
)

// 응답값으로 data의 디테일한 값들
data class CPResponse(
    val nickname: String,
    val image: String
)
