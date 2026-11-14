package com.arexa.battle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {
    private LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDashboard();
    }

    private void showDashboard() {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(Color.rgb(5, 5, 20));
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(18), dp(16), dp(18), dp(28));
        scroll.addView(content);
        addNavButton("TOURNAMENTS", Color.rgb(160, 30, 30), "TournamentActivity");
        addNavButton("LEADERBOARD", Color.rgb(70, 30, 140), "LeaderboardActivity");
        addNavButton("ADMIN PANEL", Color.rgb(50, 50, 50), "AdminActivity");
        setContentView(scroll);
    }

    private void addNavButton(String label, int color, final String activityName) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setTextSize(15);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(dp(12));
        button.setBackground(bg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(56));
        params.setMargins(0, dp(7), 0, dp(7));
        content.addView(button, params);
        button.setOnClickListener(v ->
            android.widget.Toast.makeText(this, label + " coming soon!", android.widget.Toast.LENGTH_SHORT).show());
    }

    private int dp(int value) {
        return (int)(value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
