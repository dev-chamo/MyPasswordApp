package com.chamodev.mypassword.passworddetail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chamodev.mypassword.data.Password;
import com.chamodev.mypassword.data.PasswordsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Koo on 2017. 10. 30..
 */

public class PasswordDetailPresenter implements PasswordDetailContract.Presenter,
        PasswordsDataSource.GetPasswordCallback {
    @NonNull
    private final PasswordDetailContract.View mPasswordDetailView;

    @NonNull
    private final PasswordsDataSource mPasswordsDataSource;

    @Nullable
    private String mPasswordId;


    public PasswordDetailPresenter(String passwordId, PasswordsDataSource passwordsDataSource,
                                   PasswordDetailContract.View passwordDetailView) {
        mPasswordId = passwordId;
        mPasswordsDataSource = checkNotNull(passwordsDataSource, "passwordsDataSource can't be null.");
        mPasswordDetailView = checkNotNull(passwordDetailView, "passwordDetailView can't be null.");
        mPasswordDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        openPassword();
    }

    private void openPassword() {

    }

    @Override
    public void editPassword() {

    }

    @Override
    public void deletePassword() {

    }

    @Override
    public void savePassword(String serviceName, String userName) {
        if (isNewPassword()) {
            createPassword(serviceName, userName);
        } else {
            updatePassword(serviceName, userName);
        }
    }

    private void createPassword(String serviceName, String userName) {
        Password password = new Password(serviceName, userName);
        if (password.isValid()) {
            mPasswordsDataSource.savePassword(password);
            mPasswordDetailView.showPasswordsList();
        } else {

        }
    }

    private void updatePassword(String serviceName, String userName) {

    }

    private boolean isNewPassword() {
        return mPasswordId == null;
    }

    @Override
    public void onPasswordLoaded(Password password) {

    }

    @Override
    public void onDataNotAvailable() {

    }
}
