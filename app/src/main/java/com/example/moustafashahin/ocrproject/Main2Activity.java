package com.example.moustafashahin.ocrproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moustafashahin.ocrproject.Adapter.OCRAdapter;
import com.example.moustafashahin.ocrproject.Model.Item;
import com.example.moustafashahin.ocrproject.Model.MyResponse;
import com.example.moustafashahin.ocrproject.api.Retrofit;
import com.example.moustafashahin.ocrproject.api.StackService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
//"2.2/search/advanced?order=desc&sort=activity&title=&site=stackoverflow"
ArrayList<Item> arr = new ArrayList<>();
    ArrayList<Item> x;
    OCRAdapter adapter;
RecyclerView rv;
EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv = findViewById(R.id.rev);
        x = new ArrayList<>();
        Intent i = getIntent();
        String q = i.getStringExtra("q");
        text = findViewById(R.id.question);
        text.setText(q);
    }
    private void ser(String question) {
        Retrofit t = new Retrofit();
        StackService a = Retrofit.getClient().create(StackService.class);
        retrofit2.Call<MyResponse> r = a.Repos("desc","votes",question,"stackoverflow");
        r.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                x.clear();
                x.addAll ( response.body().getItems());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(retrofit2.Call<MyResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("hi", t.toString());
                Toast.makeText(Main2Activity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public ArrayList<Item> checked() {
        //ArrayList<Item> x = new ArrayList<>();
        return x;

    }
    public void setRv() {


        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new OCRAdapter(x);
        rv.setAdapter(adapter);

    }

    public void Search(View view) {
        ser(text.getText().toString());
        setRv();
    }
}
