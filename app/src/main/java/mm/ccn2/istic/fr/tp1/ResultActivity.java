package mm.ccn2.istic.fr.tp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        User user = getIntent().getExtras().getParcelable("user");
        TextView displayerUser = (TextView) findViewById(R.id.displayUser);
        displayerUser.setText("Utilisateur: " + "\n" +
                                    "Prénom: " + user.getFirstname() +"\n" +
                                    "Nom: " + user.getLastname() +"\n" +
                                    "Date de naissance: " + user.getBirthday() +"\n" +
                                    "Ville de naissance: " + user.getBirthcity()
        );
    }
}
