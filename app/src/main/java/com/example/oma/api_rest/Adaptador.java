package com.example.oma.api_rest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adaptador extends ArrayAdapter<Users> {

    Context context;
    List<Users> arratListUsers;

    public Adaptador(@NonNull Context context, List<Users> arratListUsers) {
        super(context, R.layout.my_list_item, arratListUsers);

        this.context = context;
        this.arratListUsers = arratListUsers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ay_list_item, null, true);
        TextView tvid=view.findViewById(R.id.tvid);
        TextView tvnombre=view.findViewById(R.id.tvnombre);

        tvid.setText(arratListUsers.get(position).getNombre());
        return view;
    }
}
