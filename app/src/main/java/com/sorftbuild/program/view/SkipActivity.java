package com.sorftbuild.program.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.sorftbuild.program.R;
import com.sorftbuild.program.application.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SkipActivity extends AppCompatActivity {
    MyApplication application;
    int selectIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);

        // 初始化
        application = (MyApplication)getApplication();
        application.setExerciseSize(0);
        application.setCountDone(0);
        application.setCountTrueNum(0);
        selectIndex = getIntent().getIntExtra("selectIndex",0);

        final BootstrapEditText numeditText = findViewById(R.id.tx_edit_num);
        BootstrapButton btnOk = findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(numeditText.getText())){
                    // 如果为空
                    Toast.makeText(SkipActivity.this,R.string.toast_tip_nullnum,Toast.LENGTH_SHORT).show();
                }else {
                    // 将数据存起来
                    int num = Integer.parseInt(numeditText.getText().toString());
                    application.setExerciseSize(num);
                    application.setNullDoList(getNUllDoList(num));

                    // 跳转
                    Intent intent = new Intent(SkipActivity.this, ExerciseActivity.class);
                    intent.putExtra("selectIndex",selectIndex);
                    startActivity(intent);
                }
            }
        });

    }


    /**
     * 获取并初始化 nulldoList 保存为做题的position
     * @param size 数组大小
     * @return
     */
    private ArrayList<Integer> getNUllDoList(int size){
        ArrayList<Integer> nullDoList = new ArrayList<Integer>();

        for (int i = 0 ; i<size ; i++){
            nullDoList.add(i);
        }

        return nullDoList;

    }
}
