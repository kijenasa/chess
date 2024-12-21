# Chess
An online chess site written in Java, powered by Spring Boot and SQLite.
I made this as an entry into Spring Boot, and into backend development as a whole, as well as a submission for [High Seas](https://highseas.hackclub.com/).

## Deploying with Docker
Start by cloning and opening the repository.
```
git clone https://github.com/kijenasa/chess.git
cd chess
```
Build an image from the Dockerfile
```
docker build -t chess .
```
And finally, run it
```
docker run -d -p 8080:8080 chess
```
