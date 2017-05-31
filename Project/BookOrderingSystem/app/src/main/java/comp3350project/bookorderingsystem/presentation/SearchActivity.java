package comp3350project.bookorderingsystem.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import comp3350project.bookorderingsystem.R;

import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "comp3010_group10.bookordering.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.Searchkey);
        textView.setTextSize(20);

        char[]splitMessage = message.toCharArray();
        String output = ""; //the output
        int MAX_NUM = 30;   //max number of char to display
        int i = 0;  //only display first 10 available chars
        while((i<MAX_NUM)&&(i<splitMessage.length))
        {
            output += String.valueOf(splitMessage[i]);
            i++;
        }
        if(splitMessage.length>MAX_NUM)
            output+= "...";

        textView.setText("Searching : "+"\" "+output+" \"");

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_search);
        //layout.addView(textView);
    }

    public void sendSearch(View view) {
        // Do something in response to button
        Intent intent = new Intent(this,SearchActivity.class);
        EditText editText = (EditText) findViewById(R.id.searchText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}