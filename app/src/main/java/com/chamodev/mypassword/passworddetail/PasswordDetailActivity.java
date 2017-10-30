package com.chamodev.mypassword.passworddetail;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chamodev.mypassword.R;
import com.chamodev.mypassword.data.PasswordsRepository;
import com.chamodev.mypassword.data.PasswordsTempDataSource;
import com.chamodev.mypassword.util.ActivityUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class PasswordDetailActivity extends AppCompatActivity {
    public static final int REQUEST_ADD_PASSWORD = 1;
    public static final String EXTRA_PASSWORD_ID = "PASSWORD_ID";

    private PasswordDetailPresenter mPasswordDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = checkNotNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        String passwordId = null;

        FragmentManager fragmentManager = getSupportFragmentManager();
        PasswordDetailFragment passwordDetailFragment = (PasswordDetailFragment)
                fragmentManager.findFragmentById(R.id.password_detail_container);

        if (passwordDetailFragment == null) {
            passwordDetailFragment = PasswordDetailFragment.newInstance(passwordId);
            ActivityUtils.addFragmentToActivity(fragmentManager, passwordDetailFragment,
                    R.id.password_detail_container);
        }

        PasswordsRepository passwordsRepository = PasswordsRepository.getInstance(PasswordsTempDataSource.getInstance());
        mPasswordDetailPresenter = new PasswordDetailPresenter(passwordId, passwordsRepository,
                passwordDetailFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
