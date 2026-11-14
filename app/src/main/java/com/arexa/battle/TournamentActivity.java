package com.arexa.battle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.*;
public class TournamentActivity extends Activity {
    private static final String FB="https://arexa-battle-26e5f-default-rtdb.firebaseio.com";
    private TextView annText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(5,5,20));
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18),dp(24),dp(18),dp(32));
        scroll.addView(root);
        TextView title = new TextView(this);
        title.setText("TOURNAMENTS");
        title.setTextColor(Color.rgb(255,220,0));
        title.setTextSize(24);
        title.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title);
        annText = new TextView(this);
        annText.setText("Loading...");
        annText.setTextColor(Color.rgb(0,255,100));
        annText.setTextSize(13);
        annText.setGravity(Gravity.CENTER);
        annText.setPadding(dp(14),dp(10),dp(14),dp(10));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.rgb(10,40,10));
        bg.setCornerRadius(dp(10));
        annText.setBackground(bg);
        root.addView(annText);
        setContentView(scroll);
        fetchAnn();
        new Handler().postDelayed(new Runnable(){
            public void run(){fetchAnn();new Handler().postDelayed(this,5000);}
        },5000);
    }
    private void fetchAnn(){
        new Thread(()->{
            try{
                java.net.URL url=new java.net.URL(FB+"/admin/announcement.json");
                java.net.HttpURLConnection c=(java.net.HttpURLConnection)url.openConnection();
                c.setConnectTimeout(4000);c.setReadTimeout(4000);
                byte[] buf=new byte[512];
                int len=c.getInputStream().read(buf);
                String val=new String(buf,0,len).trim().replace("\"","").replace("null","No announcement");
                runOnUiThread(()->annText.setText("📢 "+val));
            }catch(Exception e){runOnUiThread(()->annText.setText("📢 Connect karein"));}
        }).start();
    }
    private int dp(int v){return(int)(v*getResources().getDisplayMetrics().density+0.5f);}
}
