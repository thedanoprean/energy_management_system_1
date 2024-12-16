# Energy Management System - Core Version

## Overview
The Energy Management System (EMS) is a comprehensive platform designed to facilitate efficient management of users and their associated smart energy metering devices. The system incorporates a **frontend application** and two backend **microservices**: 
- **UserMicroservice**
- **DeviceMicroservice**

These components work together to ensure robust user management and seamless device operations.

## Features
### System Objectives
- User authentication and authorization for administrators and clients.
- CRUD operations for users, devices, and user-device mappings (admin access).
- Role-based access control to restrict unauthorized actions.
- View-only access for clients to their associated smart devices.

## Architecture
### Microservices Architecture
The backend is designed using a microservices architecture to ensure modularity, scalability, and maintainability:
- **UserMicroservice**: Manages user-related operations.
- **DeviceMicroservice**: Handles device-related operations.

### Frontend Application
The frontend provides an intuitive interface for users to interact with the system, including:
- **Authentication**: Login functionality.
- **Admin Operations**: CRUD management for users and devices.
- **Client Operations**: View device details.

### Data Consistency
Data consistency between microservices is maintained using a **UserID mechanism** that ensures synchronized storage of user-related data across both services.

## Deployment Architecture
The EMS is deployed using **Docker containers**, with the following components:
- **Frontend Container**: Serves the user interface (exposed on port `7000`).
- **UserMicroservice Container**: Manages user-related data and functions (exposed on port `3000`).
- **DeviceMicroservice Container**: Handles device data and operations (exposed on port `3003`).
- **PostgreSQL Containers**: Databases for user and device data (exposed on ports `5404` and `5303` respectively).

## Functional Requirements
### User Microservice
- **Registration**: Validation and storage of user details.
- **Authentication**: Role-based login for admins and clients.
- **CRUD Operations**: Manage user accounts and data.

### Device Microservice
- **Registration**: Register and associate devices with users.
- **CRUD Operations**: Manage device details.

## Non-Functional Requirements
- **Performance**: Response times under 1 second for most operations.
- **Scalability**: Support for increasing users and devices.
- **Reliability**: High availability with minimal downtime.
- **Usability**: User-friendly frontend and clear error messages.

## Future Enhancements
Planned improvements for EMS include:
- Advanced analytics for energy consumption.
- Real-time device monitoring.
- Integration of predictive maintenance using machine learning.

## Technologies Used
- **Frontend**: React.js, Axios.
- **Backend**: Spring Boot (Java 17).
- **Databases**: PostgreSQL.
- **Containerization**: Docker, Docker Compose.
- **Web Server**: Nginx.

## Deployment Instructions
1. Clone the repository.
2. Set up the environment variables for the microservices and databases.
3. Build and run the Docker containers using `docker-compose up`.
4. Access the application at `http://localhost:7000`.

## Contributors
- **Student**: Oprean Dan
- **Group**: 30243
