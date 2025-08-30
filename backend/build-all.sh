#!/bin/bash

echo "🏗️  Building DementiCare Platform..."

# Create logs directory
mkdir -p logs

# Build parent project first
echo "📦 Building parent project..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Parent project built successfully"
else
    echo "❌ Failed to build parent project"
    exit 1
fi

# Build each service
services=("eureka-server" "user-service" "task-service" "content-service" "api-gateway")

for service in "${services[@]}"; do
    echo "📦 Building $service..."
    cd $service
    mvn clean package -DskipTests
    
    if [ $? -eq 0 ]; then
        echo "✅ $service built successfully"
    else
        echo "❌ Failed to build $service"
        exit 1
    fi
    
    cd ..
done

echo ""
echo "🎉 All services built successfully!"
echo "🚀 Run './start-services.sh' to start all services"
echo "🐳 Or use 'docker-compose up' to run with Docker"