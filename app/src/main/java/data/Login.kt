package data

// 서버에서 로그인시 받아올 응답 값
data class Login(
    var login_status : String,
    var member_id : Int,
    var member_name : String,
    var member_email : String
)


