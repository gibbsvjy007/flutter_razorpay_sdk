# :credit_card: Razorpay Plugin for flutter

A Flutter plugin to integrate razorpay SDK for Android and iOS.

## Features

* [x] Android and iOS

  * [x] Credit Card, Netbanking Payment
  * [x] Wallet payment
  * [x] All supported payment options with your account

### Screenshots

<table>
  <tr>
     <td>
       <img src = "https://github.com/gibbsvjy007/flutter_razorpay_sdk/blob/master/1.PNG" height="350">
    </td>
    <td>
      <img src = "https://github.com/gibbsvjy007/flutter_razorpay_sdk/blob/master/2.PNG" height="350">
    </td>
    <td>
      <img src = "https://github.com/gibbsvjy007/flutter_razorpay_sdk/blob/master/3.PNG" height="350">
    </td>
  </tr>
 </table>

### Show some :heart: and star the repo to support the project

* Please note this is a plugin only. This plugin has used the Android SDK.
Note: This plugin is still under development. Feedback and Pull Requests are most welcome!


## Instaling
To use this plugin :

* add the dependency to your [pubspec.yaml](https://github.com/gibbsvjy007/flutter_razorpay_sdk) file.

Add this in pubspec.yaml
```
  flutter_razorpay_sdk: ^0.1.0
```

## Usage
To call payment procedure:

```
import 'package:flutter_razorpay_sdk/flutter_razorpay_sdk.dart';

Future<Null> _showNativeView() async {
    String API_KEY_HERE = "API_KEY_HERE";

    Map<String, dynamic> options = new Map();
    options.putIfAbsent("name", () => "Laptop");
    options.putIfAbsent("image", () => "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
    options.putIfAbsent("description", () => "Testing razorpay transaction");
    options.putIfAbsent("amount", () => "100");
    options.putIfAbsent("email", () => "test@gmail.com");
    options.putIfAbsent("contact", () => "+919825123456");

    options.putIfAbsent("theme", () => "#4D68FF");
    options.putIfAbsent("api_key", () => API_KEY_HERE);

    Map<dynamic,dynamic> paymentResponse = new Map();
    paymentResponse = await FlutterRazorpaySdk.openPaymentDialog(options);
    print("response $paymentResponse");
}

```
Response :
```
 {"code": 0, "message": "Payment Cancelled"}
```
or
```
 {"code": 1, "message": "pay_BcbdhwtCmNX4ck"}
```
If payment is sucessfull message will contain the payment_id from razorpay.


## Created & Maintained By

[Vijay Rathod](https://github.com/gibbsvjy007)

> If you found this plugin helpful and want to thank me, consider buying me a cup of :coffee:
>
> * [PayPal](https://www.paypal.me/gibbsvjy007)

# License

    Copyright 2018 seven-re.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
