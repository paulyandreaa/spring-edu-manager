package com.iseg.util;

public enum CarreraUtil {
    INGENIERIA_INFORMATICA("Ingeniería en Informática"),
    INGENIERIA_CIVIL("Ingeniería Civil"),
    CONTADOR_AUDITOR("Contador Auditor"),
    INGENIERIA_COMERCIAL("Ingeniería Comercial"),
    DERECHO("Derecho"),
    MEDICINA("Medicina"),
    ENFERMERIA("Enfermería"),
    PSICOLOGIA("Psicología"),
    ARQUITECTURA("Arquitectura"),
    PEDAGOGIA("Pedagogía"),
    INGENIERIA_EJECUCION("Ingeniería de Ejecución"),
    TECNOLOGIA_MEDICA("Tecnología Médica"),
    KINESIOLOGIA("Kinesiología"),
    TRABAJO_SOCIAL("Trabajo Social"),
    PERIODISMO("Periodismo");

    private final String descripcion;

    CarreraUtil(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}