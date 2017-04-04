package com.bb.asynctaskdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by bb on 2017/4/4.
 */

/**
 * AsyncTask<Params, Progress, Result>
 * <p>
 * Params定义传入异步任务的参数类型。如本例就是，String 在MainActivity中定义了两个参数"Begin"，"End"
 * 传入异步任务的参数，由UI线程调用execute()方法传入，由后台线程调用doInBackground()方法获取
 * <p>
 * Progress定义异步任务执行过程中UI线程与后台线程之间传递的参数的类型。如本例就是，Integer
 * 后台线程调用publishProgress()方法并传入参数，由UI线程调用onProgressUpdate()方法并获取参数值
 * <p>
 * Result定义异步任务执行结束后，后台线程向UI线程反馈执行结果时传递的参数类型。如本例就是，String
 * 后台线程执行doInBackground()方法结束后，通过返回值向UI线程反馈执行结果，由UI线程调用onPostExecute()方法
 * 并通过参数获取反馈结果
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    private static final String TAG = "ASYNC_TASK";

    //MainActivity的进度条
    ProgressBar myprogressbar = null;
    //MainActivity的文本
    TextView textView = null;
    // MainActivity的上下文
    Context mycontext = null;

    public MyAsyncTask(ProgressBar progressbar, TextView textview, Context context) {
        myprogressbar = progressbar;
        mycontext = context;
        textView = textview;
    }

    //onPreExecute方法用于在执行后台任务前做一些UI操作
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "onPreExecute() called");
        textView.setText("正在加载...");
        myprogressbar.setProgress(0);
    }

    //doInBackground方法内部执行后台任务,不可在此方法内修改UI
    //params 由UI线程调用execute()方法传入
    //返回的String类型参数被onPostExecute()方法调用。
    @Override
    protected String doInBackground(String... params) {
        Log.i(TAG, "doInBackground(Params... params) called");

        String ret = null;
        /**
         * 通过Begin,End演示多参数传值
         */
        String Begin = params[0];//取出值Begin
        String End = params[1];//取出值End
        Log.i(TAG, "doInBackground" + Begin);
        /**
         * 模拟耗时步骤
         */
        for (int i = 0; i < 10; i++) {
            /**
             * 后台线程向UI线程发布进度状态i
             */
            publishProgress(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "doInBackground" + End);

        ret = "更新完毕";
        return ret;
    }


    //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
    //result为doInBackground()返回的参数
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i(TAG, "onPostExecute(Result result) called");
        textView.setText(result);
    }

    @Override
    //onProgressUpdate方法用于更新进度信息
    //values为doInBackground()中publishProgress()返回的参数
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
        int progress = myprogressbar.getMax() / 10 * (values[0] + 1);
        myprogressbar.setProgress(progress);
        textView.setText("正在加载..." + progress + "%");
    }

    //onCancelled方法用于在取消执行中的任务时更改UI
    @Override
    protected void onCancelled() {
        Log.i(TAG, "onCancelled() called");
        textView.setText("已被取消");
        myprogressbar.setProgress(0);
    }
}
