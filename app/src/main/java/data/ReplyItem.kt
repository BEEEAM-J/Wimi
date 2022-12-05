package data

data class ReplyItem(
    var comment_id : Int,
    var comment_post_id: Int,
    var comment_member_id: Int,
    var comment: String,
)