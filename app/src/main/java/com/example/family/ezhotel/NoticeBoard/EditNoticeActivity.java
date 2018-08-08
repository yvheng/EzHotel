package com.example.family.ezhotel.NoticeBoard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.family.ezhotel.R;

public class EditNoticeActivity extends AppCompatActivity {
    private EditText editNotice;
    private TextView txtViewNotice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notice);

        editNotice = (EditText) findViewById(R.id.editNotice);



        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String noticeString = prefs.getString("KEY_NOTICE", "No value");
        editNotice.setText(noticeString);

    }

    public void editNoticeBoard(View v) {

        String text = String.valueOf(editNotice.getText());


        SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
        editor.putString("KEY_NOTICE", text);
        editor.apply();



        Toast.makeText(getApplicationContext(), "Notice Board has been edited.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NoticeBoardActivity.class);
        startActivity(intent);


    }


}
