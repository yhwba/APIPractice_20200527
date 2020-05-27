package kr.co.yhw.apipractice_20200527;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

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

                ServerUtil.postRequestLogin(mContext,"aaa@bbb.com","qwer1234",null);
            }
        });
    }

    @Override
    public void setValues() {

    }
}
