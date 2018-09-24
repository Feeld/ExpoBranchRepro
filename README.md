# Summary
The React Native app in this repository reproduces an issue when opening a Branch deep link on Android when the app is backgrounded.

# Setup
- Have yarn installed on the machine
- Open a terminal and execute the following commands:
```
git clone https://github.com/Feeld/ExpoBranchRepro 
cd ExpoBranchRepro
yarn
expo start --dev -c
```
- Connect a device to your PC or open an Android emulator
- Make sure you have Android SDK installed on your machine
- On another terminal tab execute the following commands:
``` 
export ANDROID_HOME=~/Library/Android
./android/gradlew uninstallDevMinSdkDevKernelDebug clean installDevMinSdkDevKernelDebug -p android
```

# Steps to reproduce
 
- Open the app from cold
- On the terminal with the Expo bundle server wait for the build to finish. After the build you will see:

```
Object {
  "error": null,
  "params": Object {
    "+clicked_branch_link": false,
    "+is_first_session": true,
    "+non_branch_link": "exp13f5502b39b149d8a02ab7ff5385ff98://",
    "cached_initial_event": true,
  },
  "uri": "exp13f5502b39b149d8a02ab7ff5385ff98://",
}
```


- Kill the app
- Open the link on your device browser https://feeld.github.io/ExpoBranchRepro/
- Click the test link. The app will open and you will see in the terminal with the Expo bundle server something similar to:

```
Object {
  "error": null,
  "params": Object {
    "$marketing_title": "testing",
    "$one_time_use": false,
    "+click_timestamp": 1537788372,
    "+clicked_branch_link": true,
    "+is_first_session": false,
    "+match_guaranteed": true,
    "cached_initial_event": true,
    "test-data": "1234",
    "~creation_source": 1,
    "~feature": "marketing",
    "~id": "572726388101131030",
    "~marketing": true,
    "~referring_link": "https://feeld-expo.test-app.link/testing",
  },
  "uri": "https://feeld-expo.test-app.link/testing",
}
```

- Background the app
- Open the device browser on https://feeld.github.io/ExpoBranchRepro/
- Click again on the test link
- The app will open and you will see in the terminal with the Expo bundle server:
```
Object {
  "error": null,
  "params": Object {
    "+clicked_branch_link": false,
    "+is_first_session": false,
  },
  "uri": null,
}
```

## Expected results
When opening a link with the app in the background the expected outcome is (only the `+click_timestamp` and `~id` will differ):
```
Object {
  "error": null,
  "params": Object {
    "$marketing_title": "testing",
    "$one_time_use": false,
    "+click_timestamp": 1537788372,
    "+clicked_branch_link": true,
    "+is_first_session": false,
    "+match_guaranteed": true,
    "cached_initial_event": true,
    "test-data": "1234",
    "~creation_source": 1,
    "~feature": "marketing",
    "~id": "572726388101131030",
    "~marketing": true,
    "~referring_link": "https://feeld-expo.test-app.link/testing",
  },
  "uri": "https://feeld-expo.test-app.link/testing",
}
```
But the actual outcome is:
```
Object {
  "error": null,
  "params": Object {
    "+clicked_branch_link": false,
    "+is_first_session": false,
  },
  "uri": null,
}
```