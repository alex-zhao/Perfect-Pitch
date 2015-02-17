package guessthepitchapp.guessthepitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Transition5Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        TextView actualText = (TextView) findViewById(R.id.actual_value);
        actualText.setText("Actual Value: " + Round5Activity.randPitch);
        TextView yourGuessText = (TextView) findViewById(R.id.guessed_value);
        yourGuessText.setText("Your Value: " + Round5Activity.yourGuess);
        TextView deltaText = (TextView) findViewById(R.id.delta_value);
        deltaText.setText("You were off by: " + Round5Activity.delta);
        //GameActivity.score -= (Round5Activity.delta / 4.0);
    }

    public void continueGame(View view) {
        Intent intent = new Intent(this, GameOverActivity.class);
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
