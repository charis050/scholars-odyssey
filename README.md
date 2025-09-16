# Scholar’s Odyssey
*A learn‑through‑play text adventure in Java*

Scholar’s Odyssey is a lightweight, Zuul‑style text adventure designed for kids. It blends interactive storytelling with **math, geography, and logic** challenges so players learn while they explore.

> Features include inventory and item‑based puzzles, a **beamer** teleportation device, **randomized transporter rooms**, and **stack‑based movement tracking** (go back). New **Quiz Rooms** gate progress behind short educational questions.

---

## ✨ Features
- **Educational gameplay**
  - `QuizRoom` with pluggable `Quiz` types (`MathQuiz`, `StaticQuiz`)  
  - Example subjects: arithmetic, world capitals, riddles/logic
- **Adventure mechanics**
  - Inventory (`take`, `drop`), items with weights/descriptions
  - Item interactions (e.g., cookies, computers, chairs; beamer device)
  - `TransporterRoom` (random exit) and `ExamRoom` (math “boss” rooms)
  - Movement history with a stack (`back`)
- **Clean OOP**
  - Inheritance: `Room` → `TransporterRoom`, `QuizRoom`, `ExamRoom`
  - Encapsulation of puzzles via `Quiz` interface

---

## 🕹️ How to Play
You’ll see where you are, the available exits, and any items in the room.
Type commands at the prompt.

**Common flow**
```
> look
> go north
> take cookie
> back
> answer -1
> quit
```

When a **quiz** blocks your path, answer with:
```
answer <your-answer>
```
Examples:
```
answer -1
answer Paris
```

> If your parser only grabs a single word after `answer`, prefer short answers (`answer -1`, `answer Paris`). You can later extend `Parser` to support multi‑word answers like `answer New York`.

---

## ⌨️ Commands
(Your exact set may vary slightly by version.)

| Command | Purpose |
|---|---|
| `go <direction>` | Move to an adjacent room (`north`, `south`, `east`, `west`, etc.). |
| `look` | Reprint the current room’s long description and items. |
| `take <item>` | Pick up an item in the room. |
| `drop <item>` | Drop an item from your inventory. |
| `back` | Return to the previous room (stack-based). |
| `answer <text>` | Answer a quiz in a `QuizRoom`. |
| `eat <item>` | Eat consumable items (e.g., cookie). |
| `charge` | Charge the beamer (stores current location). |
| `fire` | Fire the beamer (teleport to stored location). |
| `help` | Show help text. |
| `quit` | Exit the game. |

---

## 🏗️ Build & Run

### Requirements
- Java 8+ (JDK)

### Compile & Run (terminal)
From the directory containing the `.java` files:

```bash
javac *.java
java Main
```

### BlueJ
- Open the project (or the directory), right‑click `Main` → `void main(String[] args)` → `OK`.

---

## 📁 Project Layout (suggested)
```
scholars-odyssey/
├─ src/                     # (optional) put sources here, or keep in root
│  ├─ Beamer.java
│  ├─ Command.java
│  ├─ CommandWords.java
│  ├─ ExamRoom.java
│  ├─ Game.java
│  ├─ Item.java
│  ├─ Main.java
│  ├─ MathQuiz.java
│  ├─ Parser.java
│  ├─ Quiz.java
│  ├─ QuizRoom.java
│  ├─ Room.java
│  ├─ StaticQuiz.java
│  └─ TransporterRoom.java
├─ README.md
├─ LICENSE
└─ .gitignore
```

**Recommended `.gitignore`:**
```gitignore
# Java
*.class
/out/
/bin/
/build/
/target/

# BlueJ
*.ctxt
bluej.pkg
bluej.pkh

# IDEs
.idea/
*.iml
.vscode/
.project
.classpath
.settings/

# OS
.DS_Store
Thumbs.db

# Javadoc (generated)
doc/
```

---

## 🗺️ Ideas to expand
- Multi‑word answers (update `Parser` to capture the rest of the line).
- Difficulty levels (larger number ranges, timed quizzes).
- Subject packs (science/spelling/history).
- Score/rank at the end (“Apprentice → Scholar → Master Explorer”).
- Save/Load game state.

---

## 🙏 Acknowledgements
- Inspired by the classic **BlueJ “Zuul”** text‑adventure exercises.

## 📄 License
MIT
