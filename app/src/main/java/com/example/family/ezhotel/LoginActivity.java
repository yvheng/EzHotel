package com.example.family.ezhotel;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;
    ConnectivityManager connMgr;
    NetworkInfo networkInfo;
    Boolean isConnected;
    private EditText editTextId, editTextPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextId = (EditText) findViewById(R.id.editTxtID);
        editTextPw = (EditText) findViewById(R.id.editTxtPW);
    }
    public void login(View v) {
        String username = String.valueOf(editTextId.getText());
        String password = String.valueOf(editTextPw.getText());
        try {
            // Check availability of network connection.
            if (isConnected) {
                if(username.equals("S001") || username.equals("S002") || username.equals("S003")){
                    if(password.equals("123456")){
                        Toast.makeText(getApplicationContext(), "Log In Successfully", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid Password.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Username.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Network is NOT available",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Error reading record:" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }
}
