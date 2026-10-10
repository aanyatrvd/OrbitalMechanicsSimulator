 🌌 Orbital Mechanics Simulator

An interactive Java Swing desktop application that simulates the motion of the eight planets around the Sun using simplified orbital mechanics.

The project combines Object-Oriented Programming, GUI development, file handling, event handling, and physics-based calculations in one application.

🚀 Features

- Simulates all eight planets of the Solar System
- Animated planetary motion
- Start, Pause, and Reset controls
- Adjustable simulation speed
- Planet selection menu
- Displays planetary information dynamically
- Calculates:
  - Orbital velocity
  - Orbital period
  - Gravitational force
- Loads planetary data from a CSV file
- Uses nonlinear visual scaling so all planetary orbits can fit on screen
- Highlights the selected planet

🧠 Physics Used

The simulator uses simplified circular-orbit equations.

Gravitational Force

F = GMm / r²

Where:

- G = Gravitational constant
- M = Mass of the Sun
- m = Mass of the planet
- r = Orbital radius

Orbital Velocity

v = √(GM / r)

Orbital Period

T = 2π√(r³ / GM)

Planet Position

The angular position of a planet is calculated using:

θ(t) = θ₀ + ωt

The Cartesian coordinates are then calculated using:

x = r cos(θ)

y = r sin(θ)

💻 Technologies Used

- Java 21
- Java Swing
- Java AWT
- IntelliJ IDEA
- CSV File Handling

🧩 Object-Oriented Programming Concepts

Inheritance

Star and Planet inherit common properties from the abstract CelestialBody class.

CelestialBody
   │
   ├── Star
   │
   └── Planet

Encapsulation

Planetary properties such as mass, radius, and orbital radius are stored inside classes and accessed using getter methods.

Polymorphism

The abstract getInformation() method is implemented differently by Star and Planet.

Abstraction

Physics calculations are handled by a separate OrbitalMechanics class so the GUI does not need to directly implement mathematical equations.

📁 Project Structure

OrbitalMechanicsSimulator/
│
├── data/
│   └── planets.csv
│
├── src/
│   ├── CelestialBody.java
│   ├── Star.java
│   ├── Planet.java
│   ├── OrbitalMechanics.java
│   ├── PlanetDataLoader.java
│   ├── SimulationPanel.java
│   ├── InfoPanel.java
│   ├── MainFrame.java
│   └── Main.java
│
├── .gitignore
└── OrbitalMechanicsSimulator.iml

📄 Planet Data

Planet information is stored inside:

data/planets.csv

The file contains:

- Name
- Mass
- Radius
- Orbital Radius
- Initial Angle

The PlanetDataLoader class reads this file and creates the planet objects when the program starts.

▶️ How to Run

1. Clone the repository:

git clone https://github.com/aanyatrvd/OrbitalMechanicsSimulator.git

2. Open the project in IntelliJ IDEA.

3. Make sure Java 21 or another compatible JDK is configured.

4. Run Main.java.

5. The Orbital Mechanics Simulator window will open.

🎮 Controls

Start
Starts the planetary simulation.

Pause
Pauses planetary motion.

Reset
Resets simulation time.

Speed
Changes simulation speed.

Select Planet
Displays information for a selected planet.

At 1x speed:

1 real second ≈ 1 simulated day

Higher speeds make the motion of the outer planets easier to observe.

⚠️ Simplifications

This project is intended as an educational orbital mechanics simulator and is not a full astronomical N-body simulation.

The current model assumes:

- Circular planetary orbits
- The Sun remains fixed at the center
- Planet-to-planet gravitational forces are ignored
- Orbital inclination is not simulated
- Planet sizes and orbital distances are visually scaled

🔮 Future Improvements

Possible future additions include:

- Elliptical orbits
- N-body gravitational simulation
- Planet trails
- Zoom and pan functionality
- Click-to-select planets
- Moons and dwarf planets
- Velocity and gravitational force vectors
- Editable planetary parameters
- 3D visualization

👩‍💻 Author

Aanya Trivedi

Registration Number: RA2511003030212

2nd Year CSE Core D

📌 Project Purpose

This project was developed as a Java academic project to demonstrate the practical use of:

- Object-Oriented Programming
- Java Swing GUI
- Event Handling
- File Handling
- Exception Handling
- Mathematical Simulation
- Physics-based computation
