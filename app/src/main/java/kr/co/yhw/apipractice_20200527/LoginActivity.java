package kr.co.yhw.apipractice_20200527;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import org.json.JSONObject;

import kr.co.yhw.apipractice_20200527.databinding.ActivityLoginBinding;
import kr.co.yhw.apipractice_20200527.utils.ServerUtil;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login);

        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEdt.getText().toString();
                String password = binding.pwEdt.getText().toString();

                ServerUtil.postRequestLogin(mContext, email, password, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("JSON확인",json.toString());

                    }
                });
            }
        });
    }

    @Override
    public void setValues() {

    }
}
