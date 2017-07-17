package comp3350project.bookorderingsystem.presentation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp3350project.bookorderingsystem.R;
import comp3350project.bookorderingsystem.application.Main;
import comp3350project.bookorderingsystem.business.AccessCustomer;
import comp3350project.bookorderingsystem.objects.Customer;

public class MainActivity extends AppCompatActivity {

    private String accountName="";
    private AccessCustomer accessCustomer;
    private AlertDialog Logindialog;
    private AlertDialog Signupdialog;
    private Button showSignUp;
    private Button showSignIn;
    private Button showLogOut;
    private Button showMyAccount;
    private Button searchButton;
    private Button category;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();

        Main.startUp();
        setContentView(R.layout.activity_main);
        accessCustomer = new AccessCustomer();
        setButton();
        checkLogStatus();
        signIn();
        signUp();
        logOut();
        MyAccount();
    }

    /*******************************
     * check if the user has already log in, if not, hide the search and category button
     ******************************/
    public void checkLogStatus()
    {
        if(accountName.compareTo("")==0)
        {
            showLogOut.setVisibility(View.GONE);
            showMyAccount.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            category.setVisibility(View.GONE);
            searchButton.setVisibility(View.GONE);
            showSignUp.setVisibility(View.VISIBLE);
            showSignIn.setVisibility(View.VISIBLE);
        }
        else
        {
            showLogOut.setVisibility(View.VISIBLE);
            showMyAccount.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            category.setVisibility(View.VISIBLE);
            searchButton.setVisibility(View.VISIBLE);
            showSignUp.setVisibility(View.GONE);
            showSignIn.setVisibility(View.GONE);
        }
    }


    /*******************************************************
     set the log out button
     ********************************************************/
    public void logOut()
    {
        showLogOut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                accountName="";
                checkLogStatus();
                Toast.makeText(MainActivity.this,
                        "Log out successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*******************************************************
     set the my account button, when click the button, go
     to the my account page
     ********************************************************/
    public void MyAccount()
    {
        showMyAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyAccountActivity.class);
                intent.putExtra("name",accountName );
                startActivity(intent);
            }
        });
    }

    /*******************************************************
     when user log in with a manager account, go to the manager
     main page
     ********************************************************/
    public void managerLogin()
    {
        Intent init = new Intent(MainActivity.this,
                ManagerMainActivity.class);
        init.putExtra("name", accountName);
        startActivity(init);
    }


    /*******************************************************
     when user sign up, save the account's information to database
     ********************************************************/
    public void signUp()
    {
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
                        try {
                            checkSignupInformaiton(signupAccount.getText().toString(), signupPassword.getText().toString(), signupRetypePassword.getText().toString());
                        }catch(signUpInException e)
                        {
                            Toast.makeText(MainActivity.this,
                                    e.getMsg(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                logupBuilder.setView(signupView);
                Signupdialog=logupBuilder.create();
                Signupdialog.show();
            }
        });
    }



    /*******************************************************
     when user sign in, check if the account information match the account in database,
     ********************************************************/
   public void signIn()
   {
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
                           try {
                               checkSigninInformation(signinAccount.getText().toString(), signinPassword.getText().toString());
                           }catch(signUpInException e)
                           {
                               Toast.makeText(MainActivity.this,
                                       e.getMsg(),
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





    /*******************************************************
     *when user click search button, to the the search page.
     * when user click category button, go to the search by category page.
     ********************************************************/
    public void setButton()
    {
        searchButton = (Button) findViewById(R.id.SearchBut);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                EditText editText = (EditText) findViewById(R.id.searchText);
                String word = editText.getText().toString();
                String[] message = {word,accountName};
                intent.putExtra("search",message);
                startActivity(intent);
            }
        });
        search=(EditText)findViewById(R.id.searchText);
        showLogOut=(Button)findViewById(R.id.logOutButton2);
        showMyAccount=(Button)findViewById(R.id.MyAccountButton2);
        showSignUp=(Button)findViewById(R.id.signupBut);
        showSignIn=(Button)findViewById(R.id.signinBut);

        //set category button
        category = (Button) findViewById(R.id.CateBut);
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


    /*******************************************************
     signup information check
     ********************************************************/
    public void checkSignupInformaiton(String account,String password,String retypePassword) throws signUpInException
    {   signUpInException e = new signUpInException();
        if(account.isEmpty() || password.isEmpty())
        {

            e.setMsg("please fill account or password");
            throw e;

        }
        else if(password.compareTo(retypePassword)!=0)
        {
            e.setMsg("please ensure passwords are same");
            throw e;

        }
        else if(checkNewAccount(account)==false)
        {
            e.setMsg("Account already exist");
            throw e;
        }
        else
        {
            Signupdialog.dismiss();
            Toast.makeText(MainActivity.this,
                    "Sign up successful",
                    Toast.LENGTH_SHORT).show();
            onResume();
            Customer newCus=new Customer(account,password);
            accessCustomer.addCustomer(newCus);
            accountName=account;
            checkLogStatus();

        }
    }

    /*******************************************************
       signin information match database
     ********************************************************/
    public void checkSigninInformation(String account,String password)throws signUpInException
    {   signUpInException e = new signUpInException();
        if (!account.isEmpty() && !password.isEmpty())
        {
            int verify = accessCustomer.verifyCustomer(account,password);
            if(verify == 1)
            {
                //close the dialog
                Logindialog.dismiss();
                accountName = account;
                Toast.makeText(MainActivity.this, "Login successful",
                        Toast.LENGTH_SHORT).show();
                checkLogStatus();
                if(accountName.length() >= 3) {
                    if (accountName.substring(0, 3).equals("dmb")) {
                        managerLogin();
                    }
                }
            }
            else
            {
                if(verify == -1)
                {

                    e.setMsg("account doesn't exist");
                    throw e;
                }
                else
                {

                    e.setMsg("incorrect password");
                    throw e;
                }
            }
        } else {

            e.setMsg( "please fill account or password");
            throw e;
        }
    }

    /*******************************************************
     customer's account can not begin with "dmb"
     ********************************************************/
    public Boolean checkNewAccount(String newAccountName)
    {
        Boolean result=true;
        if(accessCustomer.checkAccount(newAccountName))
        {
            result=false;
        }
        else if(newAccountName.length()>=3)
        {
            if(newAccountName.charAt(0)=='d' && newAccountName.charAt(1)=='m' && newAccountName.charAt(2)=='b')
            {
                result=false;
            }
        }
        return result;
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

}

class signUpInException extends Exception {
    String msg;
    String getMsg() {
        return msg;
    }
    void setMsg(String msg) {
        this.msg = msg;
    }
}
