#import "FlutterRazorpaySdkPlugin.h"
#import <flutter_razorpay_sdk/flutter_razorpay_sdk-Swift.h>

@implementation FlutterRazorpaySdkPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterRazorpaySdkPlugin registerWithRegistrar:registrar];
}
@end
