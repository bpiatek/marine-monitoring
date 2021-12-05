# marine monitoring
##### Cloning application
```shell
# go to desired location and run
git clone https://github.com/bpiatek/marine-monitoring.git
```
##### Running with docker-compose from terminal
```shell
# enter main application folder
cd marine-monitoring
# build with maven
mvn package
# run detached
docker-compose up --build -d
# to stop 
docker-compose stop
```
Application together with PosgreSQL database will start.  
Open Chrome browser and go to:
```shell
http://localhost:8080
```
By default, map will show Trondheim area and vessels nearby.
Additionally there are some weather information provided for specified city as well - temperature, atmospheric pressure and cloudiness.

![alt text](https://i.ibb.co/KGF35Wz/Zrzut-ekranu-2021-12-5-o-17-45-19.png)

It seems like API (barentswatch.no) proposed on the stream is providing information only about Norwegian coastal nad marine areas. 

![alt text](https://i.ibb.co/ChYK2tr/Zrzut-ekranu-2021-12-5-o-18-15-28.png)  
That means you would have to limit your searches to those cities.  
Some examples: Kristiansand, Stavanger, Oslo, Tromso.  
Enter city name in search box in the upper right corner and click SEARCH: 

![alt text](https://i.ibb.co/4VS7NM3/Zrzut-ekranu-2021-12-5-o-18-08-00.png)

It is of course possible to search for any city. The map will show that location and weather details will be displayed.
