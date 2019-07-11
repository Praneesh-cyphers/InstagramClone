package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText    edtEmail,edtUsername,edtPassword;
    private Button      btnSignup,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*for setting the title in the action bar*/
        setTitle("Sign-up");

        edtEmail = findViewById(R.id.editText1);
        edtUsername = findViewById(R.id.editText2);
        edtPassword = findViewById(R.id.editText3);

        btnLogin = findViewById(R.id.button1);
        btnSignup = findViewById(R.id.button2);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:{
                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up"+edtUsername.getText().toString());
                progressDialog.show();


                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(SignupActivity.this
                                    ,appUser.getUsername()+" is signed up"
                                    ,FancyToast.LENGTH_SHORT
                                    ,FancyToast.SUCCESS,true).show();

                        }
                        else{
                            FancyToast.makeText(SignupActivity.this
                                    ,"There was an error: "+e.getMessage()
                                    ,FancyToast.LENGTH_LONG
                                    ,FancyToast.ERROR,true).show();
                        }

                        progressDialog.dismiss();
                    }
                });
            }
                break;
            case R.id.button2:{
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
             }
            break;

        }
    }
}
