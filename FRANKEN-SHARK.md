# Franken-Shark Branch Guide

Instructions for creating the `franken-shark` PR branch that introduces architectural violations.

## Violations to Introduce

### Violation 1: Tangle — `digestive` ↔ `circulatory`

**Story:** "The stomach needs blood flow to digest food, and the blood needs nutrients from digestion."

**Change:** Make `Stomach.java` import from `circulatory`, creating a cycle with `BloodStream.java` (which already imports from `respiratory`).

In `Stomach.java`, add:
```java
import com.sharkitecture.circulatory.BloodStream;
```

Add a method:
```java
public List<Nutrient> digest(String prey, BloodStream bloodStream) {
    double oxygen = bloodStream.deliverOxygen(0.5);
    if (oxygen < 0.1) {
        return List.of(); // can't digest without blood flow
    }
    return digest(prey);
}
```

Then in `BloodStream.java`, add:
```java
import com.sharkitecture.digestive.Nutrient;
```

Add a method:
```java
public void absorbNutrients(List<Nutrient> nutrients) {
    for (Nutrient n : nutrients) {
        this.oxygenLevel += n.getEnergyKcal() * 0.001;
    }
}
```

**Result:** `digestive` → `circulatory` → `respiratory` AND `circulatory` → `digestive` = **TANGLE**

---

### Violation 2: Tangle — `skeleton` ↔ `locomotion`

**Story:** "The cartilage wants to know which fin it supports."

**Change:** Make `Cartilage.java` import from `locomotion`.

In `Cartilage.java`, add:
```java
import com.sharkitecture.locomotion.Muscle;

public Muscle getAttachedMuscle() {
    return new Muscle(Muscle.FiberType.RED_SLOW_TWITCH, 1.0, this);
}
```

**Result:** `skeleton` → `locomotion` → `skeleton` = **TANGLE**

---

### Violation 3: Forbidden Dependency — `skeleton` → `brain`

**Story:** "The jaw wants to communicate pain directly to the brain."

**Change:** In `Jaw.java`, add:
```java
import com.sharkitecture.brain.NeuralSignal;

public NeuralSignal reportPain(double intensity) {
    return new NeuralSignal(NeuralSignal.Command.BRAKE, intensity);
}
```

**Result:** `skeleton` → `brain` = **FORBIDDEN** (bones shouldn't think!)

---

### Violation 4: Forbidden Dependency — `sensory` → `digestive`

**Story:** "When the lateral line detects prey vibrations, it triggers hunger."

**Change:** In `LateralLine.java`, add:
```java
import com.sharkitecture.digestive.Stomach;

public void triggerHunger(Stomach stomach, String nearbyPrey) {
    if (!stomach.isFull()) {
        stomach.digest(nearbyPrey);
    }
}
```

**Result:** `sensory` → `digestive` = **FORBIDDEN** (sensors shouldn't control digestion)

---

## What SonarQube Will Detect

After defining the intended architecture on `main`:

| Issue Type | Location | Description |
|------------|----------|-------------|
| Tangle | `digestive` ↔ `circulatory` | Circular dependency between digestion and blood |
| Tangle | `skeleton` ↔ `locomotion` | Circular dependency between structure and movement |
| Wrong Dependency | `skeleton` → `brain` | Jaw shouldn't talk to brain directly |
| Wrong Dependency | `sensory` → `digestive` | Sensors shouldn't control digestion |
