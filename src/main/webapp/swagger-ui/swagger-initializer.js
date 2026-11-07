window.onload = function() {
    //<editor-fold desc="Changeable Configuration Block">

    // Charger ton OpenAPI JSON
    window.ui = SwaggerUIBundle({
        url: "/Gestion_UE_VF_war_exploded/api/openapi.json", // <-- ton endpoint OpenAPI
        dom_id: '#swagger-ui',
        deepLinking: true,
        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],
        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],
        layout: "StandaloneLayout"
    });

    //</editor-fold>
};
