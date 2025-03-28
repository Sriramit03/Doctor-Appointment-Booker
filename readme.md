# DocBridge - Mobile Doctor Appointment Booking System

## 📋 Project Overview

DocBridge is a mobile application that simplifies the process of booking doctor appointments online. The platform provides an easy-to-use interface for patients to find, select, and schedule appointments with healthcare professionals.

## ✨ Features

- User Registration and Authentication
- Doctor Profile Browsing
- Real-time Appointment Scheduling
- Specialized Department Filtering
- Appointment Management
- Mobile-Friendly User Interface

## 🛠 Tech Stack

### Mobile App

- React Native
- JavaScript/TypeScript

### Backend

- Spring Boot
- Spring Security
- Spring Data JPA

### Database

- MySQL

### Authentication

- Spring Security

## 🚀 Getting Started

### Prerequisites

- Node.js and npm
- Expo
- Java Development Kit (JDK)
- MySQL Server

### Installation

1. Clone the repository

```bash
git clone https://github.com/Sriramit03/Doctor-Appointment-Booker.git
```

2. Set Up Backend

```bash
cd backend
# Use your preferred IDE or Maven to set up the Spring Boot project
```

3. Configure Database

- Create a MySQL database
- Update `application.properties` with your database credentials

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/docbridge
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Install Mobile App Dependencies

```bash
cd mobile
npm install
```

### Running the Application

1. Start Backend Server

```bash
# Using Maven
mvn spring-boot:run
```

2. Run Mobile App

```bash
# For Android
npx expo start
```

## 📦 Project Structure

```
Doctor-Appointment-Booker/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/AppointmentBooker/
│   │   │   │       ├── controller/
│   │   │   │       ├── dao/
│   │   │   │       ├── dto/
│   │   │   │       ├── entity/
│   │   │   │       ├── exception/
│   │   │   │       ├── security/
│   │   │   │       ├── service/
│   │   │   │
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   └── pom.xml
│
└── frontend/
    ├── components/
    ├── context/
    ├── app/
    │   ├── (auth)
    │   ├── (tabs)
    │   └── booking
    └── package.json
```

## 🔐 Authentication

The application uses Spring Security for secure user authentication:

- User registration
- Secure login process
- Password encryption using BCrypt

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

## 🙌 Acknowledgements

- React Native Community
- Spring Boot
- Spring Security
- MySQL
- Open-source libraries and tools

## 📞 Contact

Sriramit - [GitHub Profile](https://github.com/Sriramit03)

Project Link: [https://github.com/Sriramit03/Doctor-Appointment-Booker](https://github.com/Sriramit03/Doctor-Appointment-Booker)
