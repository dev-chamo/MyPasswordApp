package com.chamodev.mypassword.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Koo on 2017. 10. 30..
 */

public interface PasswordsDataSource {
    interface LoadPasswordsCallback {
        void onPasswordsLoaded(List<Password> passwords);

        void onDataNotAvailable();
    }

    interface GetPasswordCallback {
        void onPasswordLoaded(Password password);

        void onDataNotAvailable();
    }

    void getPasswords(@NonNull LoadPasswordsCallback callback);

    void getPassword(@NonNull String passwordId, @NonNull GetPasswordCallback callback);

    void savePassword(@NonNull Password password);

    void refreshPasswords();

    void deletePassword(@NonNull String passwordId);

}
