package com.sevenre.flutterrazorpaysdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RazorpayActivity extends Activity implements PaymentResultListener {
    private static final String TAG = RazorpayActivity.class.getSimpleName();
    public static String NAME = "name";
    public static String IMAGE = "image";
    public static String DESCRIPTION = "description";
    public static String AMOUNT = "amount";
    public static String PREFILL = "prefill";
    public static String PREFILL_EMAIL = "email";
    public static String PREFILL_CONTACT = "contact";
    public static String THEME = "theme";
    public static String PAYMENT_ID = "payment_id";
    public static String RAZORPAY_KEY = "api_key";
    public static String CURRENCY = "currency";
    public static String COLOR = "color";
    public static String NOTES = "notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);
        Intent intent = getIntent();
        Checkout.preload(getApplicationContext());
        startPayment(intent);
    }


    /**
     * Start Razorpay payment
     *
     * @param intent
     */
    @SuppressWarnings("unchecked")
    public void startPayment(Intent intent) {
        final Activity activity = this;

        final Checkout _checkout = new Checkout();
        _checkout.setKeyID(intent.getStringExtra(RAZORPAY_KEY));

        try {
            JSONObject options = new JSONObject();
            options.put(NAME, intent.getStringExtra(NAME));
            options.put(DESCRIPTION, intent.getStringExtra(DESCRIPTION));
            // image is optional. if not specified then fetch the image from dashboard
            if (intent.getStringExtra(IMAGE) != null)
                options.put(IMAGE, intent.getStringExtra(IMAGE));
            options.put(CURRENCY, intent.getStringExtra(CURRENCY));
            options.put(AMOUNT, intent.getStringExtra(AMOUNT));

            // configure theme color
            JSONObject color = new JSONObject();
            color.put(COLOR, intent.getStringExtra(THEME));
            if (intent.getStringExtra(THEME) != null)
                options.put(THEME, color);

            // support for additional notes. maximum 15 keys are allowed
            if (intent.getSerializableExtra(NOTES) != null) {
                JSONObject notes = new JSONObject();
                Serializable noteObj = intent.getSerializableExtra(NOTES);
                HashMap<String, String> noteHash = (HashMap<String, String>) noteObj;
                for (Map.Entry<String, String> entry : noteHash.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    notes.put(key, value);
                }
                options.put(NOTES, notes);
            }

            // configure contact details
            JSONObject preFill = new JSONObject();
            preFill.put(PREFILL_EMAIL, intent.getStringExtra(PREFILL_EMAIL));
            preFill.put(PREFILL_CONTACT, intent.getStringExtra(PREFILL_CONTACT));

            options.put(PREFILL, preFill);

            // open rezorpay dialog
            _checkout.open(activity, options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
            Toast.makeText(activity, "Error while processing payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Log.i(TAG, "Payment Successful: " + razorpayPaymentID);

            Intent data = new Intent();
            data.putExtra(PAYMENT_ID, razorpayPaymentID);
            setResult(Activity.RESULT_OK, data);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Error in onPaymentSuccess", e);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Log.i(TAG, "onPaymentError Error: " + response);
            Log.i(TAG, "onPaymentError Error: " + code);

            Intent data = new Intent();
            data.putExtra(PAYMENT_ID, response);
            setResult(Activity.RESULT_CANCELED, data);
            Log.i(TAG, "onPaymentError Error: " + response);
            finish();
        } catch (Exception e) {
            Log.e(TAG, "Error in onPaymentError", e);
        }
    }
}
