package sd.rittal.cardsmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import sd.rittal.cardsmodule.beans.CardTransferResponse;

public class CardTransferResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_transfer_result);


        TextView response_pan = findViewById(R.id.txt_response_pan);
        TextView date_time = findViewById(R.id.date_time);
        TextView response_fee = findViewById(R.id.txt_response_fee);
        TextView response_amount = findViewById(R.id.txt_response_amount);
        TextView response_code = findViewById(R.id.txt_response_code);
        TextView response_msg = findViewById(R.id.txt_response_msg);
         Button button_done = findViewById(R.id.dialog_button_done);
        Button button_exit = findViewById(R.id.dialog_button_exit);


        Intent intent = getIntent();
        if (intent != null) {
            CardTransferResponse response = (CardTransferResponse) intent.getSerializableExtra("cardResult");
            date_time.setText(String.format(Locale.ENGLISH, setDateTime(response.getTranDateTime()).toString()));
            response_pan.setText(String.format(Locale.ENGLISH, response.getPAN()));
             Double amount = response.getTranAmount();

            double totalFee = Math.abs(response.getAcqTranFee()) + Math.abs(response.getIssuerTranFee());
            response_amount.setText(String.format(getString(R.string.currency), amount));
            response_fee.setText(String.format(getString(R.string.currency), totalFee));

            response_msg.setText(response.getResponseMessage());
            response_code.setText(String.valueOf(response.getResponseCode()));

        }

        button_done.setOnClickListener(view -> share());
        button_exit.setOnClickListener(vv -> {
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private String setDateTime(String tranDateTime) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");

        Date date = null;
        try {
            date = dateFormat.parse(String.valueOf(tranDateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Date : " + date);

        SimpleDateFormat dateFormatNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String formattedDate = dateFormatNew.format(date);

        return formattedDate;
    }

    public void share() {

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        LinearLayout linearLayout = findViewById(R.id.layout_main_view);
        Bitmap bitmap = getBitmapFromView(linearLayout);
        try {
            File file = new File(getExternalCacheDir(), "CardsTrans.jpeg");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.putExtra(Intent.EXTRA_TITLE, getString(R.string.qr_purchase));
            intent.setType("image/jpeg");
            startActivity(Intent.createChooser(intent, "Share via"));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}