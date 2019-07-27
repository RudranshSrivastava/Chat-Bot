package com.example.android.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private EditText conf_password;
    private TextView submit;
    private TextView login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        email=(EditText)findViewById(R.id.editText3);
        password=(EditText)findViewById(R.id.editText4);
        conf_password=(EditText)findViewById(R.id.editText5);
        submit=(TextView)findViewById(R.id.textView8);
        login=(TextView)findViewById(R.id.login);
        if(firebaseAuth.getCurrentUser() != null)
        {
            //start profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        submit.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view== submit)
        {
            registerUser();
        }
        if(view==login)
        {
            login();
        }

    }

    private void registerUser() {
        String em=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String cpass=conf_password.getText().toString().trim();

        if(TextUtils.isEmpty(em))
        {
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cpass))
        {
            Toast.makeText(this,"Please enter password again", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.equals(cpass)==false)
        {
            Toast.makeText(this,"Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Registered successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                }
                else
                {   Log.e("Firebase err", "" + task.getException());
                    Toast.makeText(SignUp.this,"Could not register, please try again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }

    public void login() {
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}
