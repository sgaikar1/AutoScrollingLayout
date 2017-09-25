# AutoScrollingLayout


The goal of this library is to help you in your development of User Interface.
This library will give you scrolling background effect as shown in the below image

##Preview

![gif](https://github.com/sgaikar1/AutoScrollingLayout/blob/master/screen/screen1.gif)



### Installation

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
	        compile 'com.github.sgaikar1:AutoScrollingLayout:2.0.0'
	}

```

### Usage

* For implementation please refer to the sample app.
 ## Attributes
 
 | Name        | Description           | Value  |
 | ------------- |:-------------:| -----:|
 | img_src     | drawable resource for background     | drawable reference|
 | scroll_speed| scrolling speed of background      |   float |
 | tint_alpha | Tint alpha for foreground of scrolling layout      |   float|
 | tint_color | Tint color for foreground of scrolling layout      |   color reference|

 ## XML
 ```xml
 <com.sgaikar1.autoscrollinglayout.AutoScrollingLayout
         android:id="@+id/scrolling_layout"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         app:img_src="@drawable/longimage1"
         app:scroll_speed="500"
         app:tint_alpha="0.9"
         app:tint_color="#444777">
         
         //Use your code here
         
         </com.sgaikar1.autoscrollinglayout.AutoScrollingLayout>
 ```
 
 #### Inside Activity or Fragment:
 ```
 AutoScrollingLayout autoScrollingLayout =(AutoScrollingLayout)findViewById(R.id.scrolling_layout);
         autoScrollingLayout.setBackgroundSrc(ContextCompat.getDrawable(this,R.drawable.longimage1));
         autoScrollingLayout .setSpeed(500f);
         autoScrollingLayout .setBackgroundAlpha(0.5f);
         autoScrollingLayout .setTintColor(Color.parseColor("#E658AA"));
 ```
 
 ## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2017 Santosh Gaikar....

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
