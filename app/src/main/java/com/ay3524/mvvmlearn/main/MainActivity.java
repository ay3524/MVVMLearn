package com.ay3524.mvvmlearn.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ay3524.mvvmlearn.ImageRecyclerAdapter;
import com.ay3524.mvvmlearn.R;
import com.ay3524.mvvmlearn.StateData;
import com.ay3524.mvvmlearn.pojo.Wallpaper;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageRecyclerAdapter imageRecyclerAdapter;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initOtherComponents();
    }

    /**
     * This method initializes MainViewModel
     */
    private void initOtherComponents() {
        //This is standard bookish way of doing it :)
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //Below line make sures that MainRepository is initialized.
        //And yes the below line of code can be improved
        mainViewModel.init();
        //Below line just observes the data changes, after all it is reactive..!
        mainViewModel.getWallpaperList().observe(this, this::handleWallpapersList);
    }

    private void handleWallpapersList(StateData<List<Wallpaper>> listStateData) {
        switch (listStateData.getStatus()) {
            case SUCCESS:
                //When api came back with data
                progressBar.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "onChanged()", Toast.LENGTH_SHORT).show();
                updateRecyclerAdapter(listStateData.getData());
                break;
            case ERROR:
                //When api came back with error
                //Yeah the below implementation can be improved :)
                progressBar.setVisibility(View.GONE);
                if (listStateData.getError() != null) {
                    Toast.makeText(this, listStateData.getError().getMessage(), Toast.LENGTH_SHORT).show();
                    textView.setVisibility(View.VISIBLE);
                    textView.append(listStateData.getError().getMessage());
                }
                break;
            case LOADING:
                //When api is still fetching
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initUI() {
        textView = findViewById(R.id.text);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_circular);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        imageRecyclerAdapter = new ImageRecyclerAdapter();
        recyclerView.setAdapter(imageRecyclerAdapter);
    }

    private void updateRecyclerAdapter(List<Wallpaper> wallpapers) {
        recyclerView.setVisibility(View.VISIBLE);
        imageRecyclerAdapter.updateItems(wallpapers);
    }
}
