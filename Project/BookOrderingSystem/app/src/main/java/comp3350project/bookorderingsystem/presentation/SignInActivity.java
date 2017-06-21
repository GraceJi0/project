package comp3350project.bookorderingsystem.presentation;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;

/**
 * Created by lee on 2017/6/20.
 */

public class SignInActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showSignIn=(Button)findViewById(R.id.signinBut);
        showSignIn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                AlertDialog.Builder loginBuilder=new AlertDialog.Builder(SignInActivity.this);
                View signinView=getLayoutInflater().inflate(R.layout.activity_signin,null);
                final EditText signinAccount=(EditText) signinView.findViewById(R.id.signin_account);
                final EditText signinPassword=(EditText) signinView.findViewById(R.id.signin_password);
                Button signinButton=(Button) signinView.findViewById(R.id.signin_button);

                signinButton.setOnClickListener(new View.OnClickListener()
                {
                   public void onClick(View view)
                   {
                       if(!signinAccount.getText().toString().isEmpty() && !signinPassword.getText().toString().isEmpty())
                       {
                           Toast.makeText(SignInActivity.this,
                                   "Login successful",
                                   Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(SignInActivity.this,
                                   "please fill account or password",
                                   Toast.LENGTH_SHORT).show();
                       }
                   }
                });
                loginBuilder.setView(signinView);
                AlertDialog dialog=loginBuilder.create();
                dialog.show();
            }
        });


    }

}
