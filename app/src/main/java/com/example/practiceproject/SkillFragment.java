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

import com.example.practiceproject.services.ServiceBuilder;
import com.example.practiceproject.services.SkillIQService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillFragment extends Fragment {

    private Context mContext;

    public SkillFragment(Context context) {
        mContext = context;
    }

    public SkillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);

        SkillIQService skillIQService = ServiceBuilder.buildService(SkillIQService.class);
        Call<List<SkillIqLeaders>> skillIQRequest = skillIQService.getSkillIQ();

        skillIQRequest.enqueue(new Callback<List<SkillIqLeaders>>() {
            @Override
            public void onResponse(Call<List<SkillIqLeaders>> request, Response<List<SkillIqLeaders>> response) {

                List<SkillIqLeaders> list = response.body();
                SkillIqLeadersRecyclerAdapter skillIqLeadersRecyclerAdapter = new SkillIqLeadersRecyclerAdapter(getContext(), list);
                final RecyclerView recyclerLearners = view.findViewById(R.id.skill_iq_list);
                final LinearLayoutManager skillIqLayoutManager = new LinearLayoutManager(mContext);
                recyclerLearners.setLayoutManager(skillIqLayoutManager);
                recyclerLearners.setAdapter(skillIqLeadersRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<List<SkillIqLeaders>> request, Throwable t) {
                ((EditText) view.findViewById(R.id.message)).setText("Request Failed");
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}