package com.example.task.ocrproject.ui.main;

import android.util.Log;
import android.widget.Toast;

import com.example.task.ocrproject.api.Retrofit;
import com.example.task.ocrproject.api.StackService;
import com.example.task.ocrproject.model.answers.MyResponse;
import com.example.task.ocrproject.model.questions.QuestionResponse;
import com.example.task.ocrproject.ui.Main2Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionPresenter implements IQuestionPresenter {
    private IHomeView view;
    private boolean isToast = false;
    QuestionPresenter(IHomeView view) {
        this.view = view;
    }
    @Override
    public void getQuestions() {
        Retrofit t = new Retrofit();
        StackService a = Retrofit.getClient().create(StackService.class);
        retrofit2.Call<QuestionResponse> r = a.QUESTION_RESPONSE_CALL();
        r.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
              if (response.isSuccessful()){
                  view.onReceive(response.body().getItems());
              }else {
                  view.getErrorMessage(response.message(),null,true);
              }

            }

            @Override
            public void onFailure(retrofit2.Call<QuestionResponse> call, Throwable t) {
                // Log error here since request failed
                view.getErrorMessage(t.getMessage(),null,true);

            }
        });
    }
}
