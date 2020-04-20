package com.sorftbuild.program.application;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyApplication extends Application {
    private int exerciseSize;       // 题量
    // 1表示正确，0表示错误
    private int countTrueNum;       // 记录正确数量
    private int countDone;           // 记录已做题数量

    private ArrayList<Integer> nullDoList;      // 未作的题的position


    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
        Iconify
                .with(new FontAwesomeModule());
    }

    public int getExerciseSize() {
        return exerciseSize;
    }

    public void setExerciseSize(int exerciseSize) {
        this.exerciseSize = exerciseSize;
    }

    public int getCountTrueNum() {
        return countTrueNum;
    }

    public void setCountTrueNum(int countTrueNum) {
        this.countTrueNum = countTrueNum;
    }

    public int getCountDone() {
        return countDone;
    }

    public void setCountDone(int countDone) {
        this.countDone = countDone;
    }

    public ArrayList<Integer> getNullDoList() {
        return nullDoList;
    }

    public void setNullDoList(ArrayList<Integer> nullDoList) {
        this.nullDoList = nullDoList;
    }
}
