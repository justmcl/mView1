package com.example.mview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private mdh dbHelper;
    private List<evevt> datas =new ArrayList<evevt>();
    private List<Integer> selected =new ArrayList<Integer>();


    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;




    TextView tv1;
    TextView tv2;

    public static final int UPDATE_TEXT = 1;

    private int maxscrool=2560;

    private int offsetX;
    private int offsetY;
    private int offsetX2;
    private int offsetY2;
    private int startX;
    private int startY;
    private int startX2;
    private int startY2;
    private int asdf=0;
    private int flag2=0;
    private int flag3=0;


    private int oldy1=0;
    private int oldy2=0;




    private int selEvent=-1;


    private Handler handler = new Handler() {
        int flag1;
        int flag2;
        int sum=0;
        public void handleMessage(Message msg) {
            mView mView1 = (mView) findViewById(R.id.m1);

            switch (msg.what) {
                case UPDATE_TEXT:
                    mView1.setData(datas);

                    mView1.invalidate();
                break;
            default: break;
            }
        flag1=msg.arg1;
        flag2=msg.arg2;
        if(sum+flag2>=0 & sum+flag2<1200){
            mView1.scrollBy(0,flag2);
            sum+=flag2;}

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView mView1 = (mView) findViewById(R.id.m1);

        dbHelper = new mdh(this, "eventList.db", null, 1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button creat = (Button) findViewById(R.id.bt1);
        creat.setOnClickListener(this);

        Button update = (Button) findViewById(R.id.bt2);
        update.setOnClickListener(this);

        Button show = (Button) findViewById(R.id.bt3);
        show.setOnClickListener(this);

        Button delete = (Button) findViewById(R.id.bt4);
        delete.setOnClickListener(this);

        Button search = (Button) findViewById(R.id.bt5);
        search.setOnClickListener(this);

        Button bt6 = (Button) findViewById(R.id.bt6);
        bt6.setOnClickListener(this);

        Button bt7 = (Button) findViewById(R.id.bt7);
        bt7.setOnClickListener(this);

        mView1.setOnTouchListener(touch);
        mView1.setOnLongClickListener(longclick);
//        mView1.setOnClickListener(click);


        LinearLayout ll=(LinearLayout) findViewById(R.id.ll);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et5 = (EditText) findViewById(R.id.et5);

        datas.clear();
        SQLiteDatabase db0 = dbHelper.getWritableDatabase(); // 查询 Book 表中所有的数据
        Cursor cursor = db0.query("Book", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id =cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String startTime = cursor.getString(cursor.getColumnIndex("startTime"));
            String endTime =cursor.getString(cursor.getColumnIndex("endTime"));
            String type =cursor.getString(cursor.getColumnIndex("type"));
            int state =cursor.getInt(cursor.getColumnIndex("state"));

            Log.e("初始数据",startTime);//


            evevt p=new evevt(id,name, startTime, endTime, type, state);

            datas.add(p);

            TextView tv=new TextView(this);

            tv.setText(p.toString());
            tv.setTextSize(18);
            //3.把TextView设置成线性布局的子节点
            ll.addView(tv);
            tv.setClickable(true);
        }
        mView1.setData(datas);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;

    }
    @Override public boolean onOptionsItemSelected(MenuItem item) { switch (item.getItemId()) { case R.id.backup: Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT). show();
        break;
        case R.id.delete: Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT). show();
            break;
        case R.id.settings: Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT). show();
            break; default:
    } return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                String inputText1 = et1.getText().toString();
                String inputText2 = et2.getText().toString();
                String inputText3 = et3.getText().toString();
                String inputText4 = et4.getText().toString();
                String inputText5 = et5.getText().toString();
                if(inputText3.length()!=4 | inputText4.length()!=4){
                    Toast.makeText(MainActivity.this, "时间要输入4位数字哦", Toast.LENGTH_LONG).show();
                }
                else {
                    ContentValues values = new ContentValues(); // 开始组装第一条数据
                    int i = Integer.parseInt(inputText1);
                    values.put("id",i);
                    values.put("name", inputText2);
                    values.put("startTime", inputText3);
                    values.put("endTime", inputText4);
                    values.put("type", inputText5);
                    values.put("state", 0);



                    db1.insert("Book", null, values); // 插入第一条数据
                    values.clear();
                }

                break;
            case R.id.bt2:
                SQLiteDatabase db3 = dbHelper.getWritableDatabase();
                String inputTextu1 = et1.getText().toString();
                String inputTextu2 = et2.getText().toString();
                String inputTextu3 = et3.getText().toString();
                String inputTextu4 = et4.getText().toString();
                String inputTextu5 = et5.getText().toString();

                int iu = Integer.parseInt(inputTextu1);
                ContentValues values2 = new ContentValues();
                values2.put("id", iu);
                if(et2.length()!=0){values2.put("name", inputTextu2);
                }
                if(et3.length()!=0){
                    if(inputTextu3.length()!=4){
                        Toast.makeText(MainActivity.this, "时间要输入4位数字哦", Toast.LENGTH_LONG).show();
                    }
                    else {values2.put("startTime", inputTextu3);datas.get(iu-1).setstartTime(inputTextu3);}
                }
                if(et4.length()!=0){
                    if( inputTextu4.length()!=4){
                        Toast.makeText(MainActivity.this, "时间要输入4位数字哦", Toast.LENGTH_LONG).show();
                    }
                    else {values2.put("endTime", inputTextu4);datas.get(iu-1).setendTime(inputTextu4);}
                }
                if(et5.length()!=0){values2.put("type", inputTextu5);
                }

                db3.update("Book", values2, "id = ?", new String[] { inputTextu1});
                values2.clear();
                new Thread(new Runnable() { @Override public void run() { Message message = new Message(); message.what = UPDATE_TEXT; handler.sendMessage(message); // 将 Message 对象发送出去
                }
                }).start();

                break;

            case R.id.bt3:
                datas.clear();

                LinearLayout ll=(LinearLayout) findViewById(R.id.ll);
                ll.removeAllViews();
                SQLiteDatabase db2 = dbHelper.getWritableDatabase(); // 查询 Book 表中所有的数据
                Cursor cursor = db2.query("Book", null, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    int id =cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String startTime = cursor.getString(cursor.getColumnIndex("startTime"));
                    String endTime =cursor.getString(cursor.getColumnIndex("endTime"));
                    String type =cursor.getString(cursor.getColumnIndex("type"));
                    int state =cursor.getInt(cursor.getColumnIndex("state"));


                    evevt p=new evevt(id,name, startTime, endTime, type,state);

                    datas.add(p);

                    TextView tv=new TextView(this);
                    //2.把人物的信息设置为文本的内容
                    tv.setText(p.toString());
                    tv.setTextSize(18);
                    //3.把TextView设置成线性布局的子节点
                    ll.addView(tv);
                }
                new Thread(new Runnable() { @Override public void run() { Message message = new Message(); message.what = UPDATE_TEXT; handler.sendMessage(message); // 将 Message 对象发送出去
                }
                }).start();
                break;
            case R.id.bt4:
                String inputText6 = et1.getText().toString();
                Toast.makeText(MainActivity.this, inputText6, Toast.LENGTH_LONG).show();

                int i1 = Integer.parseInt(inputText6);
                SQLiteDatabase db6 = dbHelper.getWritableDatabase();
                db6.delete("Book", "id = ?",new String[] { inputText6 });
                break;
            case R.id.bt6:
                Calendar cal;

                String year;
                String month;
                String day;
                String hour;
                String minute;
                String second;

                String cTime1;

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

                SQLiteDatabase db5 = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                if(hour.length()==1){hour="0"+hour;}
                if(minute.length()==1){minute="0"+minute;}

                values.put("startTime", "0900");
