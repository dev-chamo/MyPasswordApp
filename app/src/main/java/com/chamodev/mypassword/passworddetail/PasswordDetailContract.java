package com.chamodev.mypassword.passworddetail;

import com.chamodev.mypassword.BasePresenter;
import com.chamodev.mypassword.BaseView;

/**
 * Created by Koo on 2017. 10. 30..
 */

public interface PasswordDetailContract {
    interface View extends BaseView<Presenter> {
        void showPasswordsList();

        void showEditPassword(String passwordId);

        void setServiceName(String serviceName);

        void setUserName(String userName);
    }

    interface Presenter extends BasePresenter {
        void editPassword();

        void deletePassword();

        void savePassword(String serviceName, String userName);
    }
}
