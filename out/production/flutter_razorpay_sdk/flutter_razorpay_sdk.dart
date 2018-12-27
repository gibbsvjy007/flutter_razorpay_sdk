import 'dart:async';

import 'package:flutter/services.dart';

class FlutterRazorpaySdk {
  static const MethodChannel _channel =
      const MethodChannel('flutter_razorpay_sdk');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> openPaymentDialog({String type}) async {
    final Map<String, dynamic> params = <String, dynamic> {
      'type': type,
    };
   final String success = await _channel.invokeMethod('startPayment', params);
   return success;
  }
}
