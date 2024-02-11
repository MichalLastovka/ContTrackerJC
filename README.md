# ContTracker

ContTracker is mobile application for Android operating system designed to help tracking containers under HHLA logistic company based in Germany.

### Features:

Add new container for tracking:

![Screenshot from 2024-02-11 12-11-38](https://github.com/MichalLastovka/ContTrackerJC/assets/112790340/02f55696-df97-4a2b-89bc-0e168654a2d9)

Get details about container, career, terminal and ship:

![Screenshot from 2024-02-11 13-09-06](https://github.com/MichalLastovka/ContTrackerJC/assets/112790340/cb5f4d2a-a75c-4fa6-9c6a-6b95e919b151)

Turn on notification to stay updated even when app is closed, update the data instantly or delete record from your database:

![ScreenCapture_select-area_20240211140438-ezgif com-resize](https://github.com/MichalLastovka/ContTrackerJC/assets/112790340/0e8cffa2-82b2-4ae5-b54d-864ae99345f5)

### How it works?

By submiting valid container tracking number, application caches available information through Retrofit API call into local database in Room. Once notification is turned on, application schedules regular 15-minutes calls for new data on server. In the moment app finds the difference between cached data and new data brought from server, it notifies user.

### Contributions 

#### Icons
Big thanks to <a href="https://www.flaticon.com/free-icons/container" title="container icons">Container icons created by Iconjam - Flaticon</a> for providing free icons.

### Under the hood

#### Presentation & navigation
The ContTracker main screen consist of scaffold with top and bottom AppBar, LazyColumn containing ContainerItem composable and FloatableActionButton triggering Dialog. By clicking on ContainerItem navigation generates ContainerScreen with additional infographics.

#### Remote & local data handling
Data about the container are brought from HHLA API (no key, no authorization needed) in JSON with Retrofit2 library based on container ID (or number). App parses JSON using GSON library into Container data class, which contains only selected data. Data are stored in the Room database with exception of "notifyOn" data field (only local, ensures the notification worker), "note" (only local, provides the option to add note) and UUID (only serves for notification worker labeling)

#### Background worker and notification
After notification is turned on, the app sends request through HHLA API and schedules a new one within 15 minutes period. In case of differences between cached and brought data field with status, it triggers the notification. Background worker is not interupted by closing app nor rebooting. However making phone off-line causes the worker to fail and be rescheduled.
