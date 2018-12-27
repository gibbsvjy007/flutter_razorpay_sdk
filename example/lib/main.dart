// Copyright 2018 The SevenRE Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter_razorpay_sdk/flutter_razorpay_sdk.dart';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  /// open razorpay payment dialog. Theme and image keys are optional.
  ///
  /// If this is not passed then it will grab the image url and theme color set in the razorpay dashboard
  /// Please replace your test api key in order to test the plugin
  Future<Null> _showNativeView() async {
    String apiKey = "rzp_test_p7XqWYIyoY4yYG";

    Map<String, dynamic> options = new Map();
    options.putIfAbsent("name", () => "Laptop");
    options.putIfAbsent("image", () => "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
    options.putIfAbsent("description", () => "Testing razorpay transaction");
    options.putIfAbsent("amount", () => "100");
    options.putIfAbsent("email", () => "test@gmail.com");
    options.putIfAbsent("contact", () => "+919825123456");

    options.putIfAbsent("theme", () => "#4D68FF");
    options.putIfAbsent("api_key", () => apiKey);

    Map<dynamic,dynamic> paymentResponse = new Map();
    paymentResponse = await FlutterRazorpaySdk.openPaymentDialog(options);
    print("response $paymentResponse");
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: const Text('Razorpay Plugin'),
        ),
        body: Center(child:
          Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
                  MaterialButton(
                  color: Colors.blue,
                  child: new Text("Pay with RazorPay", style: TextStyle(color: Colors.white)),
                  onPressed: _showNativeView)
            ],
          ),
        )

      ),
    );
  }
}
