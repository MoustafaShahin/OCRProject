package com.example.task.ocrproject.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task.ocrproject.R;
import com.example.task.ocrproject.adapter.questionAdapter;
import com.example.task.ocrproject.databinding.FragmentHomeBinding;
import com.example.task.ocrproject.model.questions.Item;
import com.example.task.ocrproject.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IHomeView {

    FragmentHomeBinding binding;
    List<Item> Questions;
    questionAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private QuestionPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        setupUI();
        setupRecycler();
        presenter.getQuestions();
        binding.ivScan.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), MainActivity.class));
        });
    return binding.getRoot();
    }

    @Override
    public void setupUI() {
        presenter = new QuestionPresenter(this);
        Questions = new ArrayList<>();

    }

    @Override
    public void setupRecycler() {
adapter = new questionAdapter(Questions);
binding.rvques.setLayoutManager(new LinearLayoutManager(getContext()));
binding.rvques.setAdapter(adapter);


    }

    @Override
    public void getErrorMessage(String msg, Throwable t, Boolean isToast) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(List<Item> data) {
Questions.addAll(data);
adapter.notifyDataSetChanged();
    }
}