package com.sorftbuild.program;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.sorftbuild.program.view.ExerciseActivity;
import com.sorftbuild.program.view.SkipActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inite();

    }

    private void inite(){
        BootstrapButton btnAdd = findViewById(R.id.btn_ex_add);
        BootstrapButton btnSub = findViewById(R.id.btn_ex_sub);
        BootstrapButton btnMix = findViewById(R.id.btn_ex_mix);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toOtherActivity(0);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toOtherActivity(1);
            }
        });

        btnMix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toOtherActivity(2);
            }
        });
    }

    /**
     *  根据 selectIndex 跳转到ExerciseActivity 并传递值
     * @param selectIndex 【0-2】 且会传递到activity
     */
    private void toOtherActivity(int selectIndex){
        Intent intent = new Intent(this, SkipActivity.class);
        intent.putExtra("selectIndex",selectIndex);
        startActivity(intent);
    }
}
