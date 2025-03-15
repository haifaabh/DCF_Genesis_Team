# Use Node.js official image
FROM node:18

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package*.json ./
RUN npm install

# Copy all project files
COPY . .

# Expose Hardhat’s network port
EXPOSE 8545

# Start the Hardhat local blockchain
CMD ["npx", "hardhat", "node"]
