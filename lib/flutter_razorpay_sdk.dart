// Copyright 2018 The SevenRE Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'dart:async';

import 'package:flutter/services.dart';

// FlutterRazorpaySdk
class FlutterRazorpaySdk {
  static const MethodChannel _channel =
      const MethodChannel('flutter_razorpay_sdk');


  // open razorpay payment dialog. Theme and image keys are optional.
  // Please replace your test api key in order to test the plugin
  static Future<Map<dynamic, dynamic>>  openPaymentDialog(Map<dynamic, dynamic> map) async {
   Map<dynamic, dynamic> response = new Map();

   // call plugin method here
   response = await _channel.invokeMethod('startPayment', map);
   print(response.toString());
   return response;
  }
}
