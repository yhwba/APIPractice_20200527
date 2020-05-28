package kr.co.yhw.apipractice_20200527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.yhw.apipractice_20200527.databinding.ActivitySignUpBinding;
import kr.co.yhw.apipractice_20200527.utils.ServerUtil;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;
//    응용문제
//    비번은 타이필 할때마다 길이검사
//    => 0 글자 : 비밀번호 입력해주세요
//    => 8글자 미만 :비밀번호 길이가 너무 짧습니다
//    그 이상 : 사용해도 좋은 비밀번호입니다

//     비번 확인도 타이핑할때마다 검사
//     => 0글자: 비밀번호 확인을 입력해주세요
//    => 비번과 같다 : 비밀번호 재입력이 확인 되었습니다.
//    => 다르다 : 비밀번호가 서로 다릅니다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        setupEvents();
        setValues();
    }


    @Override
    public void setupEvents() {


        binding.idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputEmail = binding.emailEdt.getText().toString();

                ServerUtil.getRequestDuplicatedCheck(mContext, inputEmail, "EMAIL", new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("중복응답확인", json.toString());

                        try {
                            int code = json.getInt("code");
                            String emailRepeatCheck
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });

                            if( code ==200){
//                                중복검사 통과
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, "사용해도 좋은 아이디입니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                            else {
//                                중복검사 에러
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, "중복검사에 통과하지 못했습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });


//        비밀번호 확인
        binding.pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkSignUpEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.pwRepeatEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkSignUpEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        응용문제 => 비번과 / 비번확인중 어느것을 타이핑해도 매번 둘다 검사.

    }
        boolean checkPasswords () {

            boolean isPwOk = false;

            String pw = binding.pwEdt.getText().toString();
            if (pw.length() == 0) {
                binding.pwCheckResultTxt.setText("비밀번호를 입력해주세요");
                binding.pwCheckResultTxt.setTextColor(Color.parseColor("#A0A0A0"));
            } else if (pw.length() < 8) {
                binding.pwCheckResultTxt.setText("비밀번호가 너무 짧습니다.");
                binding.pwCheckResultTxt.setTextColor(Color.RED);
            } else {
                binding.pwCheckResultTxt.setText("사용해도 좋은 비밀번호입니다.");
                binding.pwCheckResultTxt.setTextColor(Color.parseColor("#2767e3"));
                isPwOk = true;
            }

            boolean isPwRepeatOk = false;
            String pwRepeat = binding.pwRepeatEdt.getText().toString();
            if (pwRepeat.length() == 0) {
                binding.pwrepeatCheckResultTxt.setText("비밀번호를 입력해주세요");
                binding.pwrepeatCheckResultTxt.setTextColor(Color.parseColor("#A0A0A0"));
            } else if (pwRepeat.equals(binding.pwEdt.getText().toString())) {
                binding.pwrepeatCheckResultTxt.setText("비밀번호 재입력이 확인되었습니다");
                binding.pwrepeatCheckResultTxt.setTextColor(Color.parseColor("#2767e3"));
                isPwRepeatOk = true;
            } else {
                binding.pwrepeatCheckResultTxt.setText("비밀번호가 서로 다릅니다.");
                binding.pwrepeatCheckResultTxt.setTextColor(Color.RED);
            }

            return isPwOk && isPwRepeatOk;

        }

//    아이디중복 / 비번확인 / 닉네임 중복이 모두 통과여야, 회원가입버튼 활성화
    //    하나라도 틀리면 회원가입버튼 비활성화
    void checkSignUpEnable() {

        boolean isAllPasswordOk = checkPasswords();

        boolean isIdDuplCheckOk = true;

        binding.signUpBtn.setEnabled(isAllPasswordOk && isIdDuplCheckOk);
    }






    @Override
    public void setValues() {

    }
}
