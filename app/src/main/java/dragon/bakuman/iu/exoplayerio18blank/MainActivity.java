package dragon.bakuman.iu.exoplayerio18blank;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.exoplayer2.offline.ProgressiveDownloadAction;
import com.google.android.exoplayer2.util.Util;

import static dragon.bakuman.iu.exoplayerio18blank.Samples.SAMPLES;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, AudioPlayerService.class);
        Util.startForegroundService(this, intent);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SAMPLES));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgressiveDownloadAction action = new ProgressiveDownloadAction(
                        SAMPLES[position].uri, false, null, null);
                AudioDownloadService.startWithAction(
                        MainActivity.this,
                        AudioDownloadService.class,
                        action,
                        false);
            }
        });
    }

}