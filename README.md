# 🚀 Smartic WebApp - Framework de Automatización de Pruebas

Framework de automatización de pruebas E2E para la aplicación **GestionAsistencia** desarrollado con Selenium WebDriver, TestNG y Allure Reports.

---

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración de Variables de Entorno](#-configuración-de-variables-de-entorno)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Ejecución de Pruebas](#-ejecución-de-pruebas)
- [Reportes](#-reportes)
- [Arquitectura](#-arquitectura)
- [Troubleshooting](#-troubleshooting)

---

## ✨ Características

- ✅ **Page Object Model (POM)** - Arquitectura mantenible y escalable
- ✅ **Multi-navegador** - Soporte para Chrome y Firefox
- ✅ **Allure Reports** - Reportes visuales y detallados
- ✅ **ThreadLocal** - Soporte para ejecución paralela
- ✅ **Log4j2** - Sistema de logging robusto
- ✅ **Data Driven** - Parametrización con JSON
- ✅ **WebDriverManager** - Gestión automática de drivers
- ✅ **Seguridad** - Credenciales en variables de entorno

---

## 🔧 Requisitos Previos

### Software Requerido

| Herramienta | Versión | Descarga |
|------------|---------|----------|
| **Java JDK** | 17+ | [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) |
| **Maven** | 3.6+ | [Apache Maven](https://maven.apache.org/download.cgi) |
| **Git** | 2.0+ | [Git SCM](https://git-scm.com/downloads) |
| **Chrome/Firefox** | Última | Navegador instalado |

### Verificar Instalación

```bash
# Verificar Java
java -version
# Salida esperada: openjdk version "17.x.x"

# Verificar Maven
mvn -version
# Salida esperada: Apache Maven 3.x.x

# Verificar Git
git --version
# Salida esperada: git version 2.x.x
```

---

## 📥 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/vigatec/smartic-webapp.git
cd smartic-webapp
```

### 2. Instalar Dependencias

```bash
mvn clean install -DskipTests
```

✅ Esto descargará todas las dependencias del [pom.xml](pom.xml) automáticamente.

---

## 🔐 Configuración de Variables de Entorno

Por razones de **seguridad**, las contraseñas NO se almacenan en el repositorio. Debes configurar variables de entorno en tu sistema.

### Windows

#### Opción A: PowerShell (Permanente)
```powershell
[System.Environment]::SetEnvironmentVariable('VALID_USER_PASSWORD', 'TU_PASSWORD_AQUI', 'User')
```

#### Opción B: CMD (Permanente)
```cmd
setx VALID_USER_PASSWORD "TU_PASSWORD_AQUI"
```

#### Opción C: Configuración Manual
1. Presiona `Windows + R`
2. Escribe `sysdm.cpl` y presiona Enter
3. Ve a **"Opciones avanzadas"** → **"Variables de entorno"**
4. En **"Variables de usuario"**, clic en **"Nueva..."**
5. Nombre: `VALID_USER_PASSWORD`
6. Valor: Tu contraseña real
7. Clic en **"Aceptar"**

### Linux / macOS

#### Temporal (solo sesión actual)
```bash
export VALID_USER_PASSWORD="TU_PASSWORD_AQUI"
```

#### Permanente
Agrega al archivo `~/.bashrc` o `~/.zshrc`:
```bash
echo 'export VALID_USER_PASSWORD="TU_PASSWORD_AQUI"' >> ~/.bashrc
source ~/.bashrc
```

### Verificar Configuración

```bash
# Windows (PowerShell)
echo $env:VALID_USER_PASSWORD

# Windows (CMD)
echo %VALID_USER_PASSWORD%

# Linux / macOS
echo $VALID_USER_PASSWORD
```

⚠️ **IMPORTANTE:** Después de configurar variables de entorno, **reinicia tu terminal o IDE**.

---

## 📁 Estructura del Proyecto

```
smartic-webapp/
│
├── src/
│   ├── main/
│   │   ├── java/com/vigatec/
│   │   │   ├── core/
│   │   │   │   ├── base/
│   │   │   │   │   ├── BaseTest.java           # Clase base para todos los tests
│   │   │   │   │   └── BasePage.java           # Clase base Page Object Model
│   │   │   │   ├── drivers/
│   │   │   │   │   ├── DriverManager.java      # Gestión abstracta de drivers
│   │   │   │   │   ├── DriverManagerFactory.java
│   │   │   │   │   └── browser/
│   │   │   │   │       ├── ChromeDriverManager.java
│   │   │   │   │       └── FireFoxDriverManager.java
│   │   │   │   └── enums/
│   │   │   │       ├── DriverType.java         # CHROME, FIREFOX
│   │   │   │       └── EnvType.java            # QA, STAG
│   │   │   │
│   │   │   ├── modules/                        # Page Objects
│   │   │   │   ├── common/
│   │   │   │   │   └── LoginPage.java
│   │   │   │   └── company/
│   │   │   │       └── CompanySelectionPage.java
│   │   │   │
│   │   │   ├── models/users/
│   │   │   │   ├── User.java                   # POJO Usuario
│   │   │   │   └── UserData.java
│   │   │   │
│   │   │   ├── dataproviders/
│   │   │   │   └── TestDataProviders.java      # Data providers TestNG
│   │   │   │
│   │   │   └── utils/
│   │   │       ├── ConfigLoader.java           # Singleton configuración
│   │   │       ├── PropertiesUtils.java
│   │   │       └── EnvironmentManager.java     # Manejo variables de entorno
│   │   │
│   │   └── resources/
│   │       ├── config/
│   │       │   └── QA.properties               # Configuración ambiente QA
│   │       └── log4j2.xml                      # Configuración logging
│   │
│   └── test/
│       ├── java/login/
│       │   └── LoginTest.java                  # Suite de pruebas Login
│       └── resources/testdata/
│           └── users.json                      # Datos de prueba
│
├── pom.xml                                     # Configuración Maven
├── testng.xml                                  # Suite TestNG
├── .gitignore                                  # Archivos ignorados por Git
└── README.md                                   # Este archivo
```

### Carpetas Generadas Automáticamente (NO en Git)

Estas carpetas se crean automáticamente al ejecutar el proyecto:

- `target/` - Archivos compilados (generado por Maven)
- `drivers/` - Drivers de navegadores (generado por WebDriverManager)
- `logs/` - Archivos de log (generado por Log4j2)
- `allure-results/` - Resultados de pruebas (generado por Allure)

---

## ▶️ Ejecución de Pruebas

### Ejecutar Todas las Pruebas

```bash
mvn clean test
```

### Ejecutar con Navegador Específico

```bash
# Chrome (por defecto)
mvn clean test -Dbrowser=CHROME

# Firefox
mvn clean test -Dbrowser=FIREFOX
```

### Ejecutar Prueba Específica

```bash
mvn test -Dtest=LoginTest#loginWhitValidUser
```

### Ejecutar con Perfil Específico

```bash
mvn clean test -Pregression
```

### Modo Debug (Más Logging)

```bash
mvn clean test -X
```

---

## 📊 Reportes

### Allure Reports

#### Generar y Abrir Reporte

```bash
# Ejecutar pruebas y generar reporte
mvn clean test

# Abrir reporte en navegador
mvn allure:serve
```

#### Generar Reporte Estático

```bash
mvn allure:report
```

Los reportes se generan en: `target/site/allure-maven-plugin/`

### Logs

Los logs se guardan en: `logs/automation.log`

Ver logs en tiempo real:
```bash
# Windows (PowerShell)
Get-Content logs/automation.log -Tail 50 -Wait

# Linux / macOS
tail -f logs/automation.log
```

---

## 🏗️ Arquitectura

### Patrones de Diseño Implementados

#### 1. Page Object Model (POM)
Cada página web se representa como una clase Java con sus elementos y acciones.

**Ejemplo:**
```java
public class LoginPage extends BasePage {
    @FindBy(css = "#input-username")
    private WebElement usernameField;

    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }
}
```

#### 2. Factory Pattern
Creación dinámica de drivers según el navegador.

```java
DriverManager manager = DriverManagerFactory.getManager(DriverType.CHROME);
```

#### 3. Singleton Pattern
Configuración centralizada de propiedades.

```java
String baseUrl = ConfigLoader.getInstance().getBaseUrl();
```

#### 4. Fluent Interface
Encadenamiento de métodos para código más legible.

```java
new LoginPage(driver)
    .enterUsername("user@test.com")
    .enterPassword("password")
    .login();
```

### Gestión de Threads

El framework usa `ThreadLocal` para aislar drivers por thread, permitiendo ejecución paralela:

```java
protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
```

---

## 🔧 Configuración

### Archivo: `QA.properties`

```properties
# URLs del ambiente
url.base=https://s001606-cl-lnast-qa.gestionasistencia.cl/auth
url.company=https://s001606-cl-lnast-qa.gestionasistencia.cl/change-organizations

# Navegador por defecto
browser=chrome

# Timeouts (segundos)
explicit.wait=15

# Selenium Grid (si aplica)
selenium.grid.url=http://localhost:4444/wd/hub
remote.execution=false

# Reportes
screenshot.on.failure=true
report.path=target/reports
```

### Archivo: `testng.xml`

```xml
<suite name="Vigatec Test Suite">
    <test name="Login Tests">
        <parameter name="browser" value="CHROME"/>
        <classes>
            <class name="login.LoginTest"/>
        </classes>
    </test>
</suite>
```

---

## 🧪 Crear Nuevas Pruebas

### 1. Crear Page Object

```java
package com.vigatec.modules.mimodulo;

import com.vigatec.core.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiPaginaPage extends BasePage {

    public MiPaginaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#mi-elemento")
    private WebElement miElemento;

    public MiPaginaPage load() {
        load("/mi-ruta");
        return this;
    }

    public void clickMiElemento() {
        clickWhenReady(miElemento);
    }
}
```

### 2. Crear Test

```java
package mitests;

import com.vigatec.core.base.BaseTest;
import org.testng.annotations.Test;

public class MiTest extends BaseTest {

    @Test
    public void miPrueba() {
        new MiPaginaPage(getDriver())
            .load()
            .clickMiElemento();
    }
}
```

---

## ❓ Troubleshooting

### Problema: "Variable de entorno no configurada"

**Error:**
```
RuntimeException: Variable de entorno no configurada: VALID_USER_PASSWORD
```

**Solución:**
1. Verifica que configuraste la variable de entorno
2. Reinicia tu terminal/IDE después de configurar
3. Verifica con: `echo $env:VALID_USER_PASSWORD` (Windows) o `echo $VALID_USER_PASSWORD` (Linux/Mac)

---

### Problema: Driver no encontrado

**Error:**
```
SessionNotCreatedException: Could not start a new session
```

**Solución:**
```bash
# Limpiar caché de drivers
rm -rf ~/.cache/selenium
# o en Windows
rmdir /s %USERPROFILE%\.cache\selenium

# Reinstalar dependencias
mvn clean install -DskipTests
```

---

### Problema: Tests fallan por timeout

**Solución:**
Aumenta el timeout en [QA.properties](src/main/resources/config/QA.properties):
```properties
explicit.wait=30
```

---

### Problema: Puerto ya en uso (Allure)

**Error:**
```
Address already in use: bind
```

**Solución:**
```bash
# Especificar otro puerto
mvn allure:serve -Dallure.serve.port=8081
```

---

## 🤝 Contribución

### Flujo de Trabajo

1. **Fork** el repositorio
2. Crea una **rama** para tu feature: `git checkout -b feature/nueva-funcionalidad`
3. **Commit** tus cambios: `git commit -m "Add: nueva funcionalidad"`
4. **Push** a la rama: `git push origin feature/nueva-funcionalidad`
5. Abre un **Pull Request**

### Convenciones de Commits

```
Add: Nueva funcionalidad
Update: Mejora de funcionalidad existente
Fix: Corrección de bug
Refactor: Refactorización de código
Test: Agregar o modificar pruebas
Docs: Documentación
```

---

## 📞 Contacto

**Equipo Vigatec**
- Email: contacto@vigatec.com
- URL Aplicación: https://s001606-cl-lnast-qa.gestionasistencia.cl

---

## 📄 Licencia

Copyright © 2024 Vigatec. Todos los derechos reservados.

---

## 🎓 Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|-----------|---------|-----------|
| Java | 17 | Lenguaje base |
| Selenium WebDriver | 4.35.0 | Automatización UI |
| TestNG | 7.11.0 | Framework de pruebas |
| Allure Reports | 2.29.1 | Reportes visuales |
| Maven | 3.x | Build tool |
| Log4j2 | 2.20.0 | Logging |
| WebDriverManager | 6.2.0 | Gestión de drivers |
| Jackson | 2.16.0 | Parsing JSON |

---

**¡Happy Testing!** 🚀
