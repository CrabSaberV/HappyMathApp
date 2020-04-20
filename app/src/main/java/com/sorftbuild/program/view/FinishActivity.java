package com.sorftbuild.program.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.sorftbuild.program.MainActivity;
import com.sorftbuild.program.R;
import com.sorftbuild.program.application.MyApplication;

import java.text.DecimalFormat;


public class FinishActivity extends AppCompatActivity {
    MyApplication application ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        application = (MyApplication) getApplication();

        AwesomeTextView radioText = findViewById(R.id.text_result_ratio);
        AwesomeTextView scoreText = findViewById(R.id.text_result_score);
        AwesomeTextView tipText = findViewById(R.id.text_result_tip);
        BootstrapButton btnReturn = findViewById(R.id.btn_return);

        // 比值分数
        String radioString = application.getCountTrueNum() + "/" + application.getExerciseSize();
        radioText.setText(radioString);

        // 所得分数 百分制
        float scoreRadio = 100/application.getExerciseSize() ;
        float score = scoreRadio * application.getCountTrueNum();
        DecimalFormat df = new DecimalFormat("#.0");   // 控制位数
        String scoreString = df.format(score);
        scoreText.setText(scoreString);

        // 返回按钮
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // 提示
        setTip(tipText,score);





    }


    /**
     * 根据分数 初始化提示文本
     * @param tipText
     * @param score
     */
    private void setTip(AwesomeTextView tipText,float score){
        if (score >= 100)
            tipText.setText(R.string.tx_tip_100);
        else if ( score >=90 && score <100 )
            tipText.setText(R.string.tx_tip_99_90);
        else if ( score >=80 && score <90 )
            tipText.setText(R.string.tx_tip_89_80);
        else if ( score >=70 && score <80 )
            tipText.setText(R.string.tx_tip_79_70);
        else if ( score >=60 && score <70 )
            tipText.setText(R.string.tx_tip_69_60);
        else if ( score >=50 && score <60 )
            tipText.setText(R.string.tx_tip_59_50);
        else if ( score >=40 && score <50 )
            tipText.setText(R.string.tx_tip_49_40);
        else if ( score >=30 && score <40 )
            tipText.setText(R.string.tx_tip_39_30);
        else if ( score >=10 && score <30 )
            tipText.setText(R.string.tx_tip_29_10);
        else
            tipText.setText(R.string.tx_tip_9_0);

    }
}
