# Louis
1st prize at Kraków Miasto Kreatywne 2016

<p>
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_1.png" height="350">
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_2.png" height="350">
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_3_5.png" height="350">
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_4_5.png" height="350">
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_3.png" height="350">
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_4.png" height="350">
<img src="https://github.com/Marchuck/Louis/blob/master/app/src/main/res/drawable/screen_5.png" height="350">
</p>

App uses Microsoft Cognitive Services in order to get mood from taken selfie

App tries to filter as much as possible events nearby, customize user's culture needs.

Do not hestitate to leave  :star:  :star:  :star:

longest RX-JAVA chain created in this project, below:

```
Common.uriToDrawable(ctx, selectedImage) 
                    .flatMap(Common::drawableToBitmap)  
                    .flatMap(Common::scaleBitmap) 
                    .flatMap(Common::rotatedByAllAngles)                     
                    .flatMap(bitmap -> EmotionRestClient.getInstance().detectAsync(bitmap))
                    .filter(response -> response != null && response.length > 0)
                    .subscribeOn(Schedulers.newThread())
                    .timeout(8, TimeUnit.SECONDS)
                    .take(1)
                    .onErrorReturn(throwable -> ...)
                    .subscribe(faceAnalysises -> ...)
