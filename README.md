# Restaurant App - Spring Boot + MySQL RDS

## Requirements
- Java 17+
- Maven 3.9+
- MySQL 8.x or AWS RDS MySQL
- Docker (optional)

## 1. Create database
Run:
mysql -u root -p < database/schema.sql

For AWS RDS, the schema file only creates the database if the RDS user has permission.
You can also create `restaurantdb` from an SQL client.

## 2. Configure connection
Linux/macOS:
export DB_URL='jdbc:mysql://YOUR-RDS-ENDPOINT:3306/restaurantdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'
export DB_USERNAME='admin'
export DB_PASSWORD='YOUR_PASSWORD'

Windows PowerShell:
$env:DB_URL='jdbc:mysql://YOUR-RDS-ENDPOINT:3306/restaurantdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'
$env:DB_USERNAME='admin'
$env:DB_PASSWORD='YOUR_PASSWORD'

## 3. Run locally
mvn clean package
java -jar target/restaurant-app-1.0.0.jar

API: http://localhost:8080

## REST APIs
GET    /api/restaurants
POST   /api/restaurants
GET    /api/restaurants/{id}
PUT    /api/restaurants/{id}
DELETE /api/restaurants/{id}

GET    /api/menu
GET    /api/menu/restaurant/{restaurantId}
POST   /api/menu/restaurant/{restaurantId}
DELETE /api/menu/{id}

GET    /api/orders
GET    /api/orders/{id}
POST   /api/orders
PATCH  /api/orders/{id}/status?value=CONFIRMED

## Example JSON - Restaurant
{
  "name": "Spice Garden",
  "address": "Hyderabad",
  "phone": "9876543210"
}

## Example JSON - Menu item
{
  "name": "Chicken Biryani",
  "description": "Hyderabadi dum biryani",
  "price": 280.00,
  "available": true
}

## Example JSON - Order
{
  "customerName": "Ravi",
  "customerPhone": "9876543210",
  "itemName": "Chicken Biryani",
  "quantity": 2,
  "totalAmount": 560.00
}

## Docker
mvn clean package
docker build -t restaurant-app .
docker run -d --name restaurant-app -p 8080:8080   -e DB_URL='jdbc:mysql://YOUR-RDS-ENDPOINT:3306/restaurantdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'   -e DB_USERNAME='admin'   -e DB_PASSWORD='YOUR_PASSWORD'   restaurant-app

Check:
curl http://YOUR-EC2-PUBLIC-IP:8080/api/restaurants

## AWS RDS / EC2 notes
1. RDS security group must allow MySQL TCP 3306 from the EC2 security group.
2. EC2 security group must allow TCP 8080 from your required client IP/range for testing.
3. Do not expose port 3306 to 0.0.0.0/0.
4. Prefer environment variables or AWS Secrets Manager for DB credentials.
