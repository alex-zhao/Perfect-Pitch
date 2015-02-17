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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends Activity {

    public static int randPitch;
    public static int yourGuess;
    public static int delta = 0;
    public static double score = 1000.0;

    public static int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        SharedPreferences prefs = this.getSharedPreferences("guessthepitchapp.guessthepitch", Context.MODE_PRIVATE);

        highScore = prefs.getInt("guessthepitchapp.guessthepitch.highscore", -1);

        score = 1000.0;

        startRound();
    }

    public void startRound() {
        Random rand = new Random();
        randPitch = rand.nextInt(850) + 150;
        playSound();
    }

    public void playSound() {
        Handler h = new Handler();
        h.post(new Runnable() {
            public void run() {
                AudioHandler.playTone(randPitch);
            }
        });
    }

    public void playSound(View view) {
        playSound();
    }

    public void guess(View view) {
        EditText txt = (EditText) findViewById(R.id.guess_box);
        String guess = txt.getText().toString();
        try {
            yourGuess = Integer.parseInt(guess);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Not an integer!", Toast.LENGTH_LONG).show();
            return;
        }
        if (yourGuess < 150 || yourGuess > 1000) {
            Toast.makeText(this, "Number must be between 150 and 1000!", Toast.LENGTH_LONG).show();
            return;
        }
        delta = Math.abs(randPitch - yourGuess);
        Intent intent = new Intent(this, TransitionActivity.class);
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