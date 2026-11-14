package com.arexa.battle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
public class AdminActivity extends Activity {
    private static final String FB="https://arexa-battle-26e5f-default-rtdb.firebaseio.com";
    private TextView statusText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(5,5,20));
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20),dp(24),dp(20),dp(32));
        scroll.addView(root);
        TextView title = new TextView(this);
        title.setText("ADMIN PANEL");
        title.setTextColor(Color.rgb(255,220,0));
        title.setTextSize(22);
        title.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title);
        statusText = new TextView(this);
        statusText.setText("Ready");
        statusText.setTextColor(Color.rgb(0,255,100));
        statusText.setTextSize(12);
        statusText.setGravity(Gravity.CENTER);
        root.addView(statusText);
        EditText ann = new EditText(this);
        ann.setHint("Announcement");
        ann.setTextColor(Color.WHITE);
        ann.setHintTextColor(Color.GRAY);
        ann.setBackgroundColor(Color.rgb(20,20,45));
        ann.setPadding(dp(14),dp(12),dp(14),dp(12));
        root.addView(ann);
        Button btn = new Button(this);
        btn.setText("PUSH LIVE");
        btn.setTextColor(Color.WHITE);
        btn.setAllCaps(false);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.rgb(160,30,30));
        bg.setCornerRadius(dp(10));
        btn.setBackground(bg);
        root.addView(btn);
        btn.setOnClickListener(v->{
            String msg=ann.getText().toString().trim();
            if(msg.isEmpty()){Toast.makeText(this,"Enter message",Toast.LENGTH_SHORT).show();return;}
            firebasePut("/admin/announcement.json","\""+msg+"\"","Live!");
        });
        setContentView(scroll);
    }
    private void firebasePut(String path,String body,String msg){
        statusText.setText("Sending...");
        new Thread(()->{
            try{
                java.net.URL url=new java.net.URL(FB+path);
                java.net.HttpURLConnection c=(java.net.HttpURLConnection)url.openConnection();
                c.setRequestMethod("PUT");
                c.setRequestProperty("Content-Type","application/json");
                c.setDoOutput(true);
                c.getOutputStream().write(body.getBytes("UTF-8"));
                int code=c.getResponseCode();
                runOnUiThread(()->statusText.setText(code==200?"✅ "+msg:"❌ Error "+code));
            }catch(Exception e){runOnUiThread(()->statusText.setText("❌ "+e.getMessage()));}
        }).start();
    }
    private int dp(int v){return(int)(v*getResources().getDisplayMetrics().density+0.5f);}
}
