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


1.AndroidManifet.xml里面配置@Dao @Controller @Service注解的包名

 <meta-data android:name="component-scan" android:value="xx.xx.xx.xx.service,xx.xx.xx.xx.controller,xx.xx.xx.xx.dao" />


2.继承AAndroidMvcApplication

3.使用 AndroidSpringMvc.inject(this)注入依赖

4.混淆配置


-keep class com.bitmain.hale.springmvc.controller.** { @com.bitmain.hale.springmvc.di.Autowired <fields>;}
-keep class com.bitmain.hale.springmvc.dao.** { @com.bitmain.hale.springmvc.di.Autowired <fields>; }
-keep class com.bitmain.hale.springmvc.service.** { @com.bitmain.hale.springmvc.di.Autowired <fields>; }
-keep interface com.bitmain.hale.springmvc.** { *; }
