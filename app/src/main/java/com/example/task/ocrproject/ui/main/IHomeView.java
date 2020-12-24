package com.example.task.ocrproject.ui.main;

import com.example.task.ocrproject.model.questions.Item;

import java.util.List;

public interface IHomeView {
    void setupUI();

    void setupRecycler();


    void getErrorMessage(String msg, Throwable t, Boolean isToast);

    void onReceive(List<Item> orders);
}
