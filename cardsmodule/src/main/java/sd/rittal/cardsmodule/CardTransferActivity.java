package sd.rittal.cardsmodule;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sd.rittal.cardsmodule.api.ApiBuilder;
import sd.rittal.cardsmodule.beans.CardTransferRequest;
import sd.rittal.cardsmodule.beans.CardTransferResponse;
import sd.rittal.cardsmodule.beans.CheckOutIDRequest;
import sd.rittal.cardsmodule.beans.CheckOutIDResponse;
import sd.rittal.cardsmodule.beans.PaymentInfoRequest;
import sd.rittal.cardsmodule.beans.PaymentInfoResponse;
import sd.rittal.cardsmodule.beans.PinPublicKeyRequest;
import sd.rittal.cardsmodule.beans.PinPublicKeyResponse;
import sd.rittal.cardsmodule.constant.AppConstant;
import sd.rittal.cardsmodule.constant.RSAEncrypt;
import sd.rittal.cardsmodule.uitl.DateSplite;
import sd.rittal.cardsmodule.uitl.MonthYearPickerFragment;
import sd.rittal.cardsmodule.uitl.ProgressATM;

public class CardTransferActivity extends AppCompatActivity {


    ProgressATM progressBar;
    Button buttonSubmit;

    EditText editTextFromPan, editTextExpireDate,
            editTextIpin;
    TextView textAmount, textToCard;

