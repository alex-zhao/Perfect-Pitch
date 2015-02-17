package perfectpitchapp.perfectpitch;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.AudioManager;
import android.view.View;
import android.media.MediaPlayer;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    public static double score = 1000.0;
    public static int round = 1;

    public static int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getSharedPreferences("guessthepitchapp.guessthepitch", Context.MODE_PRIVATE);
        highScore = prefs.getInt("guessthepitchapp.guessthepitch.highscore", -1);
    }

    public void startGame(View view) {
        score = 1000.0;
        round = 1;

        //startRound();

        Intent intent = new Intent(this, GameActivity.class);
        this.startActivity(intent);
    }

    public void learnGame(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        this.startActivity(intent);
    }

    public void gameSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
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
