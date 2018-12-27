import Flutter
import UIKit
import Razorpay

public class SwiftFlutterRazorpaySdkPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_razorpay_sdk", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterRazorpaySdkPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
