import 'dart:async';

import 'package:flutter/services.dart';

class FlutterRazorpaySdk {
  static const MethodChannel _channel =
      const MethodChannel('flutter_razorpay_sdk');

  static Future<Map<dynamic, dynamic>>  openPaymentDialog(Map<dynamic, dynamic> map) async {
   Map<dynamic, dynamic> response = new Map();
   response = await _channel.invokeMethod('startPayment', map);
   print(response.toString());
   return response;
  }
}
