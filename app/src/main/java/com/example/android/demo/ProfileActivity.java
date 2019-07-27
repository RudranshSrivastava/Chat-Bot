package com.example.android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.kommunicate.Kommunicate;

public class ProfileActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private TextView email;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();

        email=(TextView)findViewById(R.id.textView9);

        email.setText("Welcome "+ user.getEmail());

        logout=(Button)findViewById(R.id.button);

        Kommunicate.init(this, "27f9afffc52ba32430f6493fa0ee0440d");
    }

    public void signOut(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void openChat(View view) {
        finish();
        startActivity(new Intent(this, chat.class));
       /* WebView webView=new WebView(this);
        setContentView(webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocus();
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });
        webView.loadUrl("https://bot.dialogflow.com/18733952-cedb-4062-8d8c-6bc899db9607");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });

        */


        /*FirebaseUser customer=firebaseAuth.getCurrentUser();
        KMUser user = new KMUser();
        user.setUserId(customer.getEmail());
        List<String> agentList = new ArrayList();
        agentList.add("srivastava.rudransh.97@gmail.com"); //add your agentID. The agentId id the email id you have used to signup on kommunicate dashboard

        List<String> botList = new ArrayList();
        botList.add("srishti"); //Go to bots(https://dashboard.kommunicate.io/bot) -> Integrated bots -> Copy botID
        new KmChatBuilder(this).setChatName("Support").setSingleChat(false).setAgentIds(agentList).setBotIds(botList).launchChat(new KmCallback() {
            @Override

            public void onSuccess(Object message) {
                Utils.printLog(ProfileActivity.this, "ChatTest", "Success : " + message);
            }

            @Override
            public void onFailure(Object error) {
                Utils.printLog(ProfileActivity.this, "ChatTest", "Failure : " + error);
            }
        });
*/

    }
}
