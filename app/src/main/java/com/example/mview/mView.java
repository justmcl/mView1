package com.example.mview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.String.valueOf;

public class mView extends View {
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private Paint mPainta = new Paint();
    private Paint mPaintb = new Paint();
    private Paint mPaintc = new Paint();

    private float mStartAngle = 0;

    private List<evevt> datas;
//            =new ArrayList<evevt>();
//    List<Person> personList=new ArrayList<Person>();

    private int mWidth, mHeight;
    private float h6=100;
    private float h7=220;
    private float h8=340;
    private float h9=460;
    private float h10=580;
    private float h11=700;
    private float h12=820;
    private float h13=940;
    private float h14=1060;
    private float h15=1180;
    private float h16=1300;
    private float h17=1420;
    private float h18=1540;
    private float h19=1660;
    private float h20=1780;
    private float h21=1900;
    private float h22=2020;
    private float h23=2140;
    private float h24=2260;
    private float i=100;

    private evevt e1;
    private evevt e2;
    private evevt e3;
    private evevt e4;
    private evevt e5;
    private evevt e6;
    private evevt e7;
    private evevt e8;



    private Timer mTimer;

    Calendar cal;

    String year;
    String month;
    String day;
    String hour;
    String minute;
    String second;

    String cTime1;




    public mView(Context context) {
        this(context, null);
    }

    public mView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initPaint();

        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        year = String.valueOf(cal.get(Calendar.YEAR));
        month = String.valueOf(cal.get(Calendar.MONTH))+1;
        day = String.valueOf(cal.get(Calendar.DATE));
        if (cal.get(Calendar.AM_PM) == 0)
            hour = String.valueOf(cal.get(Calendar.HOUR));
        else
            hour = String.valueOf(cal.get(Calendar.HOUR)+12);
        minute = String.valueOf(cal.get(Calendar.MINUTE));
        second = String.valueOf(cal.get(Calendar.SECOND));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    private void initPaint() {
        mPainta.setColor(Color.WHITE);       //设置画笔颜色
        mPainta.setStyle(Paint.Style.FILL);  //画笔模式为填充
        mPainta.setStrokeWidth(5f);         //设置画笔宽度为10px
        mPainta.setAntiAlias(true);
        mPainta.setTextSize(40);

        mPaintb.setColor(Color.BLUE);       //设置画笔颜色
        mPaintb.setStyle(Paint.Style.FILL);  //画笔模式为填充
        mPaintb.setStrokeWidth(5f);         //设置画笔宽度为10px
        mPaintb.setAntiAlias(true);
        mPaintb.setTextSize(40);

        mPaintc.setColor(Color.YELLOW);       //设置画笔颜色
        mPaintc.setStyle(Paint.Style.FILL_AND_STROKE);  //画笔模式为填充
        mPaintc.setStrokeWidth(7f);         //设置画笔宽度为10px
        mPaintc.setAntiAlias(true);
        mPaintc.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Calendar cal;
        String year;
        String month;
        String day;
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY); //绘制蓝色
        int hour1 =6;

        for(i=100;i<2161;i+=120){
            canvas.drawLine(100,i,2560,i,mPainta);
//            Log.e("TopPackage Name","name");//

            canvas.drawText (String.valueOf(hour1),50,i+5,mPainta);
            hour1+=1;
        }
        canvas.drawLine(100,h8,2560,h8,mPaintc);
        canvas.drawLine(100,h12,2560,h12,mPaintc);
        canvas.drawLine(100,h18,2560,h18,mPaintc);
        canvas.drawLine(100,h22,2560,h22,mPaintc);
        float currentTime= 100+120*(Float.parseFloat(hour)-6)+2*Float.parseFloat(minute);

//        Log.e("TopPackage Name","空");//

        if (null == datas)
            Log.e("TopPackage Name","knjkbnjkbnjkbnkjbjk");//

