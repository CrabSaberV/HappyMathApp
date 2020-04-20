package com.sorftbuild.program.utils;

import com.sorftbuild.program.model.Exercise;
import com.sorftbuild.program.utils.*;

import java.util.ArrayList;

public class ExerciseProduction {

    /**
     *  获取一个合法的 exercise
     * @param exercises  数组
     * @param index     生成的类型(0: 加法； 1：减法；2：混合)
     * @param min		上限
     * @param max		下限
     * @return			合法的exercise
     */
    public Exercise fetchLegalExercise (ArrayList<Exercise> exercises, int index, int min, int max) {
        Exercise target = assignExercise(index, min, max);

        while (true) {
            if(isLegal(target, exercises)) // 合法
                break;
            else {	// 不合法则再次生成
                target = assignExercise(index, min, max);
            }
        }

        return target;
    }


    /**
     *  获取 多个合法算式,
     * @param num			式子数量
     * @param index			生成的类型(0: 加法； 1：减法；2：混合)
     * @param min			下限
     * @param max			上限
     * @return				式子数组
     */
    public ArrayList<Exercise> generateExerciseArray(int num,int index,int min,int max) {

        ArrayList<Exercise> exercises = new ArrayList<Exercise>() ;
        for (int i = 0; i < num; i++) {
            exercises.add(fetchLegalExercise(exercises, index, min, max));
        }
        // 对应数量的式子 生成完毕
        return exercises;

    }


    /**
     *  根据index 分配取得对应的式子
     * @param index	   生成的类型(0: 加法； 1：减法；2：混合)
     * @param min		上限
     * @param max		下限
     * @return
     */
    private Exercise assignExercise(int index , int min ,int max) {

        switch (index) {
            case 1:
                return generateAddExercise(min, max);
            case 2:
                return generateSubExercise(min, max);
            case 3:
                return generateMixExercise(min, max);
            default:
                System.err.println("fetchLegalExercise（） index 非法");
                return null;
        }

    }

    /**
     *  判断产生的式子是否合法（不重复）
     * @param target 目标被测式子
     * @param exercises 式子数组
     * @return true： 合法  false： 不合法
     */
    private boolean isLegal(Exercise target , ArrayList<Exercise> exercises) {
        boolean isok = true;

        if (exercises.isEmpty() || exercises == null ) {
            // 内部没有则是合法的
            return true;
        }

        for (int i = 0; i < exercises.size(); i++) {
            if (target.getOp() != exercises.get(i).getOp())  // 符号不同跳转
                continue;
            if (target.getLeftPart() == exercises.get(i).getLeftPart()
                    && target.getRightPart() == exercises.get(i).getRightPart()) {	//
                isok = false;
                break;
            }
        }

        return isok;

    }

    /**
     *  获取一个减法exercise
     * @param min 下限
     * @param max 上限
     * @return  一个减法exercise (不确保重复)
     */
    private Exercise generateSubExercise(int min , int max) {
        Exercise exercise = new Exercise();

        exercise.setLeftPart(RandomUnitUtils.getRandomIntUnit(min, max));
        exercise.setOp('-');
        exercise.setRightPart(RandomUnitUtils.getRandomIntUnit(min, max));

        int result = caculation(exercise.getLeftPart(), exercise.getOp(), exercise.getRightPart());
        exercise.setResult(result);

        return exercise;
    }

    /**
     *  获取一个加法exercise
     * @param min 下限
     * @param max 上限
     * @return  一个加法exercise (不确保重复)
     */
    private Exercise generateAddExercise(int min , int max) {
        Exercise exercise = new Exercise();

        exercise.setLeftPart(RandomUnitUtils.getRandomIntUnit(min, max));
        exercise.setOp('+');
        exercise.setRightPart(RandomUnitUtils.getRandomIntUnit(min, max));

        int result = caculation(exercise.getLeftPart(), exercise.getOp(), exercise.getRightPart());
        exercise.setResult(result);

        return exercise;
    }

    /**
     *  获取一个任意exercise
     * @param min 下限
     * @param max 上限
     * @return  一个任意exercise (不确保重复)
     */
    private Exercise generateMixExercise(int min , int max) {
        Exercise exercise = new Exercise();

        exercise.setLeftPart(RandomUnitUtils.getRandomIntUnit(min, max));
        exercise.setOp(RandomUnitUtils.getRandomOP());
        exercise.setRightPart(RandomUnitUtils.getRandomIntUnit(min, max));

        int result = caculation(exercise.getLeftPart(), exercise.getOp(), exercise.getRightPart());
        exercise.setResult(result);

        return exercise;
    }

    /**
     *  计算
     * @param left 左部分
     * @param op   运算符
     * @param right 右部分
     * @return  结果
     */
    private int caculation(int left , char op , int right) {
        int result;
        switch (op) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    /**
     *  算数转换字符串
     * @param left
     * @param op
     * @param right
     * @return
     */
    public static String problemToString(int left,char op ,int right){
        String problem = left+" " + op + " " + right + " " + "= ";
        return problem;
    }
}
