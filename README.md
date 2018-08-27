# android-springmvc
android use spring mvc, make your code better and clean.


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
	dependencies {
	        implementation 'com.github.dinghu:android-springmvc:1.0.5'
	}



继承AndroidSpringMvcApplication

使用 AndroidSpringMvc.inject(this)注入依赖

混淆配置


-keep class com.bitmain.hale.springmvc.controller.** { @com.bitmain.hale.springmvc.di.Autowired <fields>;}
-keep class com.bitmain.hale.springmvc.dao.** { @com.bitmain.hale.springmvc.di.Autowired <fields>; }
-keep class com.bitmain.hale.springmvc.service.** { @com.bitmain.hale.springmvc.di.Autowired <fields>; }
-keep interface com.bitmain.hale.springmvc.** { *; }
