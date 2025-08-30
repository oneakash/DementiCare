#!/bin/bash

echo "Starting DementiCare Microservices..."

# Function to check if a service is running
check_service() {
    local port=$1
    local service_name=$2
    
    if curl -s http://localhost:$port/actuator/health > /dev/null 2>&1; then
        echo "✅ $service_name is running on port $port"
        return 0
    else
        echo "❌ $service_name is not responding on port $port"
        return 1
    fi
}

# Start Eureka Server first
echo "🚀 Starting Eureka Server..."
cd eureka-server
mvn spring-boot:run > ../logs/eureka.log 2>&1 &
EUREKA_PID=$!
cd ..

# Wait for Eureka to start
echo "⏳ Waiting for Eureka Server to start..."
sleep 30

# Check if Eureka is running
if check_service 8761 "Eureka Server"; then
    echo "✅ Eureka Server started successfully"
else
    echo "❌ Failed to start Eureka Server"
    exit 1
fi

# Start other services
echo "🚀 Starting User Service..."
cd user-service
mvn spring-boot:run > ../logs/user-service.log 2>&1 &
USER_SERVICE_PID=$!
cd ..

echo "🚀 Starting Task Service..."
cd task-service
mvn spring-boot:run > ../logs/task-service.log 2>&1 &
TASK_SERVICE_PID=$!
cd ..

echo "🚀 Starting Content Service..."
cd content-service
mvn spring-boot:run > ../logs/content-service.log 2>&1 &
CONTENT_SERVICE_PID=$!
cd ..

# Wait for services to start
echo "⏳ Waiting for services to start..."
sleep 45

# Start API Gateway last
echo "🚀 Starting API Gateway..."
cd api-gateway
mvn spring-boot:run > ../logs/api-gateway.log 2>&1 &
API_GATEWAY_PID=$!
cd ..

# Wait for API Gateway
echo "⏳ Waiting for API Gateway to start..."
sleep 30

# Check all services
echo "🔍 Checking service health..."
check_service 8761 "Eureka Server"
check_service 8081 "User Service"
check_service 8082 "Task Service"
check_service 8083 "Content Service"
check_service 8080 "API Gateway"

echo ""
echo "🎉 DementiCare Platform is ready!"
echo "📊 Eureka Dashboard: http://localhost:8761"
echo "🌐 API Gateway: http://localhost:8080"
echo "👤 User Service: http://localhost:8081"
echo "📋 Task Service: http://localhost:8082"
echo "📚 Content Service: http://localhost:8083"
echo ""
echo "💡 Use Ctrl+C to stop all services"

# Create a function to cleanup on exit
cleanup() {
    echo "🛑 Stopping all services..."
    kill $EUREKA_PID $USER_SERVICE_PID $TASK_SERVICE_PID $CONTENT_SERVICE_PID $API_GATEWAY_PID 2>/dev/null
    echo "✅ All services stopped"
    exit 0
}

# Set trap to cleanup on script exit
trap cleanup SIGINT SIGTERM

# Keep script running
wait