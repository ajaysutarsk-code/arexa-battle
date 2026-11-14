package com.arexa.battle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.*;
public class LeaderboardActivity extends Activity {
    private static final String FB="https://arexa-battle-26e5f-default-rtdb.firebaseio.com";
    private LinearLayout list;
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
        title.setText("LEADERBOARD");
        title.setTextColor(Color.rgb(255,220,0));
        title.setTextSize(22);
        title.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title);
        list = new LinearLayout(this);
        list.setOrientation(LinearLayout.VERTICAL);
        root.addView(list);
        setContentView(scroll);
        showDemo();
        new Handler().postDelayed(new Runnable(){
            public void run(){showDemo();new Handler().postDelayed(this,5000);}
        },5000);
    }
    private void showDemo(){
        list.removeAllViews();
        String[][] data={{"1","ProSniper","1240"},{"2","KingSlayer","1180"},{"3","ArexaChamp","1120"}};
        for(String[] r:data){
            LinearLayout row=new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(dp(16),dp(14),dp(16),dp(14));
            GradientDrawable bg=new GradientDrawable();
            bg.setColor(Color.rgb(15,15,40));
            bg.setCornerRadius(dp(10));
            row.setBackground(bg);
            LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,dp(54));
            p.setMargins(0,dp(5),0,dp(5));
            list.addView(row,p);
            TextView rank=new TextView(this);
            rank.setText(r[0]);
            rank.setTextColor(Color.WHITE);
            rank.setTextSize(15);
            rank.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
            rank.setWidth(dp(40));
            row.addView(rank);
            TextView name=new TextView(this);
            name.setText(r[1]);
            name.setTextColor(Color.WHITE);
            name.setTextSize(14);
            name.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1f));
            row.addView(name);
            TextView score=new TextView(this);
            score.setText(r[2]+" kills");
            score.setTextColor(Color.rgb(255,220,0));
            score.setTextSize(13);
            row.addView(score);
        }
    }
    private int dp(int v){return(int)(v*getResources().getDisplayMetrics().density+0.5f);}
}
