package com.example.nhem.story.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhem.story.R;
import com.example.nhem.story.activities.DescriptionActivity;
import com.example.nhem.story.databases.StoryModel;

import java.util.List;

public class StoryAdapter extends ArrayAdapter<StoryModel> {
    private Context context;
    private int resource;
    private List<StoryModel> storyModelList;
    public static final String KEY_STORY = "key_story";

    public StoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<StoryModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.storyModelList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, null);

        final StoryModel storyModel = storyModelList.get(position);

        View viewBookmark = convertView.findViewById(R.id.v_bookmark);
        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvAuthor = convertView.findViewById(R.id.tv_author);
        ImageView ivStory = convertView.findViewById(R.id.iv_story);

        CardView itemStory = convertView.findViewById(R.id.item_story);
        itemStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_STORY, storyModel);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        String[] base64Image = storyModel.getImage().split(",");
        ivStory.setImageBitmap(BitmapFactory.decodeByteArray(
                Base64.decode(base64Image[1], Base64.DEFAULT), 0, Base64.decode(base64Image[1], Base64.DEFAULT).length));

        tvTitle.setText(storyModel.getTitle());
        tvAuthor.setText(storyModel.getAuthor());

        if (storyModel.isBookmark()) {
            viewBookmark.setBackgroundResource(R.color.colorPrimary);
        } else {
            viewBookmark.setBackgroundResource(R.color.colorPrimaryLight);
        }

        return convertView;
    }
}
