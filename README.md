# Offsets Central

## Description

This is a simple Rest tool to read Offsets from maintained repositories and complete the list of offsets fast and easy.

## Build

To build the application, run the following command with maven:

```
mvn clean compile package
```

Optionally, fill `app.github.token` in `application.properties` with your GitHub token to avoid rate limits.

## Usage

Run the application and go to `http://localhost:8080/offsets` to see the list of offsets.

<img src="https://i.gyazo.com/0a8ee4cba3accf33999b060ee5f0d402.png">

Add new repositories to the list by adding them to the `repositories` folder. 

The application will automatically read the offsets from the repositories and add them to the list.

It manages offsets lists in JSON and INI format.

<img src="https://i.gyazo.com/5e5622ae58e59a1f02e482d04ec6f887.png">

If some offsets are missing, you can ignore them and it will be completed from the other repositories.

<img src="https://i.gyazo.com/697007738c206e1d2d4e10910cd80786.png">