//                values.put("endTime", hour+minute);

//                    values.put("endTime", inputText4);
//                    values.put("type", inputText5);
                Toast.makeText(MainActivity.this,hour+"/"+second,Toast.LENGTH_SHORT).show();
                    values.put("state", 2);

                    db5.insert("Book", null, values); // 插入第一条数据
                    values.clear();
                break;

        }
    }
    private View.OnTouchListener touch = new View.OnTouchListener() {
        // 定义手指开始触摸的坐标
        int stopX;
        int stopY;
        int endX;
        int endY;
        int stopX2;
        int stopY2;
        int endX2;
        int endY2;
        int flag;
        int flag1=0;

        int shang;
        int xia;
        String sth;
        String stm;
        String eth;
        String etm;





        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Boolean a=false;
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    startX2 = (int)event.getX();
                    startY2 = (int)event.getY();
                    a=false;
                    for (int j = 0; j < datas.size(); j++){
                        evevt e1 = datas.get(j);
                        String id=valueOf(e1.getid());
                        String name=e1.getname();
                        String stTime=e1.getstartTime();
                        String enTime=e1.getendTime();
                        String type=e1.getType();

                        ContentValues values2 = new ContentValues();
                        int asd=startY;
                        int asdx=startX;

                        if(asd-asdf>=getYOfTime(e1.getstartTime()) & asd-asdf<=getYOfTime(e1.getendTime()) & asdx<500 ){
                            et1.setText(id);
                            et2.setText(name);
                            et3.setText(stTime);
                            et4.setText(enTime);
                            et5.setText(type);

                            if(flag3==1)  {
                                selEvent=j;
                                break;
                            }
                            else {selEvent=-1;}

                        }

                        else {selEvent=-1;}
                    }
                    flag2=1;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int)event.getX();
                    stopX2 = (int)event.getX();
                    stopY = (int)event.getY();
                    stopY2 = (int)event.getY();
                    offsetX = (stopX - startX);
                    offsetY =(stopY - startY);
                    offsetX2 = (stopX - startX);
                    offsetY2 =(stopY - startY);
                    if(flag2==1 & selEvent!=-1){
                        oldy1=getYOfTime(datas.get(selEvent).getstartTime());
                        oldy2=getYOfTime(datas.get(selEvent).getendTime());
                    }
                    if(selEvent==-1 | startX>500){
                        mView mView1 = (mView) findViewById(R.id.m1);
                        mView1.scrollTo(0,-(asdf+offsetY));
                        if(asdf+offsetY2>=0){mView1.scrollTo(0,-(0));}
                        else if(asdf+offsetY2<=-1500){mView1.scrollTo(0,-(-1500));}
                    }
                    else if(offsetX>200){
                        int y2=oldy2+offsetY;
                        if (y2<getYOfTime(datas.get(selEvent).getstartTime())){
                            y2=getYOfTime(datas.get(selEvent).getstartTime());
                        }

//                        datas.get(selEvent).setstartTime(getYTime(y1));
                        datas.get(selEvent).setendTime(getTimeofY(y2));

                        mView mView1 = (mView) findViewById(R.id.m1);
                        mView1.setData(datas);
                        mView1.postInvalidate();
                    }
                    else{
                        int y1=oldy1+offsetY;
                        int y2=oldy2+offsetY;

                        datas.get(selEvent).setstartTime(getTimeofY(y1));
                        datas.get(selEvent).setendTime(getTimeofY(y2));

                        mView mView1 = (mView) findViewById(R.id.m1);
                        mView1.setData(datas);
                        mView1.postInvalidate();
                    }

                    if(offsetY2>10|offsetY2<-10|offsetX2<-10|offsetX2<-10){flag2=2;}
                    else {flag2=3;}

                    a=true;
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int)event.getX();
                    endY = (int)event.getY();
                    endX2 = (int)event.getX();
                    endY2 = (int)event.getY();
                    int offsetsX = (endX - startX);
                    int offsetsY =(endY - startY);
                    if(selEvent==-1 | startX>500){

                        if(asdf+offsetY>=0){asdf=0;}
                        else if(asdf+offsetY<=-1500){asdf=-1500;}
                        else{asdf+=offsetsY;}
                    }
                    else if(offsetX>200){
                        int y1=getYOfTime(datas.get(selEvent).getstartTime());
                        int y2=oldy2+offsetsY;

                        SQLiteDatabase db3 = dbHelper.getWritableDatabase();

                        ContentValues values2 = new ContentValues();
                        values2.put("startTime", getTimeofY(y1));
                        values2.put("endTime", getTimeofY(y2));
                        et3.setText(getTimeofY(y1));
                        et4.setText(getTimeofY(y2));


                        db3.update("Book", values2, "id = ?", new String[] { valueOf(selEvent+1)});
                        values2.clear();
                    }
                    else {
                        int y1=oldy1+offsetsY;
                        int y2=oldy2+offsetsY;
                        SQLiteDatabase db3 = dbHelper.getWritableDatabase();

                        ContentValues values2 = new ContentValues();
                        values2.put("startTime", getTimeofY(y1));
                        values2.put("endTime", getTimeofY(y2));
                        et3.setText(getTimeofY(y1));
                        et4.setText(getTimeofY(y2));


                        db3.update("Book", values2, "id = ?", new String[] { valueOf(selEvent+1)});
                        values2.clear();
                    }

                    flag2=0;
                    a=false;
                    break;
                default:
                    break;

            }
            return false;
        }
