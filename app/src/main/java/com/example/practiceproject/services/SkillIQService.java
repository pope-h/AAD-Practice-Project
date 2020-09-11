package com.example.practiceproject.services;

import com.example.practiceproject.SkillIqLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQService {
    @GET("api/skilliq")
    Call<List<SkillIqLeaders>> getSkillIQ();
}
