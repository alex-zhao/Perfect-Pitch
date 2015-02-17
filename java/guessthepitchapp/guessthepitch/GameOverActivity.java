package guessthepitchapp.guessthepitch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        TextView highScoreText = (TextView) findViewById(R.id.highscore_text);
        highScoreText.setText("High Score: " + GameActivity.highScore);
        GameActivity.score -= ((GameActivity.delta + Round2Activity.delta + Round3Activity.delta + Round4Activity.delta + Round5Activity.delta) / 5.0);
        if (Math.round(GameActivity.score) > GameActivity.highScore) {
            Toast.makeText(this, "New high score!", Toast.LENGTH_LONG).show();
            SharedPreferences prefs = this.getSharedPreferences("guessthepitchapp.guessthepitch", Context.MODE_PRIVATE);
            prefs.edit().putInt("guessthepitchapp.guessthepitch.highscore", (int) Math.round(GameActivity.score)).apply();
            highScoreText.setText("High Score: " + Math.round(GameActivity.score));
        }
        TextView scoreText = (TextView) findViewById(R.id.score_text);
        scoreText.setText("Final Score: " + Math.round(GameActivity.score)+ "/1000");

    }

    public void endGame(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
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
