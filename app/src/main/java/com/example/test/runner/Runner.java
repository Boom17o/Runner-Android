package com.example.test.runner;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;


public class Runner extends Activity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //初始化FacebookSdk，記得要放第一行，不然setContentView會出錯
        super.onCreate(savedInstanceState);

        // init facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_runner);

        Log.d("TAG", "GOGO");

        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.setIsDebugEnabled(true);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "LoginResult:" + loginResult.toString());
            }

            @Override
            public void onCancel() {
                Log.d("TAG", "onCancel()");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("TAG", error.toString());
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        loginButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                LoginManager.getInstance().logInWithReadPermissions(Runner.this, Arrays.asList("public_profile"));
            }
        });

        //register intent page
        Button register =(Button)findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(Runner.this, register.class);
             startActivity(intent);
            }
        });
        Button login =(Button)findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2=new Intent(Runner.this, Main.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

}
