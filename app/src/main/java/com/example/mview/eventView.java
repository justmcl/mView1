package com.example.mview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;

public class eventView extends View {
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
    private float h7=160;
    private float h8=220;
    private float h9=280;
    private float h10=340;
    private float h11=400;
    private float h12=460;
    private float h13=520;
    private float h14=580;
    private float h15=640;
    private float h16=700;
    private float h17=760;
    private float h18=820;
    private float h19=880;
    private float h20=940;
    private float h21=1000;
    private float h22=1060;
    private float h23=1120;
    private float h24=1180;
    private float i=100;

    private Timer mTimer;

    Calendar cal;

    String year;
    String month;
    String day;
    String hour;
    String minute;
    String second;

    String cTime1;




    public eventView(Context context) {
        this(context, null);
    }

    public eventView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initPaint();




//        mTimer = new Timer();
//        TimerTask task = new TimerTask() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void run() {
//                invalidate();
//            }
//        };
//
//        mTimer.schedule(task, 1000, 1000);
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
        mPaintc.setStyle(Paint.Style.FILL);  //画笔模式为填充
        mPaintc.setStrokeWidth(5f);         //设置画笔宽度为10px
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
            Log.e("TopPackage Name","name");//

            canvas.drawText (String.valueOf(hour1),50,i+5,mPainta);
            hour1+=1;
        }
        canvas.drawLine(100,h8,2560,h8,mPaintc);
        canvas.drawLine(100,h12,2560,h12,mPaintc);
        canvas.drawLine(100,h18,2560,h18,mPaintc);
        canvas.drawLine(100,h22,2560,h22,mPaintc);
        float currentTime= 100+60*(Float.parseFloat(hour)-6)+Float.parseFloat(minute);

        Log.e("TopPackage Name","空");//

        if (null == datas)
            Log.e("TopPackage Name","ll");//

        else{
            Log.e("TopPackage Name","空");//
            for (int j = 0; j < datas.size(); j++) {
                Log.e("TopPackage Name","山");
                evevt e1 = datas.get(j);
            String name=e1.getname();
                Log.e("TopPackage Name",name);
                Log.e("TopPackage Name",name);
            String stTime=e1.getstartTime();
            String stTimeH=stTime.substring(0,2);
            String stTimeM=stTime.substring(2,4);
            String enTime=e1.getendTime();
            String enTimeH=enTime.substring(0,2);
            String enTimeM=enTime.substring(2,4);
            Float stTimef=100+120*(Float.parseFloat(stTimeH)-6)+Float.parseFloat(stTimeM);
            Float enTimef=100+120*(Float.parseFloat(enTimeH)-6)+Float.parseFloat(enTimeM);
            canvas.drawRoundRect(110,stTimef,500,enTimef,30,30,mPainta);
            canvas.drawText (name,120,40+stTimef,mPaintb);
//            canvas.drawText (stTimeH+":"+stTimeM+"-"+enTimeH+":"+enTimeM,120,120+stTimef,mPaintb);
            }
        }
        canvas.drawLine(100,currentTime,2560,currentTime,mPaintb);
//
    }
    public void setData(List<evevt> datas){
        this.datas=datas;
    }



    }





