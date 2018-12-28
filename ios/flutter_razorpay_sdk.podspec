#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'flutter_razorpay_sdk'
  s.version          = '0.0.1'
  s.summary          = 'A Flutter plugin to integrate razorpay SDK'
  s.description      = <<-DESC
A Flutter plugin to integrate razorpay SDK
                       DESC
  s.homepage         = 'https://github.com/gibbsvjy007/flutter_razorpay_sdk.git'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Vijay Rathod' => 'vijay.rathod@seven-re.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.dependency 'razorpay-pod','~> 1.0.26'

  s.ios.deployment_target = '10.0'
end

