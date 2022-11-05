package network

import data.Login
import data.PostModel
import data.Reply
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    @FormUrlEncoded
    // 회원 가입
    @POST("/finishJoin/")
    fun requestSignup(
        // 서버에 보낼 값 정의
        @Field("userid") userid : String,
        @Field("userpw") userpw : String
    ) : Call<Login>   // 서버에서 받아오는 값 정의

    // 로그인
    @POST("/tryLogin/")
    fun requestLogin(
        @Field("userid") userid : String,
        @Field("userpw") userpw : String
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