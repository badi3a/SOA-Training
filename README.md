# 🧪 Workshop REST n°1 — SOA_2025_ahmed was here


## 📘 Overview
In this workshop, you will develop a **RESTful Web Service** in **Java (JAX-RS)** to expose business methods for two entities:
- `UniteEnseignement` (Teaching Unit)
- `Module`

You will follow the same approach as the **pilot example** (`HelloRessources`) studied in class, but instead of returning simple text, you will expose **CRUD operations** and **search features** for these entities.

---

## 🎯 Objectives
- Understand how to model entities (`Module`, `UniteEnseignement`) in Java.  
- Expose CRUD and query endpoints using **JAX-RS resources**.  
- Separate **business logic** (controller/service) from **resource classes**.  
- Deploy a **Maven WAR** to Tomcat and test with **Postman**.  
- Apply **REST best practices** (status codes, content types).  

---

## 🧠 Context
The university is modernizing its academic system.  
Currently, students, teachers, and administrators use a web portal for:
- module registration,
- grades consultation,
- schedule management,
- internal communication.  

The goal is to **expose some of these features via a REST API** to enable interoperability and mobile client support.

---

## 🧩 Entities

### 1. `UniteEnseignement`
```java
package entities;

public class UniteEnseignement {
    private int code;
    private String domaine;
    private String responsable;
    private int credits;
    private int semestre;

    public UniteEnseignement() {}
    public UniteEnseignement(int code, String domaine, String responsable, int credits, int semestre) {
        this.code = code;
        this.domaine = domaine;
        this.responsable = responsable;
        this.credits = credits;
        this.semestre = semestre;
    }
    // Getters & Setters
}
```

### 2. `Module`
- Attributes: `matricule`, `nom`, `coefficient`, `volumeHoraire`, `type`, `uniteEnseignement`.  
- Enum `TypeModule`: `TRANSVERSAL`, `PROFESSIONNEL`, `RECHERCHE`.  
- Implement `equals()` and `hashCode()`.  

---

## ⚙️ Project Setup
1. Create a **Maven project** in IntelliJ with packaging type `war`.  
2. Add dependencies:  
   - `javax.servlet:javax.servlet-api:4.0.1`  
   - JAX-RS implementation (e.g., Jersey).  
3. Add a JAX-RS Activator:  
   ```java
   @ApplicationPath("/api")
   public class RestActivator extends Application {}
   ```
4. Project structure:
```
src/main/java
  ├─ entities
  ├─ business       // service classes
  └─ webservices    // REST resources
```

---

## 🔗 Endpoints to Implement

### A) `UniteEnseignement` — `/UE`
- `POST /UE` → Create a new teaching unit (XML input).  
- `GET /UE` → List all teaching units (JSON output).  
- `GET /UE?semestre=2` → List teaching units for a semester.  
- `GET /UE?code=123` → Get UE by code.  
- `PUT /UE/{id}` → Update UE (XML input).  
- `DELETE /UE/{id}` → Delete UE.  

### B) `Module` — `/modules`
- `POST /modules` → Create module (JSON input).  
- `GET /modules` → List all modules (JSON output).  
- `GET /modules/{matricule}` → Get module by matricule.  
- `PUT /modules/{matricule}` → Update module (JSON input).  
- `DELETE /modules/{matricule}` → Delete module.  
- `GET /modules/UE?codeUE=1` → List all modules of a given UE.  

---

## 🧪 Testing
- Use **Postman** to test all endpoints.  
- Input formats:
  - `application/xml` for UE creation/update.  
  - `application/json` for modules.  
- Output format: `application/json`.  
- Return proper HTTP codes (`200 OK`, `201 Created`, `404 Not Found`, `400 Bad Request`).  

---

## 📦 Deliverables
- A complete Maven project with:
  - Entities (`UniteEnseignement`, `Module`).  
  - Business layer (services).  
  - REST Resources.  
- Postman collection + screenshots for each endpoint.  
- Updated `README.md` with your group details.  

---

## 📝 Submission
- Create a **GitHub repo** for your team.  
- Push your project and Postman files.  
- Submit your repository URL on the LMS.  

---

## 🧮 Evaluation
| Criterion | Weight |
|-----------|---------|
| Correct endpoints & paths | 25% |
| HTTP status codes & media types | 15% |
| Business logic separation | 20% |
| CRUD implementation | 20% |
| Tests (Postman evidence) | 15% |
| Repo & documentation quality | 5% |

---

## 🚀 Next Steps
Once your REST API is working, we will later explore:
- **Interoperability** (connecting with clients).  
- **Security** with JWT.  
- **GraphQL** as an alternative to REST.
- ---
### 👨‍🏫 Instructor
- **[Badia Bouhdid](https://www.linkedin.com/in/badiabouhdid)**
---

🏫 This training is delivered as part of the **Client-Side Application 1** module at [Esprit School of Engineering](https://www.esprit.tn)
