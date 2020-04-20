package com.sorftbuild.program.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;
import com.sorftbuild.program.R;
import com.sorftbuild.program.adapter.ExercisesAdapter;
import com.sorftbuild.program.application.MyApplication;
import com.sorftbuild.program.model.Exercise;
import com.sorftbuild.program.utils.ExerciseProduction;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    MyApplication application;
    int selectIndex;                // 产生的式子list的类型,由广播获取
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        application = (MyApplication)getApplication();

        // 这里获取选择的题型
        selectIndex = getIntent().getIntExtra("selectIndex",0);

        ArrayList<Exercise> exercises = getExercises(application.getExerciseSize(),50,100,selectIndex);

        // 题
        final RecyclerView recyclerView = findViewById(R.id.recvclerview);
        // 布局
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        // 滑动定位
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


        // 适配器
        RecyclerView.Adapter adapter = new ExercisesAdapter(exercises,this);
        // 设置缓存大小
        recyclerView.setItemViewCacheSize(application.getExerciseSize());

        recyclerView.setAdapter(adapter);

        // 悬浮提交按钮，会跳转到未作题的位置
        FloatingActionButton fabSubmit = findViewById(R.id.fab_submit_s);

        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> list = application.getNullDoList();
                if (list.isEmpty()){  // 为空表示题都做完了
                    // 跳转即可
                    Intent intent = new Intent(ExerciseActivity.this, FinishActivity.class);
                    startActivity(intent);
                }else{      // 有没做的题
                    Toast.makeText(ExerciseActivity.this,R.string.toast_tip_exercise_has_null,Toast.LENGTH_SHORT).show();
                    // 跳转
                    int position = list.get(0);

                    linearLayoutManager.scrollToPositionWithOffset(position,0);
                    linearLayoutManager.setStackFromEnd(true);

                }
            }
        });

    }

    /**
     *  0-2 生成不同类型的 list
     * @param num         数量
     * @param min          下限
     * @param max           上限
     * @param choose        选择 （0-2）
     * @return  一个好的 ArrayList<Exercise>
     */
    private ArrayList<Exercise> getExercises(int num ,int min ,int max,int choose) {

        ExerciseProduction exp = new ExerciseProduction();
        ArrayList<Exercise> exercises = null;

        switch (choose) {
            case 0:
                exercises = exp.generateExerciseArray(num, 1, min, max);
                return exercises;
            case 1:
                exercises = exp.generateExerciseArray(num, 2, min, max);
                return exercises;
            case 2:
                exercises = exp.generateExerciseArray(num, 3, min, max);
                return exercises;
            default:
                Log.e(ExerciseActivity.class.getName(),"功能选择错误!\n");
                return null;
        }
    }

    @Override
    protected void onStart() {


        super.onStart();
    }
}
