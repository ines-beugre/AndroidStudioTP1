package mm.ccn2.istic.fr.tp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
//    private LinearLayout number;
    @Bind(R.id.valid) Button mValid;
    @Bind(R.id.lastname) TextView lastname;
    @Bind(R.id.firstname) TextView firstname;
    @Bind(R.id.birthday) TextView birthday;
    @Bind(R.id.birthcity) TextView birthcity;
    @Bind(R.id.dept_spinner) Spinner spinner;
//    @Bind(R.id.number_edit_text) TextView numero;
//    private Spinner deptSprinner;
    private View view;
    public static final String EXTRA_MESSAGE = "mmm.ccn2.istic.fr.tp1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
        @SuppressLint("WrongViewCast") LinearLayout number = findViewById(R.id.number_edit_text);

        //partie 1.1
//        mValid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String birthDept = spinner.getSelectedItem().toString();
//
//                Context context = getApplicationContext();
//                CharSequence info = "Votre nom est: " + lastname.getText().toString() + "\n" +
//                                    "Votre prénom: " + firstname.getText().toString() + "\n" +
//                                    "Votre date de naissance: " + birthday.getText().toString() + "\n" +
//                                    "Votre ville de naissance: " + birthcity.getText().toString() + "\n" +
//                                    "Votre departement de naissance: " + birthDept + "\n";
//
//                int duration = Toast.LENGTH_LONG;
//
//                Toast toast = Toast.makeText(context, info, duration);
//                toast.show();
//            }
//        });

        //partie 1.3.2
//        mValid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String birthDept = spinner.getSelectedItem().toString();
//
//                Intent pageWiki = new Intent(MainActivity.this, IntentWiki.class);
//                String info = lastname.getText().toString() + "\n" +
//                        firstname.getText().toString() + "\n" +
//                        birthday.getText().toString() + "\n" +
//                        birthcity.getText().toString() + "\n" +
//                        birthDept + "\n";
//
//                pageWiki.putExtra(EXTRA_MESSAGE, info);
//                startActivity(pageWiki);
//            }
//        });

        //partie 1.3.4
        mValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("ines", "beugre", "21 10 1988", "ABidjan");
                Intent intent = new Intent(MainActivity.this, ResultActivity.class );
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reset, menu);
        addItemOnSpinner();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
       switch (item.getItemId()) {
           case R.id.reset:
                reset();
                return true;
           case R.id.addNum:
               onAddField(view);
                return true;
           case R.id.wiki:
               goToWikipedia(view);
               return true;
           case R.id.displayinfo:
               displayInfo();
               return true;
           default:
            return super.onOptionsItemSelected(item);
       }
    }

    private void reset() {

        EditText number = (EditText) findViewById(R.id.number_edit_text);

        EditText field;
        int [] ids = new int [] {
                R.id.lastname,
                R.id.firstname,
                R.id.lastname,
                R.id.birthday,
                R.id.birthcity,
                R.id.number_edit_text,
//                R.id.dept_spinner,
        };

        for (int i = 0; i < ids.length; i++) {
            field = (EditText) findViewById(ids[i]);
            field.setText(null);
            //deptSprinner.clear();
        }
    }

    //Add number field on the view
    private void onAddField(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 2);
    }

    //Spinner on the view
    public void addItemOnSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dept_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    //Responding To User Selection
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(), "The departement is " +
                parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing.
    }


    //go to google page
    public void goToWikipedia(View view){
        String birthDept = spinner.getSelectedItem().toString();

       String google = "https://www.google.fr/search?q=" +birthDept;
       String wikipedia = "https://fr.wikipedia.org/wiki/" +birthDept;
//        https://www.google.fr/search?q=mars
//       Uri webAdress = Uri.parse(google);
        Uri webAdress = Uri.parse(wikipedia);

        Intent goToGoogle = new Intent(Intent.ACTION_VIEW, webAdress);
       if (goToGoogle.resolveActivity(getPackageManager()) != null) {
           startActivity(goToGoogle);
       }
    }

    public void displayInfo(){

        String birthDept = spinner.getSelectedItem().toString();

        Intent pageWiki = new Intent(MainActivity.this, IntentWiki.class);
        String info = lastname.getText().toString() + "\n" +
                         firstname.getText().toString() + "\n" +
                            birthday.getText().toString() + "\n" +
                            birthcity.getText().toString() + "\n" +
                             birthDept + "\n";

        pageWiki.putExtra(EXTRA_MESSAGE, info);
        startActivity(pageWiki);



    }


}

//    private void addNumber() {
//        LinearLayout numberLayout = new LinearLayout(this);
//
//        // Définition du composant Text.
//        TextView number = new TextView(this);
//        number.setText(String.format(getString(R.string.app_name), post.get));
//
//    }

//        String lname = lastname.getText().toString();
//        String fname = firstname.getText().toString();
//        String bday = birthday.getText().toString();
//        String bcity = birthcity.getText().toString();
//
//        List<String> myEditTexts = new ArrayList<>();
//        myEditTexts.add(lname);
//        myEditTexts.add(fname);
//        myEditTexts.add(bday);
//        myEditTexts.add(bcity);
