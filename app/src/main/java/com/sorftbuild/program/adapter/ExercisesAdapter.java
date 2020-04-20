package com.sorftbuild.program.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.beardedhen.androidbootstrap.font.FontAwesome;
import com.sorftbuild.program.R;
import com.sorftbuild.program.application.MyApplication;
import com.sorftbuild.program.model.Exercise;
import com.sorftbuild.program.utils.ExerciseProduction;
import com.sorftbuild.program.view.FinishActivity;

import java.util.ArrayList;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    private MyApplication application;
    private ArrayList<Exercise> exercises;
    private Context context;

    public ExercisesAdapter(ArrayList<Exercise> exercises,Context context) {
        this.exercises = exercises;
        this.context = context;

        application = (MyApplication)context.getApplicationContext();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_exercise,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String problem = ExerciseProduction.problemToString(exercises.get(position).getLeftPart(),
                exercises.get(position).getOp(),
                exercises.get(position).getRightPart());

        initeViewHolder(holder.itemView,problem,exercises.get(position).getResult(),position);



    }


    @Override
    public int getItemCount() {
        return exercises.size();
    }


    /**
     *  初始化 view
     * @param view
     * @param problem
     * @param result
     * @param position
     * @return
     */
    private View initeViewHolder(View view, String problem, final int result, final int position){
        AwesomeTextView exerciseId  = view.findViewById(R.id.exercise_id);
        AwesomeTextView problemText = view.findViewById(R.id.exercise_text);

        final BootstrapEditText answerEidtText = view.findViewById(R.id.answer_edittext);
        final AwesomeTextView iconResultText = view.findViewById(R.id.text_tf);

        // 答案
        final AwesomeTextView answerText = view.findViewById(R.id.tx_answer);


        // 提交按钮
        final BootstrapButton btnSubmit = view.findViewById(R.id.btn_submit);

        // 设定
        exerciseId.setText(context.getResources().getString(R.string.exercise_id )+ String.valueOf(position + 1) + "/" + String.valueOf(application.getExerciseSize()));
        problemText.setText(problem);
        answerText.setText(context.getResources().getString(R.string.tx_answer  ) + String.valueOf(result));
        answerText.setVisibility(View.INVISIBLE); // 答案隐藏
        iconResultText.setVisibility(View.INVISIBLE); // 图标隐藏


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(answerEidtText.getText())){
                    Toast.makeText(context,R.string.toast_tip_nullanswer,Toast.LENGTH_LONG).show();
                }else{
                    // 按钮将不可点
                    btnSubmit.setClickable(false);
                    btnSubmit.setBootstrapBrand(DefaultBootstrapBrand.SECONDARY);
                    // 按钮隐藏
                    btnSubmit.setVisibility(View.INVISIBLE);

                    // 已做题数量
                    int countDone = application.getCountDone();
                    countDone+=1;
                    application.setCountDone(countDone);
                    // 移除未作题
                    ArrayList<Integer> list = application.getNullDoList();
                    list.remove(Integer.valueOf(position));
                    application.setNullDoList(list);

                    // 显示结果图标
                    int answer = Integer.parseInt(answerEidtText.getText().toString());
                    if (answer == result){  // 答案正确
                        int countTrue = application.getCountTrueNum();
                        countTrue += 1;
                        application.setCountTrueNum(countTrue);

                        // 显示正确图标
                        changeResultText(iconResultText,answerText,true);
                    }else {
                        changeResultText(iconResultText,answerText,false);
                    }

                }
            }
        });

        return view;

    }



    /**
     *  修改显示 隐藏要素
     * @param iconResultText
     * @param answerText
     * @param isTrue
     */
    private void changeResultText(AwesomeTextView iconResultText,AwesomeTextView answerText,boolean isTrue){
        answerText.setVisibility(View.VISIBLE);
        iconResultText.setVisibility(View.VISIBLE);

        if (isTrue){
            iconResultText.setFontAwesomeIcon(FontAwesome.FA_CHECK_CIRCLE);
            iconResultText.setBootstrapBrand(DefaultBootstrapBrand.SUCCESS);
        }else {
            iconResultText.setFontAwesomeIcon(FontAwesome.FA_TIMES_CIRCLE);
            iconResultText.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AwesomeTextView exerciseId  ;
        AwesomeTextView problemText ;

        BootstrapEditText answerEditText ;
        AwesomeTextView iconResultText;
        AwesomeTextView answerText ;

        // 两个按钮待定
        BootstrapButton btnSubmit ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
