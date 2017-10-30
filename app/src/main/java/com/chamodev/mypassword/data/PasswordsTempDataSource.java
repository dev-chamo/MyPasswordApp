package com.chamodev.mypassword.data;

import android.support.annotation.NonNull;

/**
 * Created by Koo on 2017. 10. 30..
 */

public class PasswordsTempDataSource implements PasswordsDataSource {
    private static PasswordsTempDataSource mInstance;

    public PasswordsTempDataSource() {
    }

    public static PasswordsTempDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new PasswordsTempDataSource();
        }
        return mInstance;
    }

    @Override
    public void getPasswords(@NonNull LoadPasswordsCallback callback) {
    }

    @Override
    public void getPassword(@NonNull String passwordId, @NonNull GetPasswordCallback callback) {

    }

    @Override
    public void savePassword(@NonNull Password password) {

    }

    @Override
    public void refreshPasswords() {

    }

    @Override
    public void deletePassword(@NonNull String passwordId) {

    }
}
