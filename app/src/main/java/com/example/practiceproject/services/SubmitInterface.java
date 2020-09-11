package com.example.practiceproject.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubmitInterface {
    public static final String NAME_ENTRY_ID = "entry.1877115667";
    public static final String LAST_NAME_ENTRY_ID = "entry.2006916086";
    public static final String EMAIL_ENTRY_ID = "entry.1824927963";
    public static final String GITHUB_LINK_ENTRY_ID = "entry.284483984";
    public static final String SUBMIT_POST_URL = "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    public static final String SUBMIT_API_URL = "https://docs.google.com/forms/d/e/";

    @POST(SUBMIT_POST_URL)
    @FormUrlEncoded
    Call<Void> submitProject(@Field(EMAIL_ENTRY_ID) String email, @Field(NAME_ENTRY_ID) String name,
            @Field(LAST_NAME_ENTRY_ID) String lastName, @Field(GITHUB_LINK_ENTRY_ID) String gitHubLink);
}
