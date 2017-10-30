package com.chamodev.mypassword.passwords;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chamodev.mypassword.BaseFragment;
import com.chamodev.mypassword.R;
import com.chamodev.mypassword.data.Password;
import com.chamodev.mypassword.passworddetail.PasswordDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PasswordsFragment extends BaseFragment implements PasswordsContract.View {

    private PasswordsContract.Presenter mPresenter;

    private PasswordsAdapter mPasswordsAdapter;

    private View mPasswordsView;
    private View mNoPasswordsView;

    public PasswordsFragment() {
    }

    public static PasswordsFragment newInstance() {
        return new PasswordsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPasswordsAdapter = new PasswordsAdapter(mActivity, new ArrayList<Password>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_passwords, container, false);

        RecyclerView passwordsRv = rootView.findViewById(R.id.passwords_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        passwordsRv.setLayoutManager(layoutManager);
        passwordsRv.setAdapter(mPasswordsAdapter);

        FloatingActionButton fab = (FloatingActionButton) mActivity.findViewById(R.id.add_password_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addNewPassword();
            }
        });

        mPasswordsView = rootView.findViewById(R.id.passwords_ly);
        mNoPasswordsView = rootView.findViewById(R.id.no_passwords_ly);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(PasswordsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void showPasswords(List<Password> passwords) {
        mPasswordsAdapter.replaceData(passwords);
        mPasswordsView.setVisibility(View.VISIBLE);
        mNoPasswordsView.setVisibility(View.GONE);
    }

    @Override
    public void showAddPassword() {
        Intent intent = new Intent(mActivity, PasswordDetailActivity.class);
        startActivityForResult(intent, PasswordDetailActivity.REQUEST_ADD_PASSWORD);
    }

    @Override
    public void showPasswordDetails(String passwordId) {
        Intent intent = new Intent(mActivity, PasswordDetailActivity.class);
        intent.putExtra(PasswordDetailActivity.EXTRA_PASSWORD_ID, passwordId);
        startActivity(intent);
    }

    @Override
    public void showNoPasswords() {
        mPasswordsView.setVisibility(View.GONE);
        mNoPasswordsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSaveOkMessage() {
        showMessage("비밀번호가 저장되었습니다 :)");
    }
}
