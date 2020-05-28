package kr.co.yhw.apipractice_20200527.utils;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerUtil {

    private static final String BASE_URL = "http://15.165.177.142";

    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }

    public static void postRequestLogin(Context context, String email, String pw, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        POST 메쏘드 는 FormBody에 필요한 데이터를 첨부 => 여행에 짐싸는 느낌
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build();

//        API에 접근 하기위한 정보가 적혀있는 Request 변수를 만들자 => 여행 티켓을 발권하자
//        /user + POST => http://아이피주소/user + POST
        Request request = new Request.Builder()
                .url(BASE_URL + "/user")
                .post(requestBody)
//              .header()   //헤더가 필요하다면 이시점에 첨부
                .build();

//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                서버응답에 실패했을때
                Log.d("서버연결실패", "로그인기능실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                응답에 성공했을때
                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body를 JSONObject로 변환
                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public static void getRequestDuplicatedCheck(Context context, String input, String checkType, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

//        GET - 파라미터들이 주소에 적힌다.
//        요청할때 파라미터를 주소에 모두 적어줘야한다.

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/user_check").newBuilder();
        urlBuilder.addEncodedQueryParameter("type", checkType);
        urlBuilder.addEncodedQueryParameter("value",input);

        String completeUrl =  urlBuilder.build().toString();
        Log.d("완성된 Url",completeUrl);

//        비행기 티켓 발권
        Request request = new Request.Builder()
                .url(completeUrl)
//                .header()
                .get()  //get을 안써도 그리로 감
                .build();

//        먼길을 떠나달라
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                서버 응답 못했을때
//                Log.e("서버연결 실패",e.toString());
                e.printStackTrace(); //로그를 눈여겨 봐야한다.
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                  서버 응답 했을때
                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);
                    if (handler !=null){
                        handler.onResponse(json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("body",body);




            }
        });



    }
    public static void putRequestSignUp(Context context, String email, String password, String nickName, final JsonResponseHandler handler) {

//        안드로이드 앱이 클라이언트로써의 역할을 하도록 도와주는 객체.
        OkHttpClient client = new OkHttpClient();

//        PUT 메쏘드 는 FormBody에 필요한 데이터를 첨부 => 여행에 짐싸는 느낌
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("nick_name", nickName)
                .build();

//        API에 접근 하기위한 정보가 적혀있는 Request 변수를 만들자 => 여행 티켓을 발권하자
//        /user + POST => http://아이피주소/user + POST
        Request request = new Request.Builder()
                .url(BASE_URL + "/user")
                .put(requestBody)
//              .header()   //헤더가 필요하다면 이시점에 첨부
                .build();

//        client 를 이용해서 실제로 서버에 접근

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                서버응답에 실패했을때
                Log.d("서버연결실패", "로그인기능실패");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                응답에 성공했을때
                String body = response.body().string();

                Log.d("서버연결성공", body);

//                String body를 JSONObject로 변환
                try {
                    JSONObject jsonObject = new JSONObject(body);

//                    변환된 JSON을 액티비티에 전달 + 처리 실행.
                    if (handler != null) {
                        handler.onResponse(jsonObject);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}
