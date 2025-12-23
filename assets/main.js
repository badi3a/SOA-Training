// -----------------------------
// Data: adapte les "href" à ton repo
// Exemple: "./Workshop-REST/" si tu as un dossier, ou vers README: "./Workshop-REST/README.md"
// -----------------------------
const items = [
    {
        type: "workshop",
        title: "Workshop 1 — Analyse & Refonte d’une application",
        desc: "Analyse d’une application existante et proposition d’une refonte orientée services.",
        tag: "Projet",
        href: "https://github.com/badi3a/SOA-Training/Workshop-ProjectStudy/"
    },
    {
        type: "lab",
        title: "Atelier SOAP — Consommation avec SoapUI",
        desc: "Découverte du WSDL et consommation d’un service SOAP via SoapUI.",
        tag: "SOAP",
        href: "https://github.com/badi3a/SOA-Training/Workshop-SOAP/"
    },
    {
        type: "lab",
        title: "Atelier REST — Mise en place d’un service RESTful",
        desc: "Conception de ressources REST et mise en œuvre d’une API CRUD.",
        tag: "REST",
        href: "https://github.com/badi3a/SOA-Training/tree/Workshop-ProjectStudy"
    },
    {
        type: "workshop",
        title: "Workshop Sécurité — JAX-RS Security (JWT)",
        desc: "Sécurisation des ressources REST par authentification basée sur token (JWT).",
        tag: "Security",
        href: "https://github.com/badi3a/SOA-Training/tree/workshop-security"
    },
    {
        type: "workshop",
        title: "Workshop GraphQL — Mise en place d’une API GraphQL",
        desc: "Conception du schéma GraphQL et opérations Query/Mutation, tests via client.",
        tag: "GraphQL",
        href: "https://github.com/badi3a/SOA-Training/tree/workshop-graphQL"
    },
    // (Optionnel) si tu as des cours en dossiers:
    {
        type: "course",
        title: "Cours — Introduction SOA",
        desc: "Fondations, évolution du Web, paradigmes architecturaux, services & middleware.",
        tag: "Cours",
        href: "https://github.com/badi3a/SOA-Training/blob/main/Présentation_Module_SOA.pdf"
    }
];

function badgeClass(type) {
    if (type === "course") return "text-bg-secondary";
    if (type === "lab") return "text-bg-info";
    return "text-bg-primary";
}

function iconFor(tag){
    const t = (tag || "").toLowerCase();
    if (t.includes("soap")) return "bi-diagram-2";
    if (t.includes("rest")) return "bi-globe2";
    if (t.includes("graphql")) return "bi-bezier2";
    if (t.includes("security")) return "bi-shield-lock";
    if (t.includes("projet")) return "bi-kanban";
    return "bi-journal";
}

function renderCards(filter = "all") {
    const container = document.getElementById("cardsContainer");
    container.innerHTML = "";

    const filtered = items.filter(it => filter === "all" ? true : it.type === filter);

    filtered.forEach(it => {
        const col = document.createElement("div");
        col.className = "col-md-6 col-lg-4";

        col.innerHTML = `
      <a class="card card-link border-0 shadow-soft h-100 text-decoration-none" href="${it.href}">
        <div class="card-body p-4">
          <div class="d-flex justify-content-between align-items-start gap-2">
            <span class="badge ${badgeClass(it.type)} text-uppercase">${it.type}</span>
            <i class="bi ${iconFor(it.tag)} fs-5 text-primary"></i>
          </div>
          <h3 class="h6 fw-semibold mt-3 mb-2">${it.title}</h3>
          <p class="text-body-secondary mb-3">${it.desc}</p>
          <div class="d-flex justify-content-between align-items-center">
            <span class="chip">${it.tag}</span>
            <span class="text-primary small">Ouvrir <i class="bi bi-arrow-right"></i></span>
          </div>
        </div>
      </a>
    `;

        container.appendChild(col);
    });
}

// Theme toggle
function initThemeToggle() {
    const btn = document.getElementById("toggleTheme");
    const icon = document.getElementById("themeIcon");

    const saved = localStorage.getItem("soa_theme");
    if (saved === "dark") {
        document.body.classList.add("theme-dark");
        icon.className = "bi bi-moon-stars-fill";
    }

    btn?.addEventListener("click", () => {
        document.body.classList.toggle("theme-dark");
        const dark = document.body.classList.contains("theme-dark");
        localStorage.setItem("soa_theme", dark ? "dark" : "light");
        icon.className = dark ? "bi bi-moon-stars-fill" : "bi bi-sun-fill";
    });
}

// Filter
function initFilter() {
    const sel = document.getElementById("filterType");
    sel?.addEventListener("change", (e) => renderCards(e.target.value));
}

// Small CSS helper for chip via JS injection
(function injectChipStyle(){
    const style = document.createElement("style");
    style.textContent = `
    .card-link{ transition: transform .15s ease, box-shadow .15s ease; }
    .card-link:hover{ transform: translateY(-3px); box-shadow: 0 18px 40px rgba(0,0,0,.12); }
    .chip{
      font-size: .75rem;
      padding: .35rem .55rem;
      border-radius: 999px;
      background: rgba(13,110,253,.10);
      color: #0d6efd;
      border: 1px solid rgba(13,110,253,.15);
    }
    body.theme-dark .chip{
      background: rgba(110,168,254,.12);
      color: #9cc3ff;
      border-color: rgba(110,168,254,.25);
    }
  `;
    document.head.appendChild(style);
})();

// Init
renderCards("all");
initFilter();
initThemeToggle();
