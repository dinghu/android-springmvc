# android-springmvc
android use spring mvc, make your code better and clean.


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
	dependencies {
	        implementation 'com.github.dinghu:android-springmvc:1.0.0'
	}



继承AndroidSpringMvcApplication

使用 AndroidSpringMvc.inject(this)注入依赖
