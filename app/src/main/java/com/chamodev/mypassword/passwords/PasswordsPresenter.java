package com.chamodev.mypassword.passwords;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chamodev.mypassword.data.Password;
import com.chamodev.mypassword.data.PasswordsDataSource;
import com.chamodev.mypassword.data.PasswordsRepository;
import com.chamodev.mypassword.passworddetail.PasswordDetailActivity;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Koo on 2017. 10. 30..
 */

public class PasswordsPresenter implements PasswordsContract.Presenter {
    private final PasswordsRepository mPasswordsRepository;

    private final PasswordsContract.View mPasswordsView;

    public PasswordsPresenter(PasswordsRepository passwordsRepository, PasswordsContract.View passwordsView) {
        mPasswordsRepository = checkNotNull(passwordsRepository, "passwordsRepository can't be null.");
        mPasswordsView = checkNotNull(passwordsView, "passwordsView can't be null.");

        mPasswordsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadPasswords(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        if (PasswordDetailActivity.REQUEST_ADD_PASSWORD == requestCode
                && Activity.RESULT_OK == resultCode) {
            mPasswordsView.showSaveOkMessage();
        }
    }

    @Override
    public void loadPasswords(boolean forceUpdate) {
        if (forceUpdate) {
            mPasswordsRepository.refreshPasswords();
        }

        mPasswordsRepository.getPasswords(new PasswordsDataSource.LoadPasswordsCallback() {
            @Override
            public void onPasswordsLoaded(List<Password> passwords) {
                processPasswords(passwords);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void addNewPassword() {
        mPasswordsView.showAddPassword();
    }

    @Override
    public void openPasswordDetails(@NonNull Password requestedPassword) {
        checkNotNull(requestedPassword, "requestedPassword can't be null");

        mPasswordsView.showPasswordDetails(requestedPassword.getId());
    }

    private void processPasswords(List<Password> passwords) {
        Log.d("koo", "here2222");
        if (passwords.isEmpty()) {
            mPasswordsView.showNoPasswords();
        } else {
            mPasswordsView.showPasswords(passwords);
        }
    }
}
