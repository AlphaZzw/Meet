package com.alpha.framework.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.alpha.framework.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    private static SimpleDateFormat mSimpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("================");
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")================:");
        buffer.append(log);
        return buffer.toString();
    }

    /**
     * 获取文件名、方法名、所在行数
     *
     * @param sElements
     */
    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }


    //log.i
    public static void i(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                getMethodNames(new Throwable().getStackTrace());
                Log.i(className, createLog(text));
            }
        }
    }

    //log.e
    public static void e(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                getMethodNames(new Throwable().getStackTrace());
                Log.e(className, createLog(text));
            }
        }
    }

    public static void writeToFile(String text) {
        //开始写入
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            //文件路径
            String fileRoot = Environment.getExternalStorageDirectory().getPath() + "/Meet/";
            String fileName = "Meet.log";
            // 时间 + 内容
            String log = mSimpleDateFormat.format(new Date()) + " " + text + "\n";
            //检查父路径
            File fileGroup = new File(fileRoot);
            //创建根布局
            if (!fileGroup.exists()) {
                fileGroup.mkdirs();
            }
            //创建文件
            File fileChild = new File(fileRoot + fileName);
            if (!fileChild.exists()) {
                fileChild.createNewFile();
            }
            fileOutputStream = new FileOutputStream(fileRoot + fileName, true);
            //编码问题 GBK 正确的存入中文
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, Charset.forName("gbk")));
            bufferedWriter.write(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            e(e.toString());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
