package com.sorftbuild.program.utils;

import android.util.Log;

import java.util.Random;

/**
 *  随机数工具类
 * @author CrabSaber
 *
 */
public class RandomUnitUtils {

	/**
	 * 获取一个整型随机数单元
	 * @param min 下限
	 * @param max 上限
	 * @return 整型随机数
	 */
	public static int getRandomIntUnit(int min,int max) {
		int randomUnit = (int)(min + Math.random() * (max-min+1));
		if (randomUnit >= min && randomUnit <= max) {
			return randomUnit;
		}else
			return min;

	}

	/**
	 * 获取随机运算符
	 * @return 随机运算符
	 */
	public static char getRandomOP() {
		char op ;
		int select = getRandomIntUnit(0, 1);

		switch (select) {
			case 0:
				op = '+';
				break;
			case 1:
				op = '-';
				break;
			default:
				Log.e("随机数出错","getRandomOP()");
				// 默认为 +
				op = '+';
				break;
		}
		return op;

	}





}
