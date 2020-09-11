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

public class SkillIqLeadersRecyclerAdapter extends RecyclerView.Adapter<SkillIqLeadersRecyclerAdapter.ViewHolder> {
    private final Context mContext;
    private final List<SkillIqLeaders> mList;
    private final LayoutInflater mLayoutInflater;

    public SkillIqLeadersRecyclerAdapter(Context context, List<SkillIqLeaders> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.skill_iq_leaders_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillIqLeaders skillIqLeaders = mList.get(position);
        holder.mTextNames.setText(skillIqLeaders.getName());
        holder.mTextScores.setText(String.valueOf(skillIqLeaders.getScore()) + " skill IQ score, " + skillIqLeaders.getCountry());
        Picasso.with(holder.itemView.getContext()).load(skillIqLeaders.getBadgeUrl()).into(holder.mImageSkillIq);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageSkillIq;
        public final TextView mTextNames;
        public final TextView mTextScores;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageSkillIq = (ImageView) itemView.findViewById(R.id.image_skill_iq);
            mTextNames = (TextView) itemView.findViewById(R.id.text_skill_iq_name);
            mTextScores = (TextView) itemView.findViewById(R.id.text_skill_iq_score);
        }
    }
}
