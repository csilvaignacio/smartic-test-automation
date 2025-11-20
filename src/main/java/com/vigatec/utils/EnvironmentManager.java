package com.vigatec.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Gestor de variables de entorno para manejo seguro de credenciales.
 * Esta clase proporciona métodos para obtener y resolver contraseñas
 * desde variables de entorno del sistema.
 *
 * @author Vigatec
 * @version 1.0
 */
public class EnvironmentManager {

    private static final Logger logger = LogManager.getLogger(EnvironmentManager.class);

    /**
     * Obtiene el valor de una variable de entorno.
     *
     * @param key Nombre de la variable de entorno
     * @return Valor de la variable de entorno
     * @throws RuntimeException si la variable no existe o está vacía
     */
    public static String getEnvironmentVariable(String key) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            logger.error("Variable de entorno no encontrada o vacía: {}", key);
            throw new RuntimeException("Variable de entorno no configurada: " + key +
                ". Por favor, configure la variable antes de ejecutar las pruebas.");
        }
        logger.debug("Variable de entorno '{}' obtenida correctamente", key);
        return value;
    }

    /**
     * Resuelve un valor de contraseña, que puede ser:
     * - Una referencia a variable de entorno en formato ${VAR_NAME}
     * - Un valor literal (usado para datos de prueba inválidos)
     *
     * @param passwordField Valor del campo password (puede ser ${VAR} o literal)
     * @return La contraseña resuelta
     */
    public static String resolvePassword(String passwordField) {
        if (passwordField == null || passwordField.trim().isEmpty()) {
            logger.warn("Campo de contraseña vacío o null");
            return "";
        }

        // Si tiene formato ${VAR}, buscar en variables de entorno
        if (passwordField.startsWith("${") && passwordField.endsWith("}")) {
            String envVar = passwordField.substring(2, passwordField.length() - 1);
            logger.debug("Resolviendo contraseña desde variable de entorno: {}", envVar);
            return getEnvironmentVariable(envVar);
        }

        // Si no, retornar tal cual (para usuarios inválidos de prueba)
        logger.debug("Usando contraseña literal (usuario de prueba inválido)");
        return passwordField;
    }

    /**
     * Verifica si una variable de entorno está configurada.
     *
     * @param key Nombre de la variable de entorno
     * @return true si existe y no está vacía, false en caso contrario
     */
    public static boolean isEnvironmentVariableSet(String key) {
        String value = System.getenv(key);
        boolean isSet = value != null && !value.trim().isEmpty();
        logger.debug("Variable '{}' configurada: {}", key, isSet);
        return isSet;
    }

    /**
     * Obtiene una variable de entorno con un valor por defecto.
     *
     * @param key Nombre de la variable de entorno
     * @param defaultValue Valor por defecto si la variable no existe
     * @return Valor de la variable o el valor por defecto
     */
    public static String getEnvironmentVariable(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.trim().isEmpty()) {
            logger.debug("Variable '{}' no encontrada, usando valor por defecto", key);
            return defaultValue;
        }
        logger.debug("Variable de entorno '{}' obtenida correctamente", key);
        return value;
    }
}
