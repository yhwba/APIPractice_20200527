package kr.co.yhw.apipractice_20200527.utils;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerUtil {

    private static final String BASE_URL ="http://15.165.177.142";

    public interface JsonResponseHandler {
        void onResponde(JSONObject json);
    }

    public static void postRequestLogin(Context context, String email, String pw, JsonResponseHandler handler ) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드 는 FormBody에 필요한 데이터를 첨부 => 여행에 짐싸는 느낌
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password",pw)
                .build();

//        API에 접근 하기위한 정보가 적혀있는 Request 변수를 만들자 => 여행 티켓을 발권하자
//        /user + POST => http://아이피주소/user + POST
        Request request = new Request.Builder()
                .url(BASE_URL+"/user")
                .post(requestBody)
//              .header()   //헤더가 필요하다면 이시점에 첨부
                .build();

//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                서버응답에 실패했을때
                Log.d("서버연결실패","로그인기능실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                응답에 성공했을때
                String body = response.body().string();

                Log.d("서버연결성공", body);
            }
        });


    }


}
