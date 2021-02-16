# recommendation-track-api
This API recommends music tracks based on the weather in a city.

Resolution to the ifood challenge (https://github.com/ifood/vemproifood-backend).

This project uses Spring Boot that provides a Rest Endpoint to get a list of tracks on Spotify based on the temperature in any city.

To Control execution of external integration the project make use of Java Future API and Completable execution.

## Execution
To execute locally you can execute the above command on command line


./gradlew bootRun`

The docker image is generated like it:

`sudo docker build -t recommendation-track-api:1.0

After build the image, so we could execute the another command:


`sudo docker run -d -p 8080:8080 -t recommendation-track-api:1.0
 

## API Endpoint

/track

Request
curl -X GET "localhost:8080/track?city=oslo"

OR

curl -X GET "localhost:8080/track?lat=151.2073&lat=-33.8679"

Response

```
[
   {
      "name":"Piano Concerto No. 21 in C Major, K. 467 \"Elvira Madigan\": II. Andante"
   },
   {
      "name":"Requiem, K. 626: Lacrimosa"
   },
   {
      "name":"Fantasia in D Minor, K.397"
   },
   {
      "name":"Symphony No.25 in G minor, K.183: 1. Allegro con brio"
   },
   {
      "name":"Piano Sonata No. 16 in C Major, K. 545 \"Sonata facile\": 1. Allegro"
   },
   {
      "name":"Adagio in E Major, K. 261"
   },
   {
      "name":"Serenade No. 13 in G Major, K. 525 \"Eine kleine Nachtmusik\": I. Allegro"
   },
   {
      "name":"Symphony No. 40 in G Minor, K. 550: I. Molto allegro"
   },
   {
      "name":"Mozart: Clarinet Concerto in A Major, K. 622: II. Adagio"
   },
   {
      "name":"Serenade in B-Flat Major, K. 361 \"Gran Partita\": III. Adagio"
   }
]
``
