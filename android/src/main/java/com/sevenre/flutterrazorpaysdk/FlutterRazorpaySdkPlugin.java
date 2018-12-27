package com.sevenre.flutterrazorpaysdk;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterRazorpaySdkPlugin
 */
public class FlutterRazorpaySdkPlugin implements MethodCallHandler, PluginRegistry.ActivityResultListener {
    private Activity activity;
    private Result paymentResult;
    private Map<String, Object> arguments;
    private final MethodChannel channel;
    private int PAY_WITH_RAZORPAY = 100;
    private static String PLUGIN_NAME = "flutter_razorpay_sdk";

    private FlutterRazorpaySdkPlugin(Activity activity, MethodChannel channel) {
        this.activity = activity;
        this.channel = channel;
        this.channel.setMethodCallHandler(this);
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), PLUGIN_NAME);
        FlutterRazorpaySdkPlugin _pluginInstance = new FlutterRazorpaySdkPlugin(registrar.activity(), channel);
        channel.setMethodCallHandler(_pluginInstance);
        registrar.addActivityResultListener(_pluginInstance);
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onMethodCall(MethodCall call, Result result) {
        paymentResult = result;
        if (call.method.equals("startPayment")) {
            arguments = (Map<String, Object>) call.arguments;
            Intent razorpayIntent = new Intent(activity, RazorpayActivity.class);
            razorpayIntent.putExtra(RazorpayActivity.NAME, (String) arguments.get("name"));

            if (arguments.get("image") != null)
                razorpayIntent.putExtra(RazorpayActivity.IMAGE, (String) arguments.get("image"));

            razorpayIntent.putExtra(RazorpayActivity.DESCRIPTION, (String) arguments.get("description"));
            razorpayIntent.putExtra(RazorpayActivity.AMOUNT, (String) arguments.get("amount"));
            razorpayIntent.putExtra(RazorpayActivity.CURRENCY, (String) arguments.get("currency"));
            razorpayIntent.putExtra(RazorpayActivity.PREFILL_EMAIL, (String) arguments.get("email"));

            if (arguments.get("theme") != null)
                razorpayIntent.putExtra(RazorpayActivity.THEME, (String) arguments.get("theme"));

            razorpayIntent.putExtra(RazorpayActivity.PREFILL_CONTACT, (String) arguments.get("contact"));
            razorpayIntent.putExtra(RazorpayActivity.RAZORPAY_KEY, (String) arguments.get("api_key"));
            activity.startActivityForResult(razorpayIntent, PAY_WITH_RAZORPAY);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PAY_WITH_RAZORPAY) {
            HashMap<String, String> data = new HashMap<>();
            String response = intent.getStringExtra(RazorpayActivity.PAYMENT_ID);
            String CODE = "code";
            String MESSAGE = "message";
            switch (resultCode) {
                case Activity.RESULT_OK:
                    //Payment was successful
                    data.put(CODE, "1");
                    data.put(MESSAGE, response);
                    Toast.makeText(this.activity, "Payment was successful", Toast.LENGTH_SHORT).show();
                    paymentResult.success(data);
                    break;

                case Activity.RESULT_CANCELED:
                    //Payment failed
                    data.put(CODE, "0");
                    data.put(MESSAGE, response);
                    Toast.makeText(this.activity, "Payment Cancelled", Toast.LENGTH_SHORT).show();
                    paymentResult.success(data);
                    break;
            }
            paymentResult.error("PAYMENT_FAILURE", "Failed while launching activity", null);
            paymentResult = null;
            arguments = null;
            return true;
        }
        return false;
    }
}
