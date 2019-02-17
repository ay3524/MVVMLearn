package com.ay3524.mvvmlearn.main;

import com.ay3524.mvvmlearn.StateLiveData;
import com.ay3524.mvvmlearn.pojo.Wallpaper;

import java.util.List;

import androidx.lifecycle.ViewModel;

//Important : keep this MainViewModel as public
public class MainViewModel extends ViewModel {

    /**
     * StateLiveData is the data received from the server
     */
    private StateLiveData<List<Wallpaper>> wallpaperList;

    private MainRepository mainRepository;

    public void init() {
        if (mainRepository == null) {
            mainRepository = new MainRepository();
        }
    }

    public StateLiveData<List<Wallpaper>> getWallpaperList() {
        //Below null check will ensure that the rotation scenario is handled
        //This is where the people say LiveData is helpful in restoration of data
        if (wallpaperList == null) {
            wallpaperList = mainRepository.getImages();
        }
        return wallpaperList;
    }
}