        else{
//            for (int j = 0; j < datas.size(); j++) {

            for (int j = 0; j < datas.size(); j++) {
                e1=datas.get(j);
                drawEvent(canvas,e1);}
//            switch(i){
//                case 1:
//                    e1=datas.get(0);
//                    drawEvent(canvas,e1);
//                    break;
//                case 2:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    break;
//                case 3:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    e3=datas.get(2);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    drawEvent(canvas,e3);
//                    break;
//                case 4:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    e3=datas.get(2);
//                    e4=datas.get(3);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    drawEvent(canvas,e3);
//                    drawEvent(canvas,e4);
//                    break;
//                case 5:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    e3=datas.get(2);
//                    e4=datas.get(3);
//                    e5=datas.get(4);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    drawEvent(canvas,e3);
//                    drawEvent(canvas,e4);
//                    drawEvent(canvas,e5);
//                    break;
//                case 6:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    e3=datas.get(2);
//                    e4=datas.get(3);
//                    e5=datas.get(4);
//                    e6=datas.get(5);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    drawEvent(canvas,e3);
//                    drawEvent(canvas,e4);
//                    drawEvent(canvas,e5);
//                    drawEvent(canvas,e6);
//                    break;
//                case 7:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    e3=datas.get(2);
//                    e4=datas.get(3);
//                    e5=datas.get(4);
//                    e6=datas.get(5);
//                    e7=datas.get(6);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    drawEvent(canvas,e3);
//                    drawEvent(canvas,e4);
//                    drawEvent(canvas,e5);
//                    drawEvent(canvas,e6);
//                    drawEvent(canvas,e7);
//                    break;
//                case 8:
//                    e1=datas.get(0);
//                    e2=datas.get(1);
//                    e3=datas.get(2);
//                    e4=datas.get(3);
//                    e5=datas.get(4);
//                    e6=datas.get(5);
//                    e7=datas.get(6);
//                    e8=datas.get(7);
//                    drawEvent(canvas,e1);
//                    drawEvent(canvas,e2);
//                    drawEvent(canvas,e3);
//                    drawEvent(canvas,e4);
//                    drawEvent(canvas,e5);
//                    drawEvent(canvas,e6);
//                    drawEvent(canvas,e7);
//                    drawEvent(canvas,e8);
//                    break;
//
//            }
        }
        canvas.drawLine(100,currentTime,2560,currentTime,mPaintb);
//
    }
    public void setData(List<evevt> datas){
        this.datas=datas;
    }
    public void drawEvent(Canvas canvas,evevt Event){
//
//        if (datas.get(k)==null)
//            return;
//        else {
//            Event=datas.get(k);
            String name = Event.getname();
            String stTime = Event.getstartTime();

            String enTime = Event.getendTime();

            int state = Event.getstate();
            int stTimef = getYOfTime(Event.getstartTime());
            int enTimef = getYOfTime(Event.getendTime());
            if(state==1){
                canvas.drawRoundRect(110, stTimef, 500, enTimef, 30, 30, mPaintc);
                canvas.drawRoundRect(110, stTimef, 500, enTimef, 30, 30, mPainta);
                canvas.drawCircle(130,stTimef,30,mPaintc);
                canvas.drawCircle(470,enTimef,30,mPaintc);
                canvas.drawCircle(130,stTimef,20,mPaintb);
                canvas.drawCircle(470,enTimef,20,mPaintb);
            }
            else if(state==0){canvas.drawRoundRect(110, stTimef, 500, enTimef, 30, 30, mPainta);}
            else if(state==2){
                if(stTimef>=0){canvas.drawText("A", 600, stTimef, mPainta);}
                if(enTimef>=0){canvas.drawText("B", 600, enTimef, mPainta);}
            }
            if (name==null){name="";}
            canvas.drawText(name, 120, 40 + stTimef, mPaintb);
    }
    public int getYOfTime(String string){
        Integer yOftime=0;

        if(string==null){yOftime=-100;}
        else if(string.length()==4) {
            String TimeH = string.substring(0, 2);
            String TimeM = string.substring(2, 4);
            yOftime=100+120*(Integer.parseInt(TimeH)-6)+2*Integer.parseInt(TimeM);

        }
        return yOftime;
    }
    public String getTimeofY(int Int){
        if(Int<100){Int=100;}
        String TimeH=valueOf(6+(Int-100)/120);
        if (TimeH.length()==1){TimeH="0"+TimeH;}
        String TimeM=valueOf(((Int-100)%120)/2);
        if (TimeM.length()==1){TimeM="0"+TimeM;}

        String time=TimeH+TimeM;
        return time;
    }
}





