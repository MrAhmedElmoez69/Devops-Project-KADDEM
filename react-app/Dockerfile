# Use an official Node.js runtime as a parent image
FROM node:14

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install app dependencies
RUN npm install

# Bundle app source
COPY . .

# Expose the port on which your React app runs (e.g., 3001)
EXPOSE 3001

# Define the command to start your React app
CMD ["npm", "start"]