//public boolean onTouchEvent(MotionEvent event) {
//    int x = (int) event.getX();
//    int y = (int) event.getY();
//    switch (event.getAction()) {
//        case MotionEvent.ACTION_DOWN:
//            // 记录触摸点坐标
//            lastX = x;
//            lastY = y;
//            break;
//        case MotionEvent.ACTION_MOVE:
//            // 计算偏移量
//            int offsetX = x - lastX;
//            int offsetY = y - lastY;
//            // 在当前left、top、right、bottom的基础上加上偏移量
//            layout(getLeft() + offsetX,
//                    getTop() + offsetY,
//                    getRight() + offsetX,
//                    getBottom() + offsetY);
//            break;
//    }
//    return true;
//}
    };
    private View.OnLongClickListener longclick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (flag2==1|flag2==3){
                 for (int j = 0; j < datas.size(); j++){
                     evevt e1 = datas.get(j);

                     ContentValues values2 = new ContentValues();
                     int asd=startY;
                     int asdx=startX;

                     if(asd-asdf>=getYOfTime(e1.getstartTime()) & asd-asdf<=getYOfTime(e1.getendTime()) & asdx<500 ){
                         if(datas.get(j).getstate()==0){
                            flag3=1;
    //                        values2.put("state",1);
    //                        datas.get(j).setstate(1);
    ////                        Toast.makeText(MainActivity.this, "ghfh", Toast.LENGTH_SHORT).show();
    //                        SQLiteDatabase db3 = dbHelper.getWritableDatabase();
    //
    //                        db3.update("Book", values2, "id = ?", new String[] {valueOf(j+1)});
    //                        values2.clear();
    //                        mView mView1 = (mView) findViewById(R.id.m1);
    //                        mView1.postInvalidate();
                         }
                         else if(datas.get(j).getstate()==1) {
                            flag3=2;
    //                        values2.put("state",0);
    //                        datas.get(j).setstate(0);
    ////                        Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
    //                        SQLiteDatabase db3 = dbHelper.getWritableDatabase();
    //
    //                        db3.update("Book", values2, "id = ?", new String[] {valueOf(j+1)});
    //                        values2.clear();
    //                        mView mView1 = (mView) findViewById(R.id.m1);
    //                        mView1.postInvalidate();
                         }
                         break;
                    }
                 }
                 if(flag3==1){ for (int j1 = 0; j1 < datas.size(); j1++){datas.get(j1).setstate(1);}
                 }
                 else if (flag3==2){ for (int j1 = 0; j1 < datas.size(); j1++){datas.get(j1).setstate(0);}
                 }
                 mView mView1 = (mView) findViewById(R.id.m1);
                 mView1.setData(datas);
                 mView1.postInvalidate();
            }

            return true;
        }
    };
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            for (int j = 0; j < datas.size(); j++){
                evevt e1 = datas.get(j);

                ContentValues values2 = new ContentValues();
                int asd=startY;

                if(asd-asdf>=getYOfTime(e1.getstartTime()) & asd-asdf<=getYOfTime(e1.getendTime())){
                    if(datas.get(j).getstate()==0){
                        values2.put("state",1);
                        datas.get(j).setstate(1);
//                        Toast.makeText(MainActivity.this, "ghfh", Toast.LENGTH_SHORT).show();
                        SQLiteDatabase db3 = dbHelper.getWritableDatabase();

                        db3.update("Book", values2, "id = ?", new String[] {valueOf(j+1)});
                        values2.clear();
                        mView mView1 = (mView) findViewById(R.id.m1);
                        mView1.postInvalidate();}
                    else if(datas.get(j).getstate()==1) {
                        values2.put("state",0);
                        datas.get(j).setstate(0);
//                        Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                        SQLiteDatabase db3 = dbHelper.getWritableDatabase();

                        db3.update("Book", values2, "id = ?", new String[] {valueOf(j+1)});
                        values2.clear();
                        mView mView1 = (mView) findViewById(R.id.m1);
                        mView1.postInvalidate();}
                }
            }
//            Toast.makeText(MainActivity.this, "我是单击button", Toast.LENGTH_SHORT).show();
        }
    };
    public int getYOfTime(String string){
        Integer yOftime=0;

        if(string==null){yOftime=0;}
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
