package network

import data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    // 회원 가입
    @POST("project/join")
    fun requestSignup(
        // 서버에 보낼 값 정의
        @Body signupData : SignupData
    ) : Call<Signup>   // 서버에서 받아오는 값 정의

    // 로그인
    @POST("project/login")
    fun requestLogin(
        @Body loginData : LoginData
    ) : Call<Login>

    // 댓글 작성
    @POST("/postReply/")
    fun requestReply(
        @Field("replyContent") replyContent : String
    ) : Call<Reply>

    // 게시물 수정
    @POST("/postRevise/")
    fun requestRevise(
        @Field("postTitle") postTitle : String,
        @Field("postContent") postContent : String
    ) : Call<PostModel>

    // 게시물 등록
    @POST("/posting/")
    fun requestPosting(
        @Field("postTitle") postTitle : String,
        @Field("postContent") postContent : String
    ) : Call<PostModel>
}