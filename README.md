![Logo](https://github.com/DeemaSWE/TechCare-old/assets/90179257/94015613-c942-4212-829a-a446b1a9d79a)
# TechCare

__Welcome to TechCare!__

TechCare is your reliable destination for all your electronic repair needs. We connect you with skilled technicians who can  restore your malfunctioning devices. Our platform caters to three users: Customers, Technicians, and Trainees.

__For Customers__

- Find skilled, trusted technicians who can fix your devices .
- Our platform makes it simple to request a repair, track progress, and leave feedback.
- Browse technician profiles and ratings to make informed decisions.

__For Technicians__

- Reach more customers and gain visibility for your repair skills.
- Accept or decline requests, setting your own hours and workload.
- Train the next generation of technicians and leave a lasting impact.

__For Trainees__

- Learn from experienced technicians and get hands-on training.
- Build your experience and reputation as a repair technician.
- Connect with technicians dedicated to sharing their knowledge.

## Tools
- Intellij
- DataGrip
- Postman
- Xampp
- Figma
- Canva

## Dependencies
- Lombok
- Validation
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- JUnit tests
## Presentation
https://www.canva.com/design/DAGEkE6e5qk/PMreR9pmtzD674fN389cjw/edit?utm_content=DAGEkE6e5qk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
## API Documentation
https://documenter.getpostman.com/view/33389231/2sA3JJA441
## Figma
https://www.figma.com/file/Ifmivq19uWHkpAEAQG3IZ3/Figma-basics?type=design&node-id=1669%3A162202&mode=design&t=AVVffBJIKCgRwYn8-1
## Endpoints
__Deema's Endpoints__
| No. | Endpoint | Description |
| --- | --- | --- |
| 1 | `GET /api/v1/category/get-all` | Get all categories |
| 2 | `POST /api/v1/category/add` | Admin adds a category |
| 3 | `PUT /api/v1/category/update/{categoryId}` | Admin updates a category |
| 4 | `DELETE /api/v1/category/delete/{categoryId}` | Admin deletes a category |
| 5 | `GET /api/v1/order/get-all` | Admin gets all orders |
| 6 | `PUT /api/v1/order/update/{orderId}` | Admin updates an order |
| 7 | `DELETE /api/v1/order/delete/{orderId}` | Admin deletes an order |
| 8 | `PUT /api/v1/order/update-status/{orderId}/{status}` | Admin changes order status |
| 9 | `POST /api/v1/order/create` | Customer makes an order |
| 10 | `GET /api/v1/order/get-my-orders` | Customer gets their orders |
| 11 | `GET /api/v1/product/rate-product/{productId}/{rating}` | Customer rate purchased product |
| 12 | `GET /api/v1/product/top-rated` | Get top rated products |
| 13 | `PUT /api/v1/request/set-status/{status}/{requestId}` | Technician accept/reject a request |   
| 14 | `GET /api/v1/request/get-by-status/{status}` | Technician get requests by status (pending, accepted, rejected) |   
| 15 | `GET /api/v1/services/get-all` | Get all services |
| 16 | `POST /api/v1/services/add-customer-service` | Technician adds maintenance and consulting services for customers |
| 17 | `POST /api/v1/services/add-trainer-service` | Technician adds training services for trainers |
| 18 | `PUT /api/v1/services/update/{serviceId}` | Technician updates a service |
| 19 | `DELETE /api/v1/services/delete/{serviceId}` | Technician deletes a service |
| 20 | `GET /api/v1/services/get-tech-services` | Technician gets their services |
| 21 | `GET /api/v1/services/filter-by-price/{min}/{max}` | Filter services by price range |
| 22 | `GET /api/v1/services/get-by-type/{type}` | Get services by type |
| 23 | `GET /api/v1/technician/register` | Technician registers |
| 24 | `GET /api/v1/technician/get-all` | Admin gets all technicians |
| 25 | `GET /api/v1/technician/get-profile` | Technician gets their profile |
| 26 | `PUT /api/v1/technician/update` | Technician updates their profile |
| 27 | `DELETE /api/v1/technician/delete/{technicianId}` | Admin deletes a technician |


__Sara's Endpoints__
| No. | Endpoint | Description |
| --- | --- | --- |
| 1 | `GET /api/v1/customer/get-all` | Get all customers |
| 2 | `POST /api/v1/customer/register` | Customer register |
| 3 | `GET /api/v1/customer/get-profile` | Customer get their profile |
| 4 | `PUT /api/v1/customer/update` | Customer update their profile |
| 5 | `DELETE /api/v1/customer/delete/{customerId}` | Admin delete customer |
| 6 | `PUT /api/v1/customer/rate-tech/{techID}/{reqId}/{rate}` | Customer rate technician |
| 7 | `GET /api/v1/order/get-order-by-status/{status}` | Customer get order by status |
| 8 | `GET /api/v1/order/get-order-by-date/{date}` | Customer get order by date |
| 9 | `GET /api/v1/product/get-all` | Get all products |
| 10 | `POST /api/v1/product/add-product/{catId}` | Admin add a product |
| 11 | `PUT /api/v1/product/update-product/{productId}/{catId}` | Admin update a product |
| 12 | `DELETE /api/v1/product/delete-product/{productId}` | Admin delete a product |
| 13 | `GET /api/v1/product/get-product/{productId}` | Get product by id |
| 14 | `GET /api/v1/product/get-product-category/{category}` | Get products by category |
| 15 | `GET /api/v1/product/filter-price/{minPrice}/{maxPrice}` | Get products by price range |
| 16 | `GET /api/v1/request/customer-get-my-request` | Customer get their requests |
| 17 | `POST /api/v1/request/customer-send-request/{serviceId}` | Customer send a request |
| 18 | `POST /api/v1/request/trainer-send-request/{serviceId}` | Trainer send a request |
| 19 | `DELETE /api/v1/request/delete/{request_num}` | Admin delete a request |
| 20 | `PUT /api/v1/request/change-customer-request-status/{reqId}` | Technician change customer requests status to be completed |
| 21 | `PUT /api/v1/request/customer-rate-request/{reqId}/{rate}` | Customer Rate Request |
| 22 | `GET /api/v1/request/customer-request-status/{status}` | Customer get Requests by status |
| 23 | `GET /api/v1/request/change-trainer-request-status/{reqId}` | Technician change requests status to be completed and set hours |
| 24 | `GET /api/v1/request/tech-get-request` | Technician get requests |
| 25 | `GET /api/v1/request/trainer-get-request` | Trainer get their requests |
| 26 | `GET /api/v1/auth/get-all` | Admin get all users |


__Ahmed's Endpoints__
| No. | Endpoint | Description |
| --- | --- | --- |
| 1 | `POST /api/v1/trainer/register` | Trainer register |
| 2 | `GET /api/v1/trainer/get-all` | Admin get all trainers |
| 3 | `GET /api/v1/trainer/get-profile` | Trainer get their profile |
| 4 | `PUT /api/v1/trainer/update` | Trainer update their profile |
| 5 | `DELETE /api/v1/trainer/delete/{trainer_id}` | Admin delete a trainer |
| 6 | `PUT /api/v1/trainer/rate-tech/{techID}/{reqId}/{rate}` | Trainer rate technician |
| 7 | `GET /api/v1/services/get-by-category/{categoryId}` | Get services by category |
| 8 | `GET /api/v1/services/get-by-hours/{hours}` | Get training services by hours |
| 9 | `PUT /api/v1/request/trainer-rate-request/{reqId}/{rate}` | Trainer Rate Request |
| 10 | `GET /api/v1/technician/service-technician/{service_id}` | Get technician by service |
| 11 | `GET /api/v1/technician/get-by-id/{technicianId}` | Get technician by id |
| 12 | `GET /api/v1/technician/top-rated` | Get top rated technicians |
| 13 | `GET /api/v1/technician/get-trainer-level/{level}` | Technician get trainer by level |
| 14 | `GET /api/v1/technician/filter-by-experience/{years}` | Filter technician by years of experience |
## Class Diagram
## Use Case Diagram
![Final_use_case_Diagram drawio](https://github.com/DeemaSWE/test/assets/90179257/68ee24a9-0153-4db3-b50f-3689ca525b07)
