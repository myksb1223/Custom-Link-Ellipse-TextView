package com.plokia.linkellipsetextview;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.plokia.linkellipsetextview.LinkEllipseTextView.TextLinkClickListener;
import com.plokia.linkellipsetextview.LinkEllipseTextView.TextMoreClickListener;

public class MainActivity extends Activity {
	private static String TAG = "MainActivity";
	private LinkEllipseTextView leTextView;
	private Button linkableButton, ellipsableButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String str = "Hello world! http://www.google.com, Hello world! http://www.google.com, Hello world! http://www.google.com, Hello world! http://www.google.com, Hello world! http://www.google.com";
		
		TextView textView = (TextView)findViewById(R.id.textView);
		textView.setText(str);
		
		leTextView = (LinkEllipseTextView)findViewById(R.id.leTextView);
		leTextView.setText(str);
		leTextView.setOnTextLinkClickListener(new TextLinkClickListener() {

			@Override
			public void onTextLinkClick(View textView, String clickedString) {
				// TODO Auto-generated method stub
				Log.d(TAG, "Url clicked");
  			Toast.makeText(MainActivity.this, new StringBuilder().append("Link clicked"), Toast.LENGTH_SHORT).show();
			}
			
		});

		leTextView.setOnTextMoreClickListener(new TextMoreClickListener() {

			@Override
			public void onTextMoreClick(View textView, String clickedString) {
				// TODO Auto-generated method stub
				Log.d(TAG, "More clicked");
  			Toast.makeText(MainActivity.this, new StringBuilder().append("More clicked"), Toast.LENGTH_SHORT).show();				
			}			
		});
			
	  MovementMethod m = leTextView.getMovementMethod();
    if((m == null) || !(m instanceof LinkMovementMethod)) {
        if(leTextView.getLinksClickable()) {
        	leTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    } 		  		 
    
		linkableButton = (Button)findViewById(R.id.linkableButton);
		if(leTextView.getIsLinkable()) { 
			linkableButton.setText(getString(R.string.disable));
		}
		else {
			linkableButton.setText(getString(R.string.enable));
		}
		
		ellipsableButton = (Button)findViewById(R.id.ellipsableButton);
		if(leTextView.getIsEndEllipsable()) {
			ellipsableButton.setText(getString(R.string.ellipse_disable));
		}
		else {
			ellipsableButton.setText(getString(R.string.ellipse_enable));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void linkButtonPressed(View v) {
		if(leTextView.getIsLinkable()) {
			leTextView.setIsLinkable(false);
			linkableButton.setText(getString(R.string.enable));
		}
		else {
			leTextView.setIsLinkable(true);
			linkableButton.setText(getString(R.string.disable));
		}
	}
	
	public void ellipseButtonPressed(View v) {
		if(leTextView.getIsEndEllipsable()) {
			leTextView.setIsEndEllipsable(false);
			ellipsableButton.setText(getString(R.string.ellipse_disable));
		}
		else {
			leTextView.setIsEndEllipsable(true);
			ellipsableButton.setText(getString(R.string.ellipse_enable));
		}
	}
	
}