    String orgUsrName, orgPass, appName, baseUrl, toCard, passedAmount;
    String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_transfer);


        progressBar = new ProgressATM(CardTransferActivity.this);


        buttonSubmit = findViewById(R.id.buttonSubmit);
        editTextFromPan = findViewById(R.id.editText_from_pan);
        editTextExpireDate = findViewById(R.id.editText_expire_date);

        editTextIpin = findViewById(R.id.editText_ipin);
        textAmount = findViewById(R.id.txt_amount);
        textToCard = findViewById(R.id.to_card);

        Intent intent = getIntent();

        if (intent != null) {

            if (!intent.getStringExtra("baseUrl").isEmpty()) {

                orgUsrName = intent.getStringExtra("orgUsrName");
                orgPass = intent.getStringExtra("orgPass");
                appName = intent.getStringExtra("appName");

                baseUrl = intent.getStringExtra("baseUrl");
                passedAmount = intent.getStringExtra("amount");
                toCard = intent.getStringExtra("toPan");

                if (!baseUrl.endsWith("/")) {
                    baseUrl = baseUrl + "/";
                }

                checkOutIDRequest();

            } else {
                Toast.makeText(CardTransferActivity.this, "Please provide the Base Url", Toast.LENGTH_LONG).show();

                new Handler().postDelayed(CardTransferActivity.this::finish, 5000);
            }


        }

        handelUI();


    }

    private void handelUI() {

        editTextExpireDate.setOnClickListener(vvv -> {
            showExpireDateDialogFragment();
        });


        buttonSubmit.setOnClickListener(vv -> {
            if (checkFields()) {
                getPublicKey();
            }
        });


    }

    private void doCardTransfer(String pubKeyValue) {
        String uuid = getUUID();

        String ipin = new RSAEncrypt(editTextIpin.getText().toString()).rsa_EBS_encrypt(uuid, pubKeyValue).replace("\n", "").replace("\r", "");
        ;

        CardTransferRequest request = new CardTransferRequest(toCard,
                appName, getMd5(orgPass), AppConstant.authenticationType,
                amount,
                editTextFromPan.getText().toString(),
                uuid, editTextExpireDate.getText().toString(),
                ipin, orgUsrName);


        ApiBuilder.callAPI(baseUrl).cardTransfer(baseUrl + AppConstant.CardTransfer, request).enqueue(new Callback<CardTransferResponse>() {
            @Override
            public void onResponse(@NotNull Call<CardTransferResponse> call, @NotNull Response<CardTransferResponse> response) {
                editTextIpin.getText().clear();
                if (response.isSuccessful()) {
                    CardTransferResponse transferResponse = response.body();
                    if (Objects.requireNonNull(transferResponse).getResponseCode() == 0) {
                        progressBar.closeATM();
                        openResultUI(transferResponse);
                    } else {
                        progressBar.closeATM();
                        showDialogError(transferResponse.getResponseMessage());
                    }

                } else {
                    progressBar.closeATM();
                    Toast.makeText(CardTransferActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<CardTransferResponse> call, @NotNull Throwable t) {
                editTextIpin.getText().clear();
                progressBar.closeATM();
                Toast.makeText(CardTransferActivity.this, getString(R.string.went_error), Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });
    }


    private void getPublicKey() {

        progressBar.startATM();


        PinPublicKeyRequest request = new PinPublicKeyRequest(orgUsrName, appName, getMd5(orgPass));

        ApiBuilder.callAPI(baseUrl).getPublicKey(baseUrl + AppConstant.getPublicKey, request).enqueue(new Callback<PinPublicKeyResponse>() {
            @Override
            public void onResponse(@NotNull Call<PinPublicKeyResponse> call, @NotNull Response<PinPublicKeyResponse> response) {

                if (response.isSuccessful()) {
                    PinPublicKeyResponse publicKeyResponse = response.body();
                    if (publicKeyResponse.getResponseCode().equals("0")) {
                        doCardTransfer(publicKeyResponse.getPubKeyValue());
                    } else {
                        progressBar.closeATM();
                        showDialogError(publicKeyResponse.getResponseMessage());
                    }

                } else {
                    progressBar.closeATM();
                    Toast.makeText(CardTransferActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<PinPublicKeyResponse> call, @NotNull Throwable t) {
                editTextIpin.getText().clear();
                progressBar.closeATM();
                Log.wtf("onFailure", t.getMessage());
                Toast.makeText(CardTransferActivity.this, getString(R.string.went_error), Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });
    }

    private void openResultUI(CardTransferResponse json) {

        Intent intent = null;
        try {
            intent = new Intent(this,
                    Class.forName("sd.rittal.cardsmodule.CardTransferResultActivity"));
            intent.putExtra("cardResult", json);

            startActivity(intent);
            finish();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showDialogError(String error) {

        Dialog dialog = new Dialog(CardTransferActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_error);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        TextView txt_msg = dialog.findViewById(R.id.txt_result_content_r);

        txt_msg.setText(error);

        Button button_done = dialog.findViewById(R.id.dialog_button_error);


        button_done.setOnClickListener(ppp -> {
            dialog.dismiss();
        });

    }

    private void showExpireDateDialogFragment() {
        MonthYearPickerFragment pickerFragment = new MonthYearPickerFragment();
        pickerFragment.setListener((datePicker, year, month, i2) -> {

            String monthYearStr = year + "/" + month + "/" + i2;
            editTextExpireDate.setText(new DateSplite().expDate(monthYearStr));

        });

        pickerFragment.show(getSupportFragmentManager(), "MonthYearPickerDialog");
    }

    private boolean checkFields() {

        if (editTextFromPan.getText().toString().isEmpty() || editTextFromPan.getText().toString().length() < 16) {
            Toast.makeText(this, getString(R.string.pan_error), Toast.LENGTH_LONG).show();
            return false;
        }

        if (editTextExpireDate.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.expire_error), Toast.LENGTH_LONG).show();
            return false;
        }


        if (editTextIpin.getText().toString().isEmpty() || editTextIpin.getText().toString().length() < 4) {
            Toast.makeText(this, getString(R.string.ipin_erro), Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    public String getUUID() {
        return UUID.randomUUID().toString();
    }


    private String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private void getPaymentInfo(String checkOutID) {

        progressBar.startATM();


        PaymentInfoRequest request = new PaymentInfoRequest(orgUsrName, appName, getMd5(orgPass), checkOutID);

        ApiBuilder.callAPI(baseUrl).getPaymentInfo(baseUrl + AppConstant.getPaymentInfo, request).enqueue(new Callback<PaymentInfoResponse>() {
            @Override
            public void onResponse(@NotNull Call<PaymentInfoResponse> call, @NotNull Response<PaymentInfoResponse> response) {

                if (response.isSuccessful()) {
                    PaymentInfoResponse paymentInfoResponse = response.body();
                    if (paymentInfoResponse.getResponseCode().equals("0")) {
                        toCard = paymentInfoResponse.getReturnedURL();
                        amount = paymentInfoResponse.getAmount();
                        textAmount.setText(String.format(Locale.ENGLISH, getString(R.string.currency), Double.parseDouble(paymentInfoResponse.getAmount())));
                        textToCard.setText(paymentInfoResponse.getAppName());
                        buttonSubmit.setVisibility(View.VISIBLE);
                        progressBar.closeATM();

                    } else {
                        progressBar.closeATM();
                        showDialogError(paymentInfoResponse.getResponseMessage());
                    }

                } else {
                    progressBar.closeATM();
                    Toast.makeText(CardTransferActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<PaymentInfoResponse> call, @NotNull Throwable t) {

                progressBar.closeATM();
                Log.wtf("onFailure", t.getMessage());
                Toast.makeText(CardTransferActivity.this, getString(R.string.went_error), Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });
    }


    private void checkOutIDRequest() {

        progressBar.startATM();


        CheckOutIDRequest request = new CheckOutIDRequest(orgUsrName, appName, getMd5(orgPass), passedAmount, toCard);

        ApiBuilder.callAPI(baseUrl).checkOutIDRequest(baseUrl + AppConstant.CheckOutIDRequest, request).enqueue(new Callback<CheckOutIDResponse>() {
            @Override
            public void onResponse(@NotNull Call<CheckOutIDResponse> call, @NotNull Response<CheckOutIDResponse> response) {

                if (response.isSuccessful()) {
                    CheckOutIDResponse paymentInfoResponse = response.body();
                    if (paymentInfoResponse.getResponseCode().equals("0")) {

                        getPaymentInfo(paymentInfoResponse.getCheckOutID());

                    } else {
                        progressBar.closeATM();
                        showDialogError(paymentInfoResponse.getResponseMessage());
                        new Handler().postDelayed(CardTransferActivity.this::finish, 5000);
                    }

                } else {
                    progressBar.closeATM();
                    Toast.makeText(CardTransferActivity.this, getString(R.string.server_error), Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(CardTransferActivity.this::finish, 5000);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CheckOutIDResponse> call, @NotNull Throwable t) {

                progressBar.closeATM();
                Log.wtf("onFailure", t.getMessage());
                Toast.makeText(CardTransferActivity.this, getString(R.string.went_error), Toast.LENGTH_LONG).show();
                t.printStackTrace();
                new Handler().postDelayed(CardTransferActivity.this::finish, 5000);

            }
        });
    }
}