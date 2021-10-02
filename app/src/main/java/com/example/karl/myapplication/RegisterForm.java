package com.example.karl.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.widget.TextView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.AdapterView;  
import android.widget.ArrayAdapter;  
import android.widget.Spinner;  
import android.widget.Toast;

public class RegisterForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
	Button button;
    	String[] ID = { "Social Security ID", "Driving License", "Passport"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_form);

		getSupportActionBar().setTitle("Register on ONEHealth");
       		//Getting the instance of Spinner and applying OnItemSelectedListener on it  
        	Spinner spinID = (Spinner) findViewById(R.id.SpinnerID);  
        	spinID.setOnItemSelectedListener(this);  
  
        	//Creating the ArrayAdapter instance having the ID list  
        	ArrayAdapter aaID = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ID);  
       	 	aaID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        	//Setting the ArrayAdapter data on the Spinner  
        	spinID.setAdapter(aaID);   
	}
  
    //Performing action onItemSelected and onNothing selected  
    @Override  
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {  
        Toast.makeText(getApplicationContext(),ID[position] , Toast.LENGTH_LONG).show();
    }

    @Override  
    public void onNothingSelected(AdapterView<?> arg0) {  
        // TODO Auto-generated method stub  
    }  

}

