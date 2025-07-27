# 🔔 Real-time Notifications System

A complete real-time notification system built with **Spring Boot WebSockets** backend and **modern HTML/CSS/JavaScript** frontend. This project demonstrates bidirectional communication between client and server using WebSocket technology.

## ✨ Features

### Frontend
- 🎨 **Modern UI Design** - Glassmorphism design with smooth animations
- 🔌 **Real-time WebSocket Connection** - Uses SockJS and STOMP protocols
- 📱 **Responsive Design** - Works on desktop and mobile devices
- 🎭 **Demo Mode** - Fallback simulation when server is offline
- 🎯 **Interactive Notifications** - Send and receive notifications in real-time
- 🎨 **Type-based Styling** - Different colors for different notification types
- ⚡ **Auto-reconnection** - Handles connection failures gracefully

### Backend
- 🚀 **Spring Boot WebSocket** - Full WebSocket implementation with STOMP
- 🔄 **Multiple Endpoints** - WebSocket, REST API, and system notifications
- ⏰ **Scheduled Notifications** - Automatic periodic system messages
- 🔗 **Connection Management** - Handle client connect/disconnect events
- 💾 **In-memory Storage** - Notification history (easily extendable to database)
- 🌐 **CORS Support** - Cross-origin resource sharing configured
- 📝 **Comprehensive Logging** - Debug and monitor WebSocket connections

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring WebSocket**
- **STOMP Messaging**
- **Maven**

### Frontend
- **HTML5**
- **CSS3** (with modern features like backdrop-filter)
- **Vanilla JavaScript**
- **SockJS Client**
- **STOMP.js**

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Modern web browser** (Chrome, Firefox, Safari, Edge)
- **IDE** (IntelliJ IDEA, VS Code, Eclipse - optional)

## 🚀 Quick Start

### 1. Backend Setup

#### Clone or create the project structure:
```
notification-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/notificationapp/
│       │       ├── NotificationApplication.java
│       │       ├── config/
│       │       │   ├── WebSocketConfig.java
│       │       │   └── CorsConfig.java
│       │       ├── controller/
│       │       │   └── NotificationController.java
│       │       ├── model/
│       │       │   └── Notification.java
│       │       ├── service/
│       │       │   └── NotificationService.java
│       │       └── listener/
│       │           └── WebSocketEventListener.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

#### Build and run:
```bash
# Navigate to project directory
cd notification-app

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Or run the JAR file
java -jar target/notification-app-1.0.0.jar
```

The backend will start on `http://localhost:8080`

### 2. Frontend Setup

1. Save the HTML file as `index.html`
2. Open in a web browser
3. Click **"Connect"** to establish WebSocket connection

## 📖 Usage Guide

### Connecting to the Server

1. **Start the Spring Boot backend** (see backend setup above)
2. **Open the frontend** in your browser
3. **Click "Connect"** button
4. **Status should change** to "Connected to server"

### Sending Notifications

1. **Fill in the form**:
   - Title: Short notification title
   - Message: Detailed notification message
2. **Click "Send Notification"** or press `Ctrl+Enter`
3. **Notification appears** in real-time for all connected clients

### Demo Mode

If the backend server is not running:
- Frontend automatically enters **Demo Mode**
- Simulated notifications are generated
- All functionality works offline for testing

## 🔌 API Endpoints

### WebSocket Endpoints
- **Connection**: `ws://localhost:8080/notifications`
- **Send Message**: `/app/send`
- **Subscribe**: `/topic/notifications`

### REST API Endpoints
- **POST** `/api/notifications/send` - Send notification via REST
- **POST** `/api/notifications/system?message=text` - Send system notification
- **GET** `/api/notifications` - Get all notifications

### Example REST API Usage:
```bash
# Send notification via REST API
curl -X POST http://localhost:8080/api/notifications/send \
  -H "Content-Type: application/json" \
  -d '{
    "title": "API Notification",
    "message": "This notification was sent via REST API"
  }'

# Send system notification
curl -X POST "http://localhost:8080/api/notifications/system?message=System maintenance completed"

# Get all notifications
curl http://localhost:8080/api/notifications
```

## 🧪 Testing the System

### Manual Testing
1. **Open multiple browser tabs** with the frontend
2. **Connect all tabs** to the server
3. **Send notification from one tab**
4. **Verify it appears** in all other tabs instantly

### Automated Testing
```bash
# Test WebSocket connection
wscat -c ws://localhost:8080/notifications

# Test REST API
curl -X POST http://localhost:8080/api/notifications/send \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","message":"Hello World"}'
```

