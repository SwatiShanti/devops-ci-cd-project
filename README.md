# Simple DevOps CI/CD Project 🚀

This is a beginner-friendly, clean, and highly educational DevOps CI/CD demonstration project designed for college students and presentations. It showcases a complete pipeline using **Java Spring Boot**, **Maven**, **Docker**, **GitHub Actions**, and **Docker Hub** without the complexity of cloud providers, databases, or orchestrators.

---

## 📁 Project Folder Structure

```text
devops-ci-cd-project/
├── .github/
│   └── workflows/
│       └── cicd.yml           # GitHub Actions pipeline workflow configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── devopscicd/
│   │   │               ├── DevopsCiCdApplication.java   # Spring Boot Main Entrypoint
│   │   │               └── controller/
│   │   │                   └── ApiController.java        # Simple REST API Controller
│   │   └── resources/
│   │       └── application.properties                   # App configurations (port 8080)
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── devopscicd/
│                       └── DevopsCiCdApplicationTests.java # JUnit test case for CI
├── Dockerfile                  # Multi-stage Docker configuration (Build + Run)
├── pom.xml                     # Maven configuration & dependency manager
└── README.md                   # Project documentation & Viva Q&A
```

---

## 🛠️ Technology Stack & Tools Used

1. **Java Spring Boot (v3.2.5)**: The Java framework used to build our backend REST API.
2. **Maven**: Used to manage dependencies, compile the project, run tests, and package it into a JAR file.
3. **Docker**: Used to containerize the application, ensuring it runs identically on any machine.
4. **GitHub Actions**: The CI/CD tool that automatically triggers on code changes to build, test, and publish our application.
5. **Docker Hub**: The container registry where our final Docker image is stored and hosted.

---

## 💻 How to Run the Project Locally

### Prerequisites
- Install **Java JDK 17**
- Install **Maven** (or use the Maven Wrapper)
- Install **Docker Desktop** (optional, for containerization tests)

### Step 1: Run with Maven
1. Open your terminal in the project directory.
2. Build and run the Spring Boot application using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. Open your browser and go to: `http://localhost:8080/`
4. You should see the success JSON message:
   ```json
   {
     "status": "Success",
     "message": "Welcome to my DevOps CI/CD Pipeline Project!",
     "framework": "Java Spring Boot",
     "tooling": "Maven & Docker",
     "pipeline": "GitHub Actions to Docker Hub"
   }
   ```

### Step 2: Run using Docker (Local Build)
We use a **Multi-Stage Dockerfile** to build the application container.
1. Build the Docker image:
   ```bash
   docker build -t devops-ci-cd-app .
   ```
2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 devops-ci-cd-app
   ```
3. Open `http://localhost:8080/` in your browser. The app is now running inside an isolated Docker container!

---

## ⛓️ How the CI/CD Pipeline Works

Every time you push code changes to the `main` branch on GitHub:

```text
[ Developer Pushes Code ]
          │
          ▼
[ GitHub Actions Pipeline Starts ]
          │
          ├─► 1. Check out code
          ├─► 2. Set up JDK 17
          ├─► 3. Run Maven Tests & Build JAR (mvn clean package)
          ├─► 4. Log in to Docker Hub (using secrets)
          └─► 5. Build and Push Docker Image
          │
          ▼
[ Docker Hub (Image Updated) ]
```

