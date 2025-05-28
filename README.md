# Blackjack AI Game

![Java](https://img.shields.io/badge/Language-Java-blue) ![Firebase](https://img.shields.io/badge/Database-Firebase-yellow) ![Vercel](https://img.shields.io/badge/Deployment-Vercel-black)

## 📖 Overview

**Blackjack AI Game** is a single-player web application that brings the classic casino game of Blackjack to your browser. It features:

- A **Java**-based core game engine implementing standard Blackjack rules.
- An **AI dealer** with both basic (hit until ≥17, soft-17 rules) and advanced (Hi-Lo card counting) strategies.
- **Firebase Firestore** persistence for user sessions, game states, and aggregate statistics.
- A **serverless** REST API deployed on **Vercel** to bridge the front-end and Java engine.
- A **React** front-end (or Vue) for an interactive, responsive UI with animations and live stats.

## 🚀 Features

- **Robust Game Logic**: Shuffling (Fisher–Yates), dealing, Hit/Stand, Blackjack, bust detection.
- **Dealer AI**:
  - Basic: Draw until ≥17 with configurable soft-17 handling.
  - Advanced: Real-time Hi-Lo card counting to influence decisions or bets.
- **User Stats Dashboard**: Track wins/losses, true count history, and betting performance.
- **Test Suite**:
  - Unit Tests (JUnit) for core engine.
  - Integration Tests (Postman/Newman) for API.
  - E2E Tests (Cypress) for front-end flows.
  - Coverage ≥ 90%.
- **CI/CD Ready**: GitHub Actions (or your CI), one-click deploy on Vercel & Firebase.

## 🛠️ Tech Stack

| Layer              | Technology            |
|--------------------|-----------------------|
| Core Engine        | Java 11+, Maven       |
| API                | Node.js (serverless)  |
| Database           | Firebase Firestore    |
| Front-End          | React (CRA) or Vue    |
| Deployment         | Vercel, Firebase CLI  |
| Testing            | JUnit, Jest, Cypress  |

## 📦 Project Structure

```plaintext
blackjack-ai-game/
├── backend/               # Java core engine & unit tests
├── api-serverless/        # Vercel API endpoints
├── frontend/              # React or Vue application
├── docs/                  # Architecture, API specs, diagrams
├── scripts/               # Build & deploy helpers
├── tests/                 # Integration & E2E suites
├── firebase.json          # Firestore rules & hosting config
└── vercel.json            # Vercel settings
```

## 🔧 Prerequisites

- **Java 11+** and **Maven**
- **Node.js** (14+) and **npm/yarn**
- **Firebase** account with Firestore enabled
- **Vercel** account & **Vercel CLI** (`npm i -g vercel`)

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/mshah972/blackjack-ai-game.git
cd blackjack-ai-game
```

### 2. Backend (Java Core Engine)

```bash
cd backend
./mvnw clean package
# (Optional) Run unit tests
./mvnw test
```

### 3. Serverless API (Vercel)

```bash
cd api-serverless
npm install
vercel --prod
```

### 4. Front-End

```bash
cd frontend
npm install
npm run dev     # or npm start for CRA
# Build for production
npm run build
vercel --prod
```

### 5. Firebase Configuration

1. Create a `.env` file at the project root:
   ```ini
   FIREBASE_API_KEY=...
   FIREBASE_AUTH_DOMAIN=...
   FIREBASE_PROJECT_ID=...
   FIRESTORE_EMULATOR_HOST=...
   ```
2. Deploy Firestore rules and hosting (if applicable):
   ```bash
   firebase deploy --only firestore,hosting
   ```

## 🧪 Testing

- **Unit Tests**: `cd backend && ./mvnw test`
- **API Tests**: `cd tests/integration && newman run api-collection.json`
- **E2E Tests**: `cd tests/e2e && npx cypress open`

## 📂 Continuous Integration

A sample GitHub Actions workflow is included at `.github/workflows/ci.yml` to:
- Build & test the Java engine.
- Lint & test the front-end.
- Run API integration tests.

## 🤝 Contributing

1. Fork the repo
2. Create a feature branch (`git checkout -b feat/your-feature`)
3. Commit your changes (`git commit -m 'Add feature'`)
4. Push to branch (`git push origin feat/your-feature`)
5. Open a pull request

Please read `CONTRIBUTING.md` for more details on code standards.

## 📄 License

This project is licensed under the [MIT License](LICENSE).
