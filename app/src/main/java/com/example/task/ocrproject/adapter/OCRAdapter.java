package com.example.task.ocrproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.task.ocrproject.model.answers.Item;
import com.example.task.ocrproject.R;

import java.util.ArrayList;

public class OCRAdapter extends RecyclerView.Adapter<OCRAdapter.ViewHolder> {
    ArrayList<Item> data;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView link;
        public ViewHolder(View row) {

            super(row);
        title = row.findViewById(R.id.title);
        link = row.findViewById(R.id.link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = link.getText().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
        }
    }


    public OCRAdapter(ArrayList<Item> response) {
        data = response;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemview = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new ViewHolder(itemview);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.link.setText(data.get(position).getLink());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
