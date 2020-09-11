package com.example.practiceproject.services;

import com.example.practiceproject.LearningLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningLeadersService {
    @GET("api/hours")
    Call<List<LearningLeaders>> getLearners();
}
