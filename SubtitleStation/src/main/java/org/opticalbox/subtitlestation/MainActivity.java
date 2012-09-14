package org.opticalbox.subtitlestation;

import org.opticalbox.subtitlestation.model.SubtitleFileModel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SubtitleFileModel s = new SubtitleFileModel();
        s.addLine();
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(s.getLastLine().getBegin().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
