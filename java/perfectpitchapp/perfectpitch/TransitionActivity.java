package perfectpitchapp.perfectpitch;

import android.app.Activity;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.media.AudioManager;
import android.view.MotionEvent;
import android.view.View;
import android.media.MediaPlayer;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class TransitionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        TextView actualText = (TextView) findViewById(R.id.actual_value);
        actualText.setText("Actual Value: " + GameActivity.randPitch);
        TextView yourGuessText = (TextView) findViewById(R.id.guessed_value);
        yourGuessText.setText("Your Value: " + GameActivity.guess);
        TextView deltaText = (TextView) findViewById(R.id.delta_value);
        deltaText.setText("You were off by: " + GameActivity.delta);
        MainActivity.score -= (GameActivity.delta / 5.0);
    }

    public void continueGame(View view) {
        MainActivity.round++;
        if (MainActivity.round > 5) {
            Intent intent = new Intent(this, GameOverActivity.class);
            this.startActivity(intent);
        } else {
            Intent intent = new Intent(this, GameActivity.class);
            this.startActivity(intent);
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
