package com.example.practiceproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningLeadersRecyclerAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final List<LearningLeaders> mList;
    private final LayoutInflater mLayoutInflater;

    public LearningLeadersRecyclerAdapter(Context context, List<LearningLeaders> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learning_leaders_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningLeaders learningLeaders = mList.get(position);
        holder.mTextNames.setText(learningLeaders.getName());
        holder.mTextHours.setText(Integer.toString(learningLeaders.getHours()) + " learning hours, " + learningLeaders.getCountry());
        Picasso.with(holder.itemView.getContext()).load(learningLeaders.getBadgeUrl()).into(holder.mImageLearners);

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mImageLearners;
        public final TextView mTextNames;
        public final TextView mTextHours;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            mImageLearners = (ImageView) itemView.findViewById(R.id.image_learners);
            mTextNames = (TextView) itemView.findViewById(R.id.text_learner_name);
            mTextHours = (TextView) itemView.findViewById(R.id.text_learner_hours);
        }
    }
}