package edu.fzu.mobius.entity


import com.google.gson.annotations.SerializedName

data class TestData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("pageNum")
        val pageNum: Int,
        @SerializedName("pageSize")
        val pageSize: Int,
        @SerializedName("totalPage")
        val totalPage: Int,
        @SerializedName("total")
        val total: Int,
        @SerializedName("list")
        val list: List<Project>
    ) {
        data class Project(
            @SerializedName("id")
            val id: Int,
            @SerializedName("nickname")
            val nickname: String
        )
    }
}