package comp3350project.bookorderingsystem.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.business.AccessCustomer;

public class MainActivity extends AppCompatActivity {

    private String accountName;
    private AccessCustomer accessCustomer;
    private AlertDialog Logindialog;
    private AlertDialog Signupdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Main.startUp();
        setContentView(R.layout.activity_main);
        accessCustomer = new AccessCustomer();
        setButton();
        signIn();
        signUp();
    }

   /* publick void onResume()
    {
        super.onResume();
        Log.d("------","onResume");

                Intent init = new Intent(MainActivity.this,
                        ManagerMainActivity.class);
                init.putExtra("name", accountName);
                startActivity(init);
    }*/

    public void managerLogin()
    {
        Intent init = new Intent(MainActivity.this,
                ManagerMainActivity.class);
        init.putExtra("name", accountName);
        startActivity(init);
    }

    public void signUp()
    {
        Button showSignUp=(Button)findViewById(R.id.signupBut);
        showSignUp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                AlertDialog.Builder logupBuilder=new AlertDialog.Builder(MainActivity.this);
                View signupView=getLayoutInflater().inflate(R.layout.activity_signup,null);
                final EditText signupAccount=(EditText) signupView.findViewById(R.id.signup_account);
                final EditText signupPassword=(EditText) signupView.findViewById(R.id.signup_password);
                final EditText signupRetypePassword=(EditText) signupView.findViewById(R.id.signup_retype_password);
                Button signupButton=(Button) signupView.findViewById(R.id.signup_button);

                signupButton.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(signupAccount.getText().toString().isEmpty() || signupPassword.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this,
                                    "please fill account or password",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if(signupRetypePassword.getText().toString().compareTo(signupPassword.getText().toString())!=0)
                        {
                            Toast.makeText(MainActivity.this,
                                    "please ensure passwords are same",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Signupdialog.dismiss();
                            Toast.makeText(MainActivity.this,
                                    "Sign up successful",
                                    Toast.LENGTH_SHORT).show();
                            onResume();
                        }
                    }
                });
                logupBuilder.setView(signupView);
                Signupdialog=logupBuilder.create();
                Signupdialog.show();
            }
        });
    }

   public void signIn()
   {
       Button showSignIn=(Button)findViewById(R.id.signinBut);
       showSignIn.setOnClickListener(new View.OnClickListener()
       {
           public void onClick(View view)
           {
               final AlertDialog.Builder loginBuilder = new AlertDialog.Builder(MainActivity.this);
               View signinView = getLayoutInflater().inflate(R.layout.activity_signin,null);
               final EditText signinAccount=(EditText) signinView.findViewById(R.id.signin_account);
               final EditText signinPassword=(EditText) signinView.findViewById(R.id.signin_password);
               Button signinButton = (Button) signinView.findViewById(R.id.signin_button);

                   signinButton.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View view)
                       {
                           if(signinAccount.getText().toString().compareTo("1")==0 &&signinPassword.getText().toString().compareTo("1")==0 )
                           {
                               managerLogin();
                           }
                           else if (!signinAccount.getText().toString().isEmpty() &&
                                   !signinPassword.getText().toString().isEmpty())
                           {
                               String account = signinAccount.getText().toString();
                               String password = signinPassword.getText().toString();
                               int verify = accessCustomer.verifyCustomer(account,password);
                               if(verify == 1)
                               {
                                   Logindialog.dismiss();
                                   accountName = account;
                                   Toast.makeText(MainActivity.this, "Login successful",
                                           Toast.LENGTH_SHORT).show();

                                   //close the dialog
                               }
                               else
                               {
                                   if(verify == -1)
                                   {
                                       Toast.makeText(MainActivity.this, "account doesn't exist",
                                               Toast.LENGTH_SHORT).show();
                                   }
                                   else
                                   {
                                       Toast.makeText(MainActivity.this, "incorrect password",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
                           } else {
                               Toast.makeText(MainActivity.this,
                                       "please fill account or password",
                                       Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               loginBuilder.setView(signinView);
               Logindialog=loginBuilder.create();
               Logindialog.show();
           }
       });
   }
    public void setButton()
    {

        //test for manager page************************
        Button testButton = (Button) findViewById(R.id.test);
        testButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, ManagerMainActivity.class);
                String account = "dbm001";
                i.putExtra("name", account);
                startActivity(i);
            }
        });
        //end test code**********************************



        //assume accountName for customer is "asdf"
        //accountName = "asdf";
        //set search button
        Button searchButton = (Button) findViewById(R.id.SearchBut);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                EditText editText = (EditText) findViewById(R.id.searchText);
                String word = editText.getText().toString();
                String[] message = {word,accountName};
                //intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra("search",message);
                startActivity(intent);
            }
        });

        //set category button
        Button category = (Button) findViewById(R.id.CateBut);
        category.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent init = new Intent(MainActivity.this,SearchCategoryActivity.class);
                init.putExtra("name", accountName);
                startActivity(init);
            }
        });
    }
}
