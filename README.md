# **Microservices Testing Project**

Ce projet illustre une stratégie complète de tests pour une architecture basée sur des microservices. Chaque étape garantit la qualité et la robustesse des composants individuels et de leur intégration dans le système global.  

---

## **Étapes de Tests**

### 1️⃣ **Unit Tests**  
- Validation isolée des composants individuels.  
- Outils : **Mockito**, **JUnit 5**, **AssertJ**.  

### 2️⃣ **Component Tests**  
- Vérification des comportements internes des composants.  

### 3️⃣ **Integration Tests (Microservice Level)**  
- Tests des interactions internes au sein de chaque microservice.  

### 4️⃣ **Contract Tests**  
- Vérification des contrats API entre les microservices pour garantir la compatibilité des interfaces.  

### 5️⃣ **Integration Tests (Application Level)**  
- Validation des interactions globales entre microservices au niveau de l'application.  

### 6️⃣ **End-to-End Tests**  
- Simulation des parcours utilisateurs et vérification du comportement global du système.  

---


![Sans titre](https://github.com/user-attachments/assets/c430d6f8-f8ad-448b-8cdd-8d1ce80f2624)


---

## **Microservices Testés**

### **Customer-Service**  
- Tests sur la gestion des clients (création, modification, suppression).  

### **Product-Service**  
- Tests sur la gestion des produits (ajout, modification, suppression).  

---

## **Outils et Technologies**
- **Backend** : Maven, Java 21, Spring Boot, Spring Cloud, Spring Data JPA, Lombok, ModelMapper.
- **Base de données** : H2 Database, Postgresql.  
- **Tests Unitaires** : Mockito, JUnit 5, AssertJ.  
- **Containerisation** : Docker Compose Support, Test Containers.  

---

## **Comment Exécuter les Tests**

1. **Cloner le dépôt** :  
   ```bash
   git clone https://github.com/Ahmed-Tombari/UnitTest-Micro-Service-App.git
   cd UnitTest-Micro-Service-App

