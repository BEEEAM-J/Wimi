package network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASEURL = "http://13.209.135.85:8080/"
    // http://10.0.2.2:8080/

    //    logger 생성
    private val logger : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    //    OkHttpClient 생성
    private val okHttp : OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(logger)
        // 네트워크 타임아웃 설정
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        //    okHttp 빌드
        .client(okHttp.build())
        .build()

    val retrofitService = retrofit.create(RetrofitService::class.java)

}