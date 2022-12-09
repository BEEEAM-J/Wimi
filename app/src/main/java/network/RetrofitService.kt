package network

import data.*
import retrofit2.Call
import retrofit2.http.*

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

    // 게시물 등록
    @POST("project/write")
    fun requestPosting(
        @Body postData: PostData
    ) : Call<PostStatus>

    // 게시물 수정
    @PUT("project/write/modify/{post_id}")
    fun requestRevise(
        @Body postData: PostUpdateData,
        @Path("post_id", encoded = true) postId : Int
    ) : Call<ReviseStatus>

    // 게시물 삭제
    @DELETE("project/write/deletePost/{deletePostId}")
    fun requestDelete(
        @Path("deletePostId", encoded = true) deletePostID : Int
    ) : Call<DeleteStatus>

    // 전체 게시글 불러오기
    @GET("project/post/getAllPostList")
    fun requestPostList() : Call<PostModel>

    // 게시물 상세정보
    @GET("project/post/getPostDetail/{post_id}")
    fun requestDetailPost(
        @Path("post_id", encoded = true) postId: Int
    ):Call<SrhPostModelItem>

    // 게시물 검색
    @GET("project/post/search/{title}")
    fun requestSearch(
        @Path("title", encoded = true) Title : String
    ) : Call<SrhPostModel>

    // 내가 쓴 글 목록
    @GET("getMembersPostList/{member_id}")
    fun requestMyPost(
        @Path("member_id", encoded = true) memberId: Int
    ) : Call<PostModel>

    // 댓글 작성
    @POST("project/comment/write/{post_id}")
    fun requestReply(
        @Body replyData: ReplyData,
        @Path("post_id", encoded = true) postId: Int
    ) : Call<CommentPostStatus>

    // 게시물의 전체 댓글 불러오기
    @GET("project/comment/getCommentList/{post_id}")
    fun requestReplyList(
        @Path("post_id", encoded = true) postId : Int
    ) : Call<Reply>

    // 댓글 수정
    @POST("project/comment/update/{comment_id}")
    fun requestReplyRevise(
        @Body replyReviseData: ReplyReviseData,
        @Path("comment_id", encoded = true) commentId : Int
    ) : Call<ReplyUpdateStatus>



}