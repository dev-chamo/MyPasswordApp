package com.chamodev.mypassword.data;

import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Created by Koo on 2017. 10. 30..
 */

public final class Password {
    private final String mId;

    private final String mServiceName;

    private final String mUserName;

    // private final String mUnknownPassword;

    public Password(String id, String serviceName, String userName){
        mId = id;
        mServiceName = serviceName;
        mUserName = userName;
    }

    public Password(String serviceName, String userName){
        this(UUID.randomUUID().toString(), serviceName, userName);
    }

    public String getId() {
        return mId;
    }

    public String getServiceName() {
        return mServiceName;
    }

    public String getUserName() {
        return mUserName;
    }

    public boolean isValid(){
        return !Strings.isNullOrEmpty(mServiceName) && !Strings.isNullOrEmpty(mUserName);
    }

    @Override
    public String toString() {
        return String.format("Password for %s(userName=%s)", mServiceName, mUserName);
    }
}