## 📁 Project Structure

```
notification-system/
├── backend/
│   ├── src/main/java/com/example/notificationapp/
│   │   ├── NotificationApplication.java          # Main Spring Boot class
│   │   ├── config/
│   │   │   ├── WebSocketConfig.java              # WebSocket configuration
│   │   │   └── CorsConfig.java                   # CORS configuration
│   │   ├── controller/
│   │   │   └── NotificationController.java       # WebSocket & REST endpoints
│   │   ├── model/
│   │   │   └── Notification.java                 # Notification data model
│   │   ├── service/
│   │   │   └── NotificationService.java          # Business logic
│   │   └── listener/
│   │       └── WebSocketEventListener.java       # Connection event handler
│   ├── src/main/resources/
│   │   └── application.properties                # Application configuration
│   └── pom.xml                                   # Maven dependencies
├── frontend/
│   └── index.html                                # Complete frontend application
└── README.md                                     # This file
```

## 🔧 Configuration

### Backend Configuration (`application.properties`)
```properties
# Server configuration
server.port=8080
spring.application.name=notification-app

# Logging
logging.level.com.example.notificationapp=DEBUG
logging.level.org.springframework.web.socket=DEBUG

# WebSocket settings
spring.websocket.message-size-limit=64KB
spring.websocket.send-buffer-size-limit=512KB
spring.websocket.send-time-limit=20000
```

### Frontend Configuration
Update the server URL in the JavaScript if needed:
```javascript
this.serverUrl = 'http://localhost:8080'; // Change if running on different port
```

## 🎯 Notification Types

The system supports different notification types with unique styling:

- **SYSTEM_MESSAGE** ⚙️ - System-generated notifications
- **USER_MESSAGE** 👤 - User-sent notifications
- **CONNECTION** 🔗 - Connection status notifications
- **API_MESSAGE** 🔌 - REST API notifications
- **SYSTEM_SCHEDULED** ⏰ - Scheduled system notifications
- **DEMO** 🎭 - Demo mode notifications

## 🚨 Troubleshooting

### Common Issues

#### WebSocket Connection Failed
- **Check if backend is running** on port 8080
- **Verify firewall settings** allow WebSocket connections
- **Check browser console** for detailed error messages
- **Try demo mode** to test frontend functionality

#### CORS Issues
- **Backend includes CORS configuration** for all origins
- **Check browser developer tools** for CORS errors
- **Verify origin patterns** in `CorsConfig.java`

#### Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>

# Or change port in application.properties
server.port=8081
```

### Debug Mode
Enable debug logging in `application.properties`:
```properties
logging.level.org.springframework.web.socket=DEBUG
logging.level.org.springframework.messaging=DEBUG
```

## 🔄 Development Workflow

### Adding New Features

1. **Backend Changes**:
   - Add new endpoints in `NotificationController`
   - Extend `Notification` model if needed
   - Update `NotificationService` for business logic

2. **Frontend Changes**:
   - Modify the `NotificationClient` class
   - Add new UI elements and styling
   - Update message handling logic

### Database Integration
To replace in-memory storage with a database:

1. **Add JPA dependency** to `pom.xml`
2. **Create entity** with `@Entity` annotation
3. **Add repository** interface extending `JpaRepository`
4. **Update service** to use repository instead of in-memory list

## 📚 Learning Resources

### WebSocket & STOMP
- [Spring WebSocket Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)
- [STOMP Protocol Specification](https://stomp.github.io/stomp-specification-1.2.html)
- [MDN WebSocket API](https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API)

### Spring Boot
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Boot WebSocket Tutorial](https://spring.io/guides/gs/messaging-stomp-websocket/)

## 🤝 Contributing

1. **Fork the repository**
2. **Create feature branch**: `git checkout -b feature/amazing-feature`
3. **Make changes** and test thoroughly
4. **Commit changes**: `git commit -m 'Add amazing feature'`
5. **Push to branch**: `git push origin feature/amazing-feature`
6. **Open Pull Request**

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙋‍♂️ Support

If you encounter any issues or have questions:

1. **Check the troubleshooting section** above
2. **Review browser console** for error messages  
3. **Check Spring Boot logs** for backend issues
4. **Create an issue** in the repository

## 🎉 Acknowledgments

- **Spring Framework Team** for excellent WebSocket support
- **SockJS** and **STOMP.js** communities for client libraries
- **Modern CSS** techniques for beautiful UI design

---

**Happy coding! 🚀**