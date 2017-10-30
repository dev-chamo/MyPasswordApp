package com.chamodev.mypassword.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Koo on 2017. 10. 30..
 */

public class PasswordsRepository implements PasswordsDataSource {
    private static PasswordsRepository mInstance = null;

    private final PasswordsDataSource mPasswordsTempDataSource;

    private List<Password> mPasswords;

    public PasswordsRepository(PasswordsDataSource passwordsTempDataSource) {
        mPasswordsTempDataSource = checkNotNull(passwordsTempDataSource);
    }

    public static PasswordsRepository getInstance(PasswordsDataSource passwordsDataSource) {
        if (mInstance == null) {
            mInstance = new PasswordsRepository(passwordsDataSource);
        }

        return mInstance;
    }

    public static void destroyInstance() {
        mInstance = null;
    }

    @Override
    public void getPasswords(@NonNull final LoadPasswordsCallback callback) {
        checkNotNull(callback);

        if (mPasswords == null){
            mPasswords = new ArrayList<>();
        }

        callback.onPasswordsLoaded(mPasswords);

        /*mPasswordsTempDataSource.getPasswords(new LoadPasswordsCallback() {
            @Override
            public void onPasswordsLoaded(List<Password> passwords) {
                callback.onPasswordsLoaded(mPasswords);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });*/
    }

    @Override
    public void getPassword(@NonNull String passwordId, @NonNull GetPasswordCallback callback) {
        checkNotNull(passwordId);
        checkNotNull(callback);

    }

    @Override
    public void savePassword(@NonNull Password password) {

        checkNotNull(password);
        mPasswordsTempDataSource.savePassword(password);

        if (mPasswords == null){
            mPasswords = new ArrayList<>();
        }

        mPasswords.add(password);
    }

    @Override
    public void refreshPasswords() {

    }

    @Override
    public void deletePassword(@NonNull String passwordId) {
        checkNotNull(passwordId);
    }
}
