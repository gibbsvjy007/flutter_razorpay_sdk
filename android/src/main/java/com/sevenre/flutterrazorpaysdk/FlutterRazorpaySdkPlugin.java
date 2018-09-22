package com.sevenre.flutterrazorpaysdk;

import android.util.Log;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import org.json.JSONObject;
import android.app.Activity;

/** FlutterRazorpaySdkPlugin */
public class FlutterRazorpaySdkPlugin implements MethodCallHandler, PaymentResultListener {
  private final String TAG = "MainActivity";
  private final Registrar registrar;

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_razorpay_sdk");
    channel.setMethodCallHandler(new FlutterRazorpaySdkPlugin(registrar));
  }

  private FlutterRazorpaySdkPlugin(Registrar registrar) {
    this.registrar = registrar;
  }
  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if(call.method.equals("startPayment")) {
      String paymentType = call.argument("type");
      Log.w("paymentType", paymentType);
      startPayment();
      result.success("Payment done");
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onPaymentSuccess(String razorpayPaymentID) {
    Log.i(TAG, "Payment Successful: " + razorpayPaymentID);
  }

  @Override
  public void onPaymentError(int code, String response) {
    Log.i(TAG, "Payment Error: " + response);
    Log.i(TAG, "Payment Error: " + code);
  }

  public void startPayment() {
    /**
     * Instantiate Checkout
     */
    Checkout checkout = new Checkout();

    /**
     * Set your logo here
     */
//    checkout.setImage(R.drawable.common_google_signin_btn_icon_dark);

    /**
     * Reference to current activity
     */
    final Activity activity = registrar.activity();

    /**
     * Pass your payment options to the Razorpay Checkout as a JSONObject
     */
    try {
      JSONObject options = new JSONObject();

      /**
       * Merchant Name
       * eg: Rentomojo || HasGeek etc.
       */
      options.put("name", "SevenRE UG");

      /**
       * Description can be anything
       * eg: Order #123123
       *     Invoice Payment
       *     etc.
       */
      options.put("description", "Order #123456");

      options.put("currency", "INR");

      /**
       * Amount is always passed in PAISE
       * Eg: "500" = Rs 5.00
       */
      options.put("amount", "500");

      checkout.open(activity, options);
    } catch (Exception e) {
      Log.e(TAG, "Error in starting Razorpay Checkout", e);
    }
  }
}
