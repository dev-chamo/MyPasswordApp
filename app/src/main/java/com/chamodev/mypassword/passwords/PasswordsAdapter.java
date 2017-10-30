package com.chamodev.mypassword.passwords;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chamodev.mypassword.R;
import com.chamodev.mypassword.data.Password;

import java.util.List;

/**
 * Created by Koo on 2017. 10. 30..
 */

public class PasswordsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Password> mPasswords;

    public PasswordsAdapter(Context context, List<Password> passwords) {
        mContext = context;
        mPasswords = passwords;
    }

    public void replaceData(List<Password> passwords) {
        mPasswords = passwords;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.password_item, parent, false);

        return new PasswordViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PasswordViewHolder viewHolder = (PasswordViewHolder) holder;
        viewHolder.serviceNameTv.setText(mPasswords.get(position).getServiceName());
    }

    @Override
    public int getItemCount() {
        return mPasswords == null ? 0 : mPasswords.size();
    }

    private static class PasswordViewHolder extends RecyclerView.ViewHolder {

        private TextView serviceNameTv;

        public PasswordViewHolder(View itemView) {
            super(itemView);
            serviceNameTv = (TextView) itemView.findViewById(R.id.password_item_service_name_tv);
        }
    }
}
