# Stage 1: Compile and Build Frontend
FROM node:latest as build
WORKDIR /usr/local/app
# Leverage Caching
COPY ./bimdb/package.json ./bimdb/package-lock.json /usr/local/app/
RUN npm install 

COPY ./bimdb /usr/local/app/
RUN npm run build

# Stage 2: Serve app with nginx 
FROM nginx:latest
# Copy the build output 
COPY --from=build /usr/local/app/dist/bimdb /usr/share/nginx/html
# Info only
EXPOSE 80