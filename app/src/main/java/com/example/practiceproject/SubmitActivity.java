package com.example.practiceproject;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practiceproject.services.ServiceBuilder;
import com.example.practiceproject.services.SubmitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    Dialog mDialog;
    Button button;
    TextView dialog_text;
    ImageView dialog_cancel_image, dialog_question_image;
    private EditText mName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTransparentBackground(this);

        mName = findViewById(R.id.name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email);
        mGithub = findViewById(R.id.github);

        mDialog = new Dialog(this);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogScreen();
            }
        });
    }

    private void showDialogScreen() {
        mDialog.setContentView(R.layout.dialog_screen);
        dialog_cancel_image = (ImageView) mDialog.findViewById(R.id.dialog_cancel_image);
        dialog_text = (TextView) mDialog.findViewById(R.id.dialog_text);
        button = (Button) mDialog.findViewById(R.id.dialog_btn);

        dialog_cancel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData();
                mDialog.dismiss();
            }
        });

        mDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTransparentBackground(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void submitData() {
        if (mDialog != null) mDialog.dismiss();

        String name = mName.getText().toString();
        String lastName = mLastName.getText().toString();
        String email = mEmail.getText().toString();
        String github = mGithub.getText().toString();

        SubmitInterface submitService = ServiceBuilder.buildSubmitService(SubmitInterface.class);

        Call<Void> submitProject = submitService.submitProject(email, name, lastName, github);

        final Toast statusToast = new Toast(this);
        statusToast.setGravity(Gravity.CENTER, 0, 0);
        statusToast.setDuration(Toast.LENGTH_SHORT);

        final LayoutInflater layoutInflater = getLayoutInflater();

        submitProject.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() != 200) {
                    String TAG = "SUBMIT";
                    Log.e(TAG, "onResponse: " + response.code());
                    return;
                }

                View view = layoutInflater.inflate(R.layout.submission_successful, (ViewGroup) findViewById(R.id.parent_success), false);
                statusToast.setView(view);
                statusToast.show();
                Log.e("SUBMIT", "onResponse: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                View view = layoutInflater.inflate(R.layout.submission_not_successful, (ViewGroup) findViewById(R.id.parent_failure), false);
                statusToast.setView(view);
                statusToast.show();
            }
        });
    }
}