# Sharkitecture

**A shark anatomy demo for SonarQube Cloud Architecture.**

This project models the internal systems of a Great White Shark as a Java application with clean, well-layered package architecture. It's designed to demonstrate how SonarQube Cloud's Architecture features detect and prevent structural erosion.

## The Anatomy

Each package represents a biological system of the shark:

```
com.sharkitecture
├── brain          → Central processing (depends on: sensory, locomotion)
├── sensory        → Electroreception & lateral line (independent)
├── circulatory    → Heart & blood (depends on: respiratory)
├── respiratory    → Gills & gas exchange (independent)
├── digestive      → Stomach & spiral valve intestine (independent)
├── locomotion     → Fins & muscles (depends on: skeleton)
├── skeleton       → Cartilage & jaw (independent)
└── Shark.java     → Composes all systems
```

## Clean Dependency Graph

```
brain ──→ sensory
  │
  └────→ locomotion ──→ skeleton
              
circulatory ──→ respiratory

digestive (independent)
```

Dependencies flow in one direction. No tangles. No forbidden cross-talk.

## Demo Flow

### 1. Analyze Main Branch (Clean Shark)

The `main` branch represents a well-evolved shark — millions of years of natural selection produced clean architecture:

- **No tangles** (circular dependencies)
- **Clear layering** — the brain reads sensors and controls movement, never the reverse
- **Proper separation** — digestive, respiratory, and sensory systems are independent

In SonarQube Cloud:
- View the **Structure Map** → clean left-to-right flow
- Define the **Intended Architecture** → add containers, set allowed relationships
- Zero architecture issues

### 2. Create a PR (Franken-Shark)

Create a PR from the `franken-shark` branch that introduces architectural mutations:

| Violation | What | Why It's Bad |
|-----------|------|-------------|
| **Tangle** | `digestive` ↔ `circulatory` | Circular dependency: stomach needs blood flow, blood carries nutrients — creates a cycle |
| **Tangle** | `skeleton` ↔ `locomotion` | Circular dependency: cartilage references fins, fins reference cartilage |
| **Forbidden Dep** | `skeleton` → `brain` | Cartilage shouldn't think! |
| **Forbidden Dep** | `sensory` → `digestive` | Sensors shouldn't control digestion |

### 3. SonarQube Catches the Mutations

The PR analysis will detect:
- **Tangles** automatically (no configuration needed)
- **Architecture deviations** based on the intended architecture you defined
- Quality gate blocks the PR

### 4. The Punchline

> "Millions of years of evolution gave the shark clean architecture.
> One pull request from Dr. Franken-Shark almost ruined it.
> But SonarQube was watching."

## Setup

### Prerequisites
- Java 17+
- Maven 3.8+
- A SonarQube Cloud account

### Build
```bash
mvn clean compile
```

### Run
```bash
mvn exec:java -Dexec.mainClass="com.sharkitecture.Shark"
```

### SonarQube Cloud Configuration

1. Create a project in SonarQube Cloud
2. Set repository secrets:
   - `SONAR_TOKEN` — your SonarQube Cloud token
3. Set repository variables:
   - `SONAR_PROJECT_KEY` — your project key
   - `SONAR_ORG` — your organization key
4. Push to `main` and watch the analysis run

### Defining the Intended Architecture

In SonarQube Cloud, go to **Architecture → Intended Architecture** and:

1. Add the top-level containers (`brain`, `sensory`, `circulatory`, `respiratory`, `digestive`, `locomotion`, `skeleton`)
2. Define allowed relationships:
   - `brain` → `sensory` (brain reads sensor data)
   - `brain` → `locomotion` (brain controls movement)
   - `circulatory` → `respiratory` (heart pumps blood to gills)
   - `locomotion` → `skeleton` (muscles attach to cartilage)
3. Save — any undeclared dependency is now **forbidden**
4. Create the `franken-shark` PR and watch it get blocked
