package KotlinAssignment.Module_3_Android_Fundamental

// Module 3 Android Fundamental

// 1. What is R.java file.

// => R.java file is an automatically generated Java file that contains references to resources used in an Android project.
// These resources include layouts, strings, images, styles, fonts, and more. R.java files are also known as resource files.

// => Android R.java is an auto-generated file by aapt (Android Asset Packaging Tool) that contains resource IDs for all the
// resources of res/ directory.

// => If you create any component in the activity_main.xml file, id for the corresponding component is automatically created
// in this file. This id can be used in the activity source file to perform any action on the component.

// 2. What is activity and activity lifecycle.

// => In Android, an activity is a screen that lets the user interact with the app and displays the app's UI. The activity
// lifecycle is the process of an activity moving through different states, from its creation to its destruction.

// => The activity lifecycle is important for Android developers to understand because if activities don't respond correctly
// to state changes, the app can cause bugs, confusing behavior for users, or use too many system resources.

// 3. What is fragment and fragment lifecycle.

// => In Android, the fragment is the part of the Activity that represents a portion of the User Interface(UI) on the screen.
// It is the modular section of the Android activity that is very helpful in creating UI designs that are flexible in nature
// and auto-adjustable based on the device screen size.

// => Each fragment has its own lifecycle but due to the connection with the Activity it belongs to, the android fragment
// lifecycle is influenced by the activity’s lifecycle.

// 4. Activity to fragment and fragment to activity.