### How to set up GitHub Secrets
To make the pipeline work, GitHub needs permission to push images to your Docker Hub account.
1. Log in to [Docker Hub](https://hub.docker.com/).
2. Go to **Account Settings** -> **Security** -> **New Access Token** to generate a token (password).
3. Open your GitHub Repository, click on **Settings** -> **Secrets and variables** -> **Actions** -> **New repository secret**.
4. Add these two secrets:
   - `DOCKERHUB_USERNAME`: Your Docker Hub account username.
   - `DOCKERHUB_TOKEN`: The Access Token you generated in Step 2.

---

## 🎓 DevOps Viva Questions & Answers (Q&A)

Here are the most common questions examiners ask during a DevOps project viva:

### Q1: What is DevOps?
**Answer:** DevOps is a combination of cultural philosophies, practices, and tools that increases an organization's ability to deliver applications at high velocity. It bridges the gap between software development (Dev) and IT operations (Ops).

### Q2: What is CI/CD?
**Answer:**
* **Continuous Integration (CI):** The practice of automating the integration of code changes from multiple developers into a single shared repository. It involves automatic building and running tests (`mvn clean package` in our project) on every push.
* **Continuous Delivery/Deployment (CD):** The practice of automatically deploying/releasing the built code changes to production or a registry (building the Docker image and pushing it to Docker Hub in our project).

### Q3: Why do we use Docker in DevOps?
**Answer:** Docker solves the *"It works on my machine!"* problem. It packages an application and its dependencies into a lightweight container. This ensures that the application runs exactly the same way in development, testing, staging, and production environments.

### Q4: What is a Multi-stage Docker build? Why is it useful?
**Answer:** A multi-stage build uses multiple `FROM` instructions in a single Dockerfile.
* In our project, **Stage 1** uses a Maven image with JDK to compile the code and build the JAR.
* **Stage 2** uses a tiny Eclipse Temurin JRE image to run the JAR.
* **Why it's useful:** It keeps the final image size very small because we don't package compiler tools, test reports, or source files into the production container, only the final `.jar` file.

### Q5: What is the difference between JDK and JRE in Docker?
**Answer:**
* **JDK (Java Development Kit):** Used for compiling, building, and running Java programs. It is heavy and contains tools like `javac`. We use it in our build stage.
* **JRE (Java Runtime Environment):** Contains only the runtime JVM to execute compiled Java programs. It is much smaller and lighter. We use it in our run stage to minimize container size.

### Q6: What is a port mapping in Docker? (e.g., `-p 8080:8080`)
**Answer:** Containers run in isolated environments. The flag `-p 8080:8080` maps port `8080` of the host system (your machine) to port `8080` inside the Docker container. This allows external users to access the app via the host machine's port.

### Q7: What is GitHub Actions?
**Answer:** GitHub Actions is a continuous integration and continuous delivery (CI/CD) platform that allows you to automate your build, test, and deployment pipelines directly from your GitHub repository. It uses YAML files located in `.github/workflows/`.

### Q8: What are GitHub Secrets? Why not write passwords in `cicd.yml`?
**Answer:** GitHub Secrets are encrypted environment variables created at the repository level. We should never hardcode passwords, API keys, or Docker Hub tokens in code files because anyone viewing the repository can see them. Secrets allow workflows to use credentials securely.

### Q9: What does `mvn clean package` do?
**Answer:**
* `clean`: Deletes the target directory (removes previous build files).
* `package`: Compiles the source code, runs unit tests, and packages the compiled code into a distributable format (like a `.jar` file) inside the `target/` directory.

### Q10: What is the purpose of `pom.xml`?
**Answer:** `pom.xml` (Project Object Model) is the central configuration file for Maven. It describes the project, its dependencies (libraries needed to run the application like Spring Web and Spring Test), build plugins, and Java versions.

### Q11: What is the purpose of the `@RestController` annotation in Spring Boot?
**Answer:** It marks the Java class as a RESTful controller. It combines `@Controller` and `@ResponseBody`, meaning the returned data (e.g., Map, List, or Object) will be automatically converted into JSON format and written directly into the HTTP response body.

### Q12: How would you deploy this container to production?
**Answer:** Once the image is pushed to Docker Hub, we can deploy it on any server (like AWS, Azure, DigitalOcean, or a local server) by simply installing Docker and running:
`docker run -d -p 80:8080 <your-docker-username>/devops-ci-cd:latest`
This downloads the image from Docker Hub and runs it in the background on port 80.
Updated CI/CD
