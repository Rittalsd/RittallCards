package sd.rittal.rittallcards;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import sd.rittal.cardsmodule.uitl.RittalService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RittalService.checkOutIDRequest(getApplicationContext(), "orgUsrName",
                "orgPass", "appName",
                "amount","toPan",
                "baseUrl");


    }


}