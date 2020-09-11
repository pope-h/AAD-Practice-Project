package com.example.practiceproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceproject.services.LearningLeadersService;
import com.example.practiceproject.services.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnerFragment extends Fragment {

    private Context mContext;

    public LearnerFragment(Context context) {
        mContext = context;
    }

    public LearnerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        LearningLeadersService learningLeadersService = ServiceBuilder.buildService(LearningLeadersService.class);
        Call<List<LearningLeaders>> learnersRequest = learningLeadersService.getLearners();

        learnersRequest.enqueue(new Callback<List<LearningLeaders>>() {
            @Override
            public void onResponse(Call<List<LearningLeaders>> request, Response<List<LearningLeaders>> response) {

                List<LearningLeaders> list = response.body();
                LearningLeadersRecyclerAdapter leadersRecyclerAdapter = new LearningLeadersRecyclerAdapter(getContext(), list);
                final RecyclerView recyclerLearners = view.findViewById(R.id.learners_list);
                final LinearLayoutManager learnersLayoutManager = new LinearLayoutManager(mContext);
                recyclerLearners.setLayoutManager(learnersLayoutManager);
                recyclerLearners.setAdapter(leadersRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<LearningLeaders>> request, Throwable t) {
                ((EditText) view.findViewById(R.id.message)).setText("Request Failed");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}