# 🌊 Wobble

### *See It. Understand It. Master It.*

Wobble is an AI-powered mathematical assistant that transforms images containing equations, formulas, and mathematical expressions into meaningful insights. Simply upload an image, and Wobble extracts mathematical content, converts it into LaTeX, and explains the concepts in a conversational, AI-driven interface.

---

## ✨ Features

### 📸 Formula Extraction

* Upload images containing equations or formulas.
* AI-powered OCR for mathematical expressions.
* Automatic conversion of formulas into valid LaTeX.

### 🧠 AI Formula Explanation

* Identifies mathematical laws, theorems, and concepts.
* Provides beginner-friendly explanations.
* Explains variables and symbols used in equations.

### 💬 Conversational Interface

* Modern AI chat-inspired UI.
* Scrollable conversation history.
* Interactive formula explanations.
* Beautiful mathematical rendering.

### 🎨 Modern UI Experience

* JetBrains-inspired Dark Theme.
* Elegant Light Theme with soft purple aesthetics.
* Smooth animations and glassmorphism effects.
* Fully responsive design.

---

## 🏗️ Architecture

```text
User Uploads Image
        │
        ▼
Spring Boot Backend
        │
        ▼
Gemini Vision API
        │
        ▼
Text & Formula Extraction
        │
        ▼
LaTeX Generation
        │
        ▼
Formula Explanation
        │
        ▼
Conversational AI Interface
```

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Web
* RestClient
* Jackson
* Maven

### AI

* Google Gemini 2.5 Flash
* Gemini Vision API

### Frontend

* HTML5
* CSS3
* JavaScript
* KaTeX / MathJax

---

## 📂 Project Structure

```text
src
├── main
│   ├── java
│   │   └── riku.spring.wobble
│   │       ├── controller
│   │       ├── dto
│   │       ├── service
│   │       └── WobbleApplication.java
│   │
│   └── resources
│       ├── static
│       │   └── index.html
│       ├── templates
│       └── application.properties
│
└── test
```

---

## 🚀 API Endpoints

### Extract Mathematical Content

```http
POST /api/image/extract
```

**Request**

* Content-Type: `multipart/form-data`
* Parameter: `file`

**Response**

```json
{
  "title": "Newton's Law of Universal Gravitation",
  "text": "The force of attraction between two masses.",
  "latex": [
    "F = G\\frac{m_1m_2}{r^2}"
  ]
}
```

---

### Explain Formula

```http
POST /api/image/explain
```

**Request**

```json
{
  "latex": "F = G\\frac{m_1m_2}{r^2}"
}
```

**Response**

```json
{
  "title": "Newton's Law of Universal Gravitation",
  "explanation": "The gravitational force between two masses is proportional to the product of their masses and inversely proportional to the square of the distance between them.",
  "variables": {
    "F": "Gravitational Force",
    "G": "Universal Gravitational Constant",
    "m_1": "Mass of the First Object",
    "m_2": "Mass of the Second Object",
    "r": "Distance Between Centers"
  }
}
```

---

## ⚙️ Getting Started

### Clone the Repository

```bash
git clone https://github.com/Manabendu-ai/Wobble.git
cd Wobble
```

### Configure Environment Variables

Create:

```properties
src/main/resources/application.properties
```

Add:

```properties
gemini.api.key=YOUR_GEMINI_API_KEY
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### Run the Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

---

## 🌟 Future Enhancements

* Step-by-step equation solving
* Handwritten mathematical expression support
* Formula history and bookmarks
* PDF and document processing
* AI-generated study notes
* Concept quizzes and flashcards
* Voice-powered formula explanations
* Multi-language support

---

## 🎯 Vision

Wobble aims to bridge the gap between seeing a formula and understanding it. By combining modern AI capabilities with an intuitive conversational interface, Wobble transforms mathematical expressions into accessible knowledge.

---

## 👨‍💻 Author

**Manabendu Karfa**

B.Tech Artificial Intelligence & Machine Learning
Cambridge Institute of Technology

Passionate about building intelligent systems, backend architectures, and AI-powered educational technologies.

---

## 📜 License

This project is licensed under the MIT License.

---

<div align="center">

### 🌊 Wobble

#### *See It. Understand It. Master It.*

Built with ☕ Java, 🤖 Gemini AI, and 💜 curiosity.

</div>
