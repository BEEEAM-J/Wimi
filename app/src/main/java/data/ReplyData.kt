package data

data class ReplyData(
    var comment_member_id: Int,     // 댓글 작성한 회원의 id 값
    var comment_post_id: Int,       // 댓글이 작성된 게시글의 id 값
    var comment: String             // 댓글 내용
)