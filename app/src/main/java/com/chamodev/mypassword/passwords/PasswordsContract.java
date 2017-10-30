package com.chamodev.mypassword.passwords;

import android.support.annotation.NonNull;

import com.chamodev.mypassword.BasePresenter;
import com.chamodev.mypassword.BaseView;
import com.chamodev.mypassword.data.Password;

import java.util.List;

/**
 * Created by Koo on 2017. 10. 30..
 */

public interface PasswordsContract {
    interface View extends BaseView<Presenter> {
        void showPasswords(List<Password> passwords);

        void showAddPassword();

        void showPasswordDetails(String passwordId);

        void showNoPasswords();

        void showSaveOkMessage();
    }

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);

        void loadPasswords(boolean forceUpdate);

        void addNewPassword();

        void openPasswordDetails(@NonNull Password requestedPassword);
    }
}
