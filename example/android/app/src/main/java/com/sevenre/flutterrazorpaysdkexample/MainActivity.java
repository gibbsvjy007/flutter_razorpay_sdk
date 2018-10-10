package com.sevenre.flutterrazorpaysdkexample;

import android.os.Bundle;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;
import com.razorpay.PaymentResultListener;

public class MainActivity extends FlutterActivity implements PaymentResultListener {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
  }
    
  public void onPaymentSuccess(String razorpayPaymentID) {
    Log.i(TAG, "Payment Successful: " + razorpayPaymentID);
  }

  public void onPaymentError(int code, String response) {
    Log.i(TAG, "Payment Error: " + response);
    Log.i(TAG, "Payment Error: " + code);
  }
}
