package com.chamodev.mypassword.passworddetail;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chamodev.mypassword.BaseFragment;
import com.chamodev.mypassword.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class PasswordDetailFragment extends BaseFragment implements PasswordDetailContract.View {
    private static final String ARGUMENT_PASSWORD_ID = "PASSWORD_ID";

    private PasswordDetailContract.Presenter mPresenter;

    private EditText mUsernameEt;
    private EditText mServiceNameEt;
    private EditText mPasswordEt;

    public PasswordDetailFragment() {
    }

    public static PasswordDetailFragment newInstance(@Nullable String passwordId) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_PASSWORD_ID, passwordId);
        PasswordDetailFragment fragment = new PasswordDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_password_detail, container, false);

        mUsernameEt = rootView.findViewById(R.id.password_detail_username_et);
        mPasswordEt = rootView.findViewById(R.id.password_detail_password_et);
        mServiceNameEt = rootView.findViewById(R.id.password_detail_service_name_et);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

    }

    @Override
    public void setPresenter(PasswordDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.password_detail_ok) {
            String serviceName = mServiceNameEt.getText().toString();
            String userName = mUsernameEt.getText().toString();
            mPresenter.savePassword(serviceName, userName);
            return true;
        }

        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.password_detail, menu);
    }

    @Override
    public void showPasswordsList() {
        mActivity.setResult(Activity.RESULT_OK);
        mActivity.finish();
    }

    @Override
    public void showEditPassword(String passwordId) {

    }

    @Override
    public void setServiceName(String serviceName) {

    }

    @Override
    public void setUserName(String userName) {

    }
}
