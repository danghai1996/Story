package com.example.nhem.story.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhem.story.R;
import com.example.nhem.story.adapters.StoryAdapter;
import com.example.nhem.story.databases.DatabaseHandle;
import com.example.nhem.story.databases.StoryModel;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ImageView ivBookmark;
    private ImageView ivStory;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvDescription;
    private Button btStartReadding;
    private StoryModel storyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        setupUI();
        loadData();
    }

    private void loadData() {
        storyModel = (StoryModel) getIntent().getExtras().getSerializable(StoryAdapter.KEY_STORY);

        String[] base64Image = storyModel.getImage().split(",");
        ivStory.setImageBitmap(BitmapFactory.decodeByteArray(
                Base64.decode(base64Image[1], Base64.DEFAULT), 0, Base64.decode(base64Image[1], Base64.DEFAULT).length));

        tvTitle.setText(storyModel.getTitle());
        tvAuthor.setText(storyModel.getAuthor());
        tvDescription.setMovementMethod(new ScrollingMovementMethod());
        tvDescription.setText(storyModel.getDescription());
        if (storyModel.isBookmark()) {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
        } else {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }
    }

    private void setupUI() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBookmark = (ImageView) findViewById(R.id.iv_bookmark);
        ivStory = (ImageView) findViewById(R.id.iv_story);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        btStartReadding = (Button) findViewById(R.id.btn_start_reading);

        ivBack.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        btStartReadding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                super.onBackPressed();
                break;
            }
            case R.id.iv_bookmark: {
                setBookmark();
                break;
            }
            case R.id.btn_start_reading: {
                Intent intent = new Intent(this, ReadingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(StoryAdapter.KEY_STORY, storyModel);
                intent.putExtras(bundle);
                this.startActivity(intent);
                break;
            }
        }
    }

    private void setBookmark() {
        if (storyModel.isBookmark()) {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            DatabaseHandle.getInstance(this).setBookmark(storyModel, false);
            storyModel.setBookmark(false);
        } else {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
            DatabaseHandle.getInstance(this).setBookmark(storyModel, true);
            storyModel.setBookmark(true);
        }
    }
}
