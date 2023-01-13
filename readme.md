# Library Data
## Review
This is an online library, a CRUD (Create, Read, Update, Delete) application 
where users can add, edit, and delete person cards and books. 
They can also assign books to specific individuals. This application is deployed 
on an Amazon Web Services (AWS) Elastic Compute Cloud (EC2) and can be accessed 
through the provided [link](http://mylibrary-env.eba-6fniism4.us-east-1.elasticbeanstalk.com). 
It provides a user-friendly 
and efficient way to manage and organize library resources.


### Technology stack
* Java 17
* Spring Boot
* Maven
* Lombok
* Thymeleaf
* Hibernate validator
* Model mapper
* Postgresql
* Log4j

## How to run
Use this [link](http://mylibrary-env.eba-6fniism4.us-east-1.elasticbeanstalk.com): 

## Endpoints
<font color='#fa8072'>Sensor: /sensors - general path

* <font color='#5f9ea0'> POST /sensors/registration (adding sensor) 
</font>

If placed on port 7070, the request will look like:
**localhost:7070/sensors/registration**

Json example:
{
"name" : "SensorLA"
}

* <font color='#5f9ea0'> DELETE /registration/{name}</font>

  example: /registration/SensorLA (it will delete sensor by name)

<font color='#fa8072'>Measurements: /measurements - general path</font>

If placed on port 7070, the request will look like:
**localhost:7070/measurements**

* <font color='#5f9ea0'>POST /measurements/add (adding measurements to a sensor by name)</font>

Json example: {
"value" : 12,
"raining" : true,
"sensor": {
"name" : "SensorLA"
}
}


* <font color='#5f9ea0'>GET /measurements</font>
  localhost:7070/measurements

List all measurements

* <font color='#5f9ea0'>GET /measurements/rainyDaysCount</font>

Count rainy days






