# product_ecommerce

Setup data model using the sql/ folder scripts.
I have used oracle db config in the application.properties in the project.

Oracle settings

```
 spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
 spring.datasource.username=
 spring.datasource.password=
 spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

```

Checkout and further compile the project using 

```
gradle build --console=plain copyJar
```

Further, 4 services to be executed post compilation, in separate command lines.

```
 java -jar jar\product-ecommerce.jar eureka
 java -jar jar\product-ecommerce.jar customer
 java -jar jar\product-ecommerce.jar admin 
 java -jar jar\product-ecommerce.jar web 

```

Port for customer and admin as below 

http://localhost:2222/api/placeOrder/1

payload for this as below 

```json
{
    "orderedProducts": [
        {
            "productId": 2,
            "orderCount": 2
        },
        {
            "productId": 3,
            "orderCount": 1
        },
        {
            "productId": 5,
            "orderCount": 1
        }
    ]
}
```

http://localhost:3333/api/approveOrder/1

no pay load needed.
