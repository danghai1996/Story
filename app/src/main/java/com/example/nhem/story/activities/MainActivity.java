package com.example.nhem.story.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nhem.story.databases.DatabaseHandle;
import com.example.nhem.story.R;
import com.example.nhem.story.adapters.StoryAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView lvStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvStory = (ListView) findViewById(R.id.lv_story);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StoryAdapter storyAdapter = new StoryAdapter(this, R.layout.item_list_story,
                DatabaseHandle.getInstance(this).getListStory());

        lvStory.setAdapter(storyAdapter);
    }
}