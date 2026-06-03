const API = 'http://localhost:8080/api';

// Hent alle MITRE-teknikker
async function fetchTechniques() {
    const res = await fetch(`${API}/techniques`);
    return res.json();
}

// Hent alle tests
async function fetchTests() {
    const res = await fetch(`${API}/tests`);
    return res.json();
}

// Hent alle test runs
async function fetchRuns() {
    const res = await fetch(`${API}/runs`);
    return res.json();
}

// Hent coverage
async function fetchCoverage() {
    const res = await fetch(`${API}/runs/coverage`);
    return res.json();
}

// Oversæt result til dansk badge HTML
function resultBadge(result) {
    const map = {
        'DETECTED':     '<span class="badge detected">Detected</span>',
        'NOT_DETECTED': '<span class="badge not_detected">Not detected</span>',
        'PARTIAL':      '<span class="badge partial">Partial</span>'
    };
    return map[result] || result;
}

// Oversæt test type til badge HTML
function typeBadge(type) {
    if (type === 'CUSTOM') return '<span class="badge custom">Custom</span>';
    if (type === 'ART')    return '<span class="badge art">ART</span>';
    return type;
}

// Formater dato
function formatDate(dateStr) {
    if (!dateStr) return '-';
    const d = new Date(dateStr);
    return d.toLocaleDateString('da-DK');
}

// Vis alert besked
function showAlert(id, type, message) {
    const el = document.getElementById(id);
    if (!el) return;
    el.className = `alert ${type}`;
    el.textContent = message;
    el.style.display = 'block';
    setTimeout(() => { el.style.display = 'none'; }, 3000);
}

// Sæt aktiv nav-link
function setActiveNav() {
    const page = window.location.pathname.split('/').pop() || 'index.html';
    document.querySelectorAll('.nav-links a').forEach(a => {
        a.classList.remove('active');
        if (a.getAttribute('href') === page) {
            a.classList.add('active');
        }
    });
}

document.addEventListener('DOMContentLoaded', setActiveNav);
