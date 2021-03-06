package org.opticalbox.smsbomb;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				EditText destinationInput = (EditText) findViewById(R.id.phoneNumberInput);
				EditText messageInput = (EditText) findViewById(R.id.messageInput);
				new SMSSender().sendBomb(destinationInput.getText().toString(), messageInput.getText().toString(), "10");
